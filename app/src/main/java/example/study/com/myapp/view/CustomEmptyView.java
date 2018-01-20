package example.study.com.myapp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import example.study.com.myapp.R;

/**
 * Created by lw_mengo on 2018/1/20 0020.
 * 作用：自定义的emptyview
 */

public class CustomEmptyView extends FrameLayout {

    private ImageView mEmptyImg;
    private TextView mEmptyText;

    public CustomEmptyView(@NonNull Context context) {
        this(context, null);
    }

    public CustomEmptyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomEmptyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_empty,this);
        mEmptyImg = view.findViewById(R.id.empty_img);
        mEmptyText = view.findViewById(R.id.empty_text);
    }

    public void setEmptyImg(int imgRes) {
       mEmptyImg.setImageResource(imgRes);
    }

    public void setEmptyText(String text) {
        mEmptyText.setText(text);
    }
}
