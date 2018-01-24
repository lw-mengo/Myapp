package example.study.com.myapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import example.study.com.myapp.R;
import example.study.com.myapp.bean.VideoInfo;

/**
 * Created by lw_mengo on 2018/1/24 0024.
 * 作用：选集recycle 的适配器
 */

public class BangumiEpisodeAdapter extends AbsRecyclerViewAdapter {

    private int layoutPosition = 0;
    private List<VideoInfo.VideoUrlBean> videoUrlBeans;

    public BangumiEpisodeAdapter(RecyclerView recyclerView, List<VideoInfo.VideoUrlBean> videoUrlBeans) {
        super(recyclerView);
        this.videoUrlBeans = videoUrlBeans;
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
        return new ItemViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_episode, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            VideoInfo.VideoUrlBean videoInfo = videoUrlBeans.get(position);
            itemViewHolder.mIndex.setText(videoInfo.getEpisode());
            itemViewHolder.mTitle.setText(videoInfo.getVideoLink());

            if (position == layoutPosition) {
                itemViewHolder.mCardView.setForeground(getContext().getResources().getDrawable(R.drawable.bg_selection));
                itemViewHolder.mIndex.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                itemViewHolder.mTitle.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
            }else {
                itemViewHolder.mCardView.setForeground(getContext().getResources().getDrawable(R.drawable.bg_normal));
                itemViewHolder.mIndex.setTextColor(getContext().getResources().getColor(R.color.black_alpha_45));
                itemViewHolder.mTitle.setTextColor(getContext().getResources().getColor(R.color.font_normal));
            }
        }
        super.onBindViewHolder(holder, position);
    }

    public void notifyItemForeground(int clickPosition){
        layoutPosition = clickPosition;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return videoUrlBeans.size();
    }

    public class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {
        CardView mCardView;
        TextView mIndex;
        TextView mTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mCardView = $(R.id.card_view);
            mIndex = $(R.id.tv_index);
            mTitle = $(R.id.tv_title);
        }
    }
}
