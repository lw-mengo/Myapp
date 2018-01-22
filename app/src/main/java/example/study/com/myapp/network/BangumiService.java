package example.study.com.myapp.network;

import example.study.com.myapp.bean.ImageInfo;
import example.study.com.myapp.bean.VideoInfo;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by lw_mengo on 2018/1/22 0022.
 * 作用：番剧相关api
 */

public interface BangumiService {
    @GET("getJsonList?cid=0")
    Observable<VideoInfo> getBangumiInfo();

    @GET("getImageUrl")
    Observable<ImageInfo> getImageInfo();
}
