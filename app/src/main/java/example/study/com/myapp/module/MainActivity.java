package example.study.com.myapp.module;

import android.os.Bundle;
import android.util.Log;

import example.study.com.myapp.R;
import example.study.com.myapp.base.RxBaseActivity;
import example.study.com.myapp.fragment.HomePage;

public class MainActivity extends RxBaseActivity {
    private static final String TAG="MainActivity";

    private HomePage mHomePage;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        initFragment();

    }

    @Override
    public void initToolBar() {

    }

    private void initFragment() {
        mHomePage = HomePage.newInstance();
        Log.d(TAG, "initFragment: +"+"222");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, mHomePage)
                .show(mHomePage).commit();


    }
}
