package example.study.com.myapp.module;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import example.study.com.myapp.R;
import example.study.com.myapp.base.RxBaseActivity;
import example.study.com.myapp.media.MediaController;
import example.study.com.myapp.media.VideoPlayerView;
import example.study.com.myapp.media.callback.VideoBackListener;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class VideoPlayerActivity extends RxBaseActivity implements VideoBackListener {


    @BindView(R.id.playerView)
    VideoPlayerView mPlayerView;
    @BindView(R.id.buffering_indicator)
    View mBufferingIndicator;
    @BindView(R.id.video_start)
    RelativeLayout mVideoPrepareLayout;
    @BindView(R.id.video_start_info)
    TextView mPrepareText;


    private String url;
    private String title;
    private int LastPosition = 0;
    private String startText = "初始化播放器……";


    @Override
    public int getLayoutId() {
        return R.layout.activity_video_player;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent!=null){
            url = intent.getStringExtra("url");
            title = intent.getStringExtra("title");
        }

        initMediaPlayer();

    }

    @Override
    public void initToolBar() {

    }

    private void initMediaPlayer() {
        //配置播放器
        MediaController mMediaController = new MediaController(this);
        mMediaController.setTitle(title);
        mPlayerView.setMediaController(mMediaController);
        mPlayerView.setMediaBufferingIndicator(mBufferingIndicator);
        mPlayerView.requestFocus();
        mPlayerView.setOnInfoListener(onInfoListener);
        mPlayerView.setOnSeekCompleteListener(onSeekCompleteListener);
        mPlayerView.setOnCompletionListener(onCompletionListener);
        mPlayerView.setOnControllerEventsListener(onControllerEventsListener);
        //设置返回键监听
        mMediaController.setVideoBackEvent(this);
        loadData();
    }

    @Override
    public void loadData() {
        Uri uri = Uri.parse(url);
        mVideoPrepareLayout.setVisibility(View.GONE);
        mPlayerView.setVideoURI(uri);
        mPlayerView.start();
    }

    /**
     * 视频缓冲事件回调
     */
    private IMediaPlayer.OnInfoListener onInfoListener = new IMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(IMediaPlayer mp, int what, int extra) {
            if (what == IMediaPlayer.MEDIA_INFO_BUFFERING_START) {
                if (mBufferingIndicator != null) {
                    mBufferingIndicator.setVisibility(View.VISIBLE);
                }

            } else if (what == IMediaPlayer.MEDIA_INFO_BUFFERING_END) {
                if (mBufferingIndicator != null) {
                    mBufferingIndicator.setVisibility(View.GONE);
                }
            }
            return true;
        }
    };

    /**
     * 视频跳转事件回调
     */
    private IMediaPlayer.OnSeekCompleteListener onSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() {

        @Override
        public void onSeekComplete(IMediaPlayer mp) {

        }
    };

    /**
     * 视频播放完成事件回调
     */
    private IMediaPlayer.OnCompletionListener onCompletionListener = new IMediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(IMediaPlayer mp) {

            mPlayerView.pause();
        }
    };

    /**
     * 控制条控制状态事件回调
     */
    private VideoPlayerView.OnControllerEventsListener onControllerEventsListener = new VideoPlayerView.OnControllerEventsListener() {

        @Override
        public void onVideoPause() {

        }


        @Override
        public void OnVideoResume() {

        }
    };


    @Override
    protected void onResume() {
        super.onResume();

        if (mPlayerView != null && !mPlayerView.isPlaying()) {
            mPlayerView.seekTo(LastPosition);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayerView != null) {
            LastPosition = mPlayerView.getCurrentPosition();
            mPlayerView.pause();
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayerView != null && mPlayerView.isDrawingCacheEnabled()) {
            mPlayerView.destroyDrawingCache();
        }

    }


    /**
     * 退出界面回调
     */
    @Override
    public void back() {
        onBackPressed();
    }


    public static void launch(Activity activity, String url, String title) {
        Intent mIntent = new Intent(activity, VideoPlayerActivity.class);
        mIntent.putExtra("url",url);
        mIntent.putExtra("title",title);
        activity.startActivity(mIntent);
    }
}