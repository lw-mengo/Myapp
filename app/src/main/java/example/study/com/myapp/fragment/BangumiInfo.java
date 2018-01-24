package example.study.com.myapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import example.study.com.myapp.R;
import example.study.com.myapp.adapter.AbsRecyclerViewAdapter;
import example.study.com.myapp.adapter.BangumiEpisodeAdapter;
import example.study.com.myapp.base.RxBaseActivity;
import example.study.com.myapp.bean.VideoInfo;
import example.study.com.myapp.network.RetrofitHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BangumiInfo extends RxBaseActivity{

    private static final String TAG = "error";

    @BindView(R.id.nested_scroll_view)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.bangumi_bg)
    ImageView bangumiBackgroundImage;
    @BindView(R.id.bangumi_title)
    TextView bangumiTitle;
    @BindView(R.id.bangumi_content)
    TextView bangumiContent;
    @BindView(R.id.bangumi_juji)
    RecyclerView bangumiEpisode;

    private List<VideoInfo.VideoUrlBean> videoUrlBeans =new ArrayList<>();
    private VideoInfo videoInfo;

    private String imgUrl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bangumi_info;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent!=null){
            imgUrl = intent.getStringExtra("imageurl");
        }
        loadData();

    }

    @Override
    public void finishTask() {
        Glide.with(this)
                .load(imgUrl)
                .into(bangumiBackgroundImage);
        bangumiTitle.setText(videoInfo.getTitle());
        bangumiContent.setText(videoInfo.getContent());
        initSelectionRecycler();

    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void loadData() {
        RetrofitHelper.getInfo().getBangumiInfo()
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(videoInfo1 -> {
                    videoUrlBeans.addAll(videoInfo1.getVideoUrl());
                    finishTask();
                },throwable -> Log.d(TAG, "loadData: "+throwable.getMessage()));

    }

    private void initSelectionRecycler(){
        List<VideoInfo.VideoUrlBean> videoUrlBeans = videoInfo.getVideoUrl();
        bangumiEpisode.setHasFixedSize(false);
        bangumiEpisode.setNestedScrollingEnabled(false);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mLinearLayoutManager.setReverseLayout(true);
        bangumiEpisode.setLayoutManager(mLinearLayoutManager);
        BangumiEpisodeAdapter bangumiEpisodeAdapter = new BangumiEpisodeAdapter(bangumiEpisode,videoUrlBeans);
        bangumiEpisode.setAdapter(bangumiEpisodeAdapter);
        bangumiEpisodeAdapter.notifyItemForeground(videoUrlBeans.size()-1);
        bangumiEpisode.scrollToPosition(videoUrlBeans.size()-1);
        bangumiEpisodeAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
                bangumiEpisodeAdapter.notifyItemForeground(holder.getLayoutPosition());

            }
        });
    }

    public static void launch(Activity activity,String url){
        Intent intent = new Intent(activity,BangumiInfo.class);
        Bundle bundle = new Bundle();
        bundle.putString("imageurl",url);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
}

