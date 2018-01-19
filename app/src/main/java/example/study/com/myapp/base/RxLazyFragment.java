package example.study.com.myapp.base;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lw_mengo on 2018/1/19 0019.
 * 作用：Fragment基类
 */

public abstract class RxLazyFragment extends RxFragment {

    private View parentView;
    private FragmentActivity activity;

    protected boolean isPrepared;

    protected boolean isVisible;

    private Unbinder bind;

    public abstract @LayoutRes
    int getLayoutResId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = inflater.inflate(getLayoutResId(), container, false);
        activity = getSupportActivity();
        return parentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = ButterKnife.bind(this, view);
        finishCreateView(savedInstanceState);
    }

    public abstract void finishCreateView(Bundle state);

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (FragmentActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }


    public FragmentActivity getSupportActivity() {
        return super.getActivity();
    }

    public ActionBar getSupportActionBar() {
        return getSupportActivity().getActionBar();
    }

    public Context getApplicationContext() {
        return this.activity == null ? (getActivity() == null ? null : getActivity().getApplicationContext()) : this.activity.getApplicationContext();
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInVisible();
        }
    }

    protected void onVisible() {
        lazeLoad();
    }

    protected void lazeLoad() {
    }

    protected void onInVisible() {
    }

    protected void loadData() {
    }

    protected void showProgressBar() {
    }

    protected void hideProgressBar() {
    }

    protected void initRecycleView() {
    }

    protected void initRefreshLayout() {
    }

    protected void finishTask() {
    }

}
