package example.study.com.myapp.module;

import android.content.Intent;
import android.os.Bundle;

import com.trello.rxlifecycle2.components.RxActivity;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.study.com.myapp.R;
import example.study.com.myapp.utils.SystemUiVisibilityUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SplashActivity extends RxActivity {

    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        bind = ButterKnife.bind(this);
        SystemUiVisibilityUtil.hideStatusBar(getWindow(),true);
        setUpSplash();

    }

    private void setUpSplash() {
        Observable.timer(2, TimeUnit.SECONDS)
                .compose(this.<Long>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(along -> finishTask());
    }

    private void finishTask() {
        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
