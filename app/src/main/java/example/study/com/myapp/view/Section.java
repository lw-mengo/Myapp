package example.study.com.myapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by lw_mengo on 2018/1/20 0020.
 * 作用：抽象类section ，给recyclvie适配器使用
 */

public abstract class Section {
    public enum State {
        LOADING, LOADED, FAILED
    }

    private State state = State.LOADED;
    boolean visible = true;
    boolean hasHeader = false;
    boolean hasFooter = false;
    Integer headerResourceId;
    Integer footerResourceId;
    int itemResourceId;
    private Integer loadingResourceId;
    private Integer failedResourceId;

    Section() {
    }

    public Section(int itemResourceId, int loadingResourceId, int failedResourceId) {
        this.itemResourceId = itemResourceId;
        this.loadingResourceId = loadingResourceId;
        this.failedResourceId = failedResourceId;
    }

    public Section(int headerResourceId, int itemResourceId, int loadingResourceId, int failedResourceId) {
        this(itemResourceId, loadingResourceId, failedResourceId);
        this.headerResourceId = headerResourceId;
        hasHeader = true;
    }

    public Section(int headerResourceId, int footerResourceId, int itemResourceId, int loadingResourceId, int failedResourceId) {
        this(headerResourceId, itemResourceId, loadingResourceId, failedResourceId);
        this.footerResourceId = footerResourceId;
        hasFooter = true;
    }


    public final void setState(State state) {
        this.state = state;
    }

    public final State getState() {
        return state;
    }

    public final boolean isVisible() {
        return visible;
    }

    public final void setVisible(boolean visible) {
        this.visible = visible;
    }

    public final boolean hasHeader() {
        return hasHeader;
    }

    public final void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader();
    }

    public final boolean hasFooter() {
        return hasFooter;
    }

    public final void setHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
    }

    public final Integer getHeaderResourceId() {
        return headerResourceId;
    }

    public final Integer getFooterResourceId() {
        return footerResourceId;
    }

    public final int getItemResourceId() {
        return itemResourceId;
    }

    public final Integer getLoadingResourceId() {
        return loadingResourceId;
    }
    public final Integer getFailedResourceId(){
        return failedResourceId;
    }

    public final void onBindContentViewHolder(RecyclerView.ViewHolder holder,int position){
        switch (state){
            case LOADING:onBindLoadingViewHolder(holder);
            break;
            case LOADED:
                onBindItemViewHolder(holder,position);
                break;
            case FAILED:
                onBindFailedViewHolder(holder);
                break;
                default:
                    throw new IllegalStateException("invalid state!");
        }
    }

    public final int getSectionItemsTotal(){
        int contentItemsTotal;
        switch (state){
            case LOADING:contentItemsTotal=1;
            break;
            case LOADED:contentItemsTotal=getContentItemsTotal();
            break;
            case FAILED:contentItemsTotal =1;
            break;
            default:
                throw new IllegalStateException("invalid state");
        }
        return contentItemsTotal+(hasHeader?1:0)+(hasFooter?1:0);
    }

    public abstract int getContentItemsTotal();

    public RecyclerView.ViewHolder getHeaderViewHolder(View view){
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder){

    }

    public RecyclerView.ViewHolder getFooterViewHolder(View view){
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder){}

    public abstract RecyclerView.ViewHolder getItemViewHolder(View view);

    public abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder,int position);

    public RecyclerView.ViewHolder getLoadingViewHolder(View view){
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    public void onBindLoadingViewHolder(RecyclerView.ViewHolder holder){

    }
    public RecyclerView.ViewHolder getFailedViewHolder(View view){
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    public void onBindFailedViewHolder(RecyclerView.ViewHolder holder){}
}
