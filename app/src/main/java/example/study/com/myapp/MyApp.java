package example.study.com.myapp;

import android.app.Application;

/**
 * Created by lw_mengo on 2018/1/22 0022.
 * 作用：
 */

public class MyApp  extends Application{
    public static MyApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static MyApp getInstance(){
        return mInstance;
    }
}
