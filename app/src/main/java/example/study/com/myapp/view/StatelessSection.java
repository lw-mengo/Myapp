package example.study.com.myapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by lw_mengo on 2018/1/22 0022.
 * 作用：
 */

public abstract class StatelessSection extends Section {

    public StatelessSection(int itemResourceId) {
        super();
        this.itemResourceId = itemResourceId;
    }

    public StatelessSection(int headerResourceId, int itemResourceId) {
        this(itemResourceId);
        this.headerResourceId = headerResourceId;
        this.hasHeader = true;

    }

    public StatelessSection(int headerResourceId,int footerResourceId,int itemResourceId){
        this(headerResourceId,itemResourceId);
        this.footerResourceId = footerResourceId;
        this.hasHeader = true;
    }

    @Override
    public void onBindLoadingViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindLoadingViewHolder(holder);
    }

    @Override
    public RecyclerView.ViewHolder getLoadingViewHolder(View view) {
        return super.getLoadingViewHolder(view);
    }

    @Override
    public void onBindFailedViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindFailedViewHolder(holder);
    }

    @Override
    public RecyclerView.ViewHolder getFailedViewHolder(View view) {
        return super.getFailedViewHolder(view);
    }
}
