package example.study.com.myapp.bean;

import java.util.List;

/**
 * Created by lw_mengo on 2018/1/22 0022.
 * 作用：视频信息实体类
 */

public class VideoInfo {


    /**
     * title : 帝圣印战记
     * content : 浑沌支配了大陆──人民畏惧著各种灾害。君王们手持著平息浑沌的〈圣印〉守护百姓。然而随著岁月流逝，君王们逐渐忘却了“守护苍生”的理念，为了争夺彼此的圣印和领地，掀起了一波又一波的战争……魔法师希露卡唾弃毫无风骨的各地君王，而浪迹天涯的骑士提欧则是为了解放受苛政所苦的家乡修练武艺。
     * videoUrl : [{"episode":"第01集","videoLink":"http://zy.kakazy-yun.com/20180108/4GzdP81e/index.m3u8"},{"episode":"第02集","videoLink":"http://yun.zxziyuan-yun.com/20180115/9ZvzOHbw/index.m3u8"},{"episode":"第03集","videoLink":"http://zy.zxziyuan-yun.com/20180119/mXMeWUEj/index.m3u8"}]
     */

    private String title;
    private String content;
    private List<VideoUrlBean> videoUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<VideoUrlBean> getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(List<VideoUrlBean> videoUrl) {
        this.videoUrl = videoUrl;
    }

    public static class VideoUrlBean {
        /**
         * episode : 第01集
         * videoLink : http://zy.kakazy-yun.com/20180108/4GzdP81e/index.m3u8
         */

        private String episode;
        private String videoLink;

        public String getEpisode() {
            return episode;
        }

        public void setEpisode(String episode) {
            this.episode = episode;
        }

        public String getVideoLink() {
            return videoLink;
        }

        public void setVideoLink(String videoLink) {
            this.videoLink = videoLink;
        }
    }
}
