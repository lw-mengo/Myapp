package example.study.com.myapp.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.study.com.myapp.R;
import example.study.com.myapp.bean.ImageInfo;
import example.study.com.myapp.view.StatelessSection;

/**
 * Created by lw_mengo on 2018/1/22 0022.
 * 作用：
 */

public class HomeBangumiSection extends StatelessSection {

    private Context mContext;
    private List<ImageInfo.ImageUrlBean> imageUrlBeans;

    public HomeBangumiSection(Context context, List<ImageInfo.ImageUrlBean> imageUrlBeans) {
        super(R.layout.home_page_section_header, R.layout.home_page_section_body);
        this.mContext = context;
        this.imageUrlBeans = imageUrlBeans;
    }

    @Override
    public int getContentItemsTotal() {
        return imageUrlBeans.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        ImageInfo.ImageUrlBean imageUrlBean = imageUrlBeans.get(position);

        Glide.with(mContext)
                .load(imageUrlBean.getImgUrl())
                .into(itemViewHolder.mImage);

        itemViewHolder.mTitle.setText(imageUrlBean.getTitle());
        //Todo click event
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        headerViewHolder.mAllSerial.setOnClickListener(view -> {
            //Todo
        });
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_all_serial)
        TextView mAllSerial;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view)
        LinearLayout mCardView;

        @BindView(R.id.item_img)
        ImageView mImage;

        @BindView(R.id.item_title)
        TextView mTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
