package example.study.com.myapp.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import example.study.com.myapp.R;
import example.study.com.myapp.base.RxLazyFragment;
import example.study.com.myapp.view.CustomEmptyView;
import example.study.com.myapp.view.SectionedRecyclerViewAdapter;

/**
 * Created by lw_mengo on 2018/1/20 0020.
 * 作用：主页的fragment
 */

public class HomePage extends RxLazyFragment {
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycle)
    RecyclerView mRecycleView;
    @BindView(R.id.empty_layout)
    CustomEmptyView mCustomEmptyView;

    private boolean mIsRefreshing = false;
    private SectionedRecyclerViewAdapter mSectionedAdapter;

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
        isPrepared = false;
    }

    @Override
    protected void initRecycleView() {
        mSectionedAdapter = new SectionedRecyclerViewAdapter();
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mSectionedAdapter.getItemViewType(position)) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                        return 2;
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER:
                        return 2;
                    default:
                        return 1;
                }
            }
        });
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
        super.loadData();
    }

    public void initEmptyView() {
        mSwipeRefreshLayout.setRefreshing(false);
        mCustomEmptyView.setVisibility(View.VISIBLE);
        mRecycleView.setVisibility(View.GONE);
        mCustomEmptyView.setEmptyImg(R.drawable.img_tips_error_load_error);
        mCustomEmptyView.setEmptyText("加载数据失败~");
        Snackbar.make(mRecycleView, "数据加载失败,检查网络是否连接", Snackbar.LENGTH_SHORT).show();

    }

    private void clearData() {
        mIsRefreshing = true;
        mSectionedAdapter.removeAllSections();
    }

    private void setRecycleNoScroll() {
        mRecycleView.setOnTouchListener((view, motionEvent) -> mIsRefreshing);
    }
}
