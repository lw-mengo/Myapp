package example.study.com.myapp.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import example.study.com.myapp.R;
import example.study.com.myapp.base.RxLazyFragment;
import example.study.com.myapp.bean.ImageInfo;
import example.study.com.myapp.network.RetrofitHelper;
import example.study.com.myapp.view.CustomEmptyView;
import example.study.com.myapp.view.SectionedRecyclerViewAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lw_mengo on 2018/1/20 0020.
 * 作用：主页的fragment
 */

public class HomePage extends RxLazyFragment {
    private static final String TAG="error";

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycle)
    RecyclerView mRecycleView;
    @BindView(R.id.empty_layout)
    CustomEmptyView mCustomEmptyView;

    private boolean mIsRefreshing = false;
    private SectionedRecyclerViewAdapter mSectionedAdapter;

    private List<ImageInfo.ImageUrlBean> imageUrlBeans = new ArrayList<>();

    public static HomePage newInstance() {
        return new HomePage();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.home_page;
    }

    @Override
    public void finishCreateView(Bundle state) {
        isPrepared = true;
        lazeLoad();

    }

    @Override
    protected void lazeLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        initRefreshLayout();
        initRecycleView();
        isPrepared = false;
    }

    @Override
    protected void initRecycleView() {
        mSectionedAdapter = new SectionedRecyclerViewAdapter();
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mSectionedAdapter.getItemViewType(position)) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                        return 3;
                    default:
                        return 1;
                }
            }
        });
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setNestedScrollingEnabled(true);
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(mSectionedAdapter);
        setRecycleNoScroll();
    }

    @Override
    protected void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.post(() -> {
            mSwipeRefreshLayout.setRefreshing(true);
            mIsRefreshing = true;
            loadData();
        });
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            clearData();
            loadData();
        });
    }

    @Override
    protected void loadData() {
        RetrofitHelper.getInfo()
                .getImageInfo()
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(imageInfo -> {
                    imageUrlBeans.addAll(imageInfo.getImageUrl());
                    finishTask();
                }, throwable -> initEmptyView());
        Log.d(TAG, "loadData:+ "+"error");
    }

    @Override
    protected void finishTask() {
        mSwipeRefreshLayout.setRefreshing(false);
        mIsRefreshing = false;
        hideEmptyView();
        mSectionedAdapter.addSection(new HomeBangumiSection(getActivity(), imageUrlBeans));
        mSectionedAdapter.notifyDataSetChanged();
    }

    public void initEmptyView() {
        mSwipeRefreshLayout.setRefreshing(false);
        mCustomEmptyView.setVisibility(View.VISIBLE);
        mRecycleView.setVisibility(View.GONE);
        mCustomEmptyView.setEmptyImg(R.drawable.img_tips_error_load_error);
        mCustomEmptyView.setEmptyText("加载数据失败~");
        Snackbar.make(mRecycleView, "数据加载失败,检查网络是否连接", Snackbar.LENGTH_SHORT).show();

    }

    public void hideEmptyView() {
        mCustomEmptyView.setVisibility(View.GONE);
        mRecycleView.setVisibility(View.VISIBLE);
    }

    private void clearData() {
        mIsRefreshing = true;
        imageUrlBeans.clear();
        mSectionedAdapter.removeAllSections();
    }

    private void setRecycleNoScroll() {
        mRecycleView.setOnTouchListener((view, motionEvent) -> mIsRefreshing);
    }
}
