package example.study.com.myapp.bean;

import java.util.List;

/**
 * Created by lw_mengo on 2018/1/22 0022.
 * 作用：图片和标题
 */

public class ImageInfo {


    /**
     * info : success
     * imageUrl : [{"title":"魔卡少女樱CLEAR CARD篇","imgUrl":"http://img.zxziyuan.com/images/upload/vod/2018-01-14/201801141515938993.jpg"},{"title":"刻刻","imgUrl":"http://img.zxziyuan.com/images/upload/vod/2018-01-08/201801081515418816.jpg"},{"title":"柑橘味香气","imgUrl":"http://img.zxziyuan.com/images/upload/vod/2018-01-08/201801081515415127.jpg"},{"title":"刀使之巫女","imgUrl":"http://img.zxziyuan.com/images/upload/vod/2018-01-08/201801081515418227.jpg"},{"title":"帝圣印战记","imgUrl":"http://img.zxziyuan.com/images/upload/vod/2018-01-08/201801081515418673.jpg"}]
     */

    private String info;
    private List<ImageUrlBean> imageUrl;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<ImageUrlBean> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<ImageUrlBean> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static class ImageUrlBean {
        /**
         * title : 魔卡少女樱CLEAR CARD篇
         * imgUrl : http://img.zxziyuan.com/images/upload/vod/2018-01-14/201801141515938993.jpg
         */

        private String title;
        private String imgUrl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
