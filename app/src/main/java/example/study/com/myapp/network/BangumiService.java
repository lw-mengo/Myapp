package example.study.com.myapp.network;

import example.study.com.myapp.bean.ImageInfo;
import example.study.com.myapp.bean.VideoInfo;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lw_mengo on 2018/1/22 0022.
 * 作用：番剧相关api
 */

public interface BangumiService {
    @GET("getJsonList")
    Observable<VideoInfo> getBangumiInfo(@Query("cid") int id);

    @GET("getImageUrl")
    Observable<ImageInfo> getImageInfo();
}
