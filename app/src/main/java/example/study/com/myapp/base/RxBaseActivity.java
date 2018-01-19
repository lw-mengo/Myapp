package example.study.com.myapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lw_mengo on 2018/1/19 0019.
 * 作用：activity基类
 */

public abstract class RxBaseActivity extends RxAppCompatActivity {
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局内容
        setContentView(getLayoutId());
        //初始化黄油刀控件
        bind = ButterKnife.bind(this);
        //初始化控件
        initViews(savedInstanceState);
        //初始化Toolbar
        initToolBar();
    }
    public abstract int getLayoutId();

    public abstract void initViews(Bundle savedInstanceState);

    public abstract  void initToolBar();

    public void loadData(){}

    public void showProgressBar(){}

    public void hideProgressBar(){}

    public void initRecycleView(){}

    public void initRefreshLayout(){}

    public void finishTask(){}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
