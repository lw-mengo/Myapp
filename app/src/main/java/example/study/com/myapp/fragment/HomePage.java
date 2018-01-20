package example.study.com.myapp.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import example.study.com.myapp.R;
import example.study.com.myapp.base.RxLazyFragment;
import example.study.com.myapp.view.CustomEmptyView;

/**
 * Created by lw_mengo on 2018/1/20 0020.
 * 作用：
 */

public class HomePage extends RxLazyFragment {
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycle)
    RecyclerView mRecycleView;
    @BindView(R.id.empty_layout)
    CustomEmptyView mCustomEmptyView;

    private boolean mIsRefreshing = false;
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
        if(!isPrepared||!isVisible){
            return;
        }
        isPrepared =false;
    }
}
