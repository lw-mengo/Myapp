package example.study.com.myapp.network;

import java.io.File;
import java.util.concurrent.TimeUnit;

import example.study.com.myapp.Constant;
import example.study.com.myapp.MyApp;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lw_mengo on 2018/1/22 0022.
 * 作用：retrofit帮助类
 */

public class RetrofitHelper {
    private static OkHttpClient mOkhttpClient;

    static {
        initOkhttpClient();

    }

    public static BangumiService getInfo() {
        return createApi(BangumiService.class, Constant.BASE_URL);
    }


    private static <T> T createApi(Class<T> clazz, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkhttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

    private static void initOkhttpClient() {
        if (mOkhttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (mOkhttpClient == null) {
                    Cache cache = new Cache(new File(MyApp.getmInstance().getCacheDir(), "httpCache"), 1024 * 1024 * 10);
                    mOkhttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }
}
