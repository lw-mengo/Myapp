package example.study.com.myapp.utils;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by lw_mengo on 2018/1/20 0020.
 * 作用：statusBar隐藏显示工具类
 */

public class SystemUiVisibilityUtil {
    public static void addFlags(View view, int flags) {
        view.setSystemUiVisibility(view.getSystemUiVisibility() | flags);
    }

    public static void clearFlags(View view, int flags) {
        view.setSystemUiVisibility(view.getSystemUiVisibility() & ~flags);
    }

    /**
     * 显示或隐藏状态栏
     */
    public static void hideStatusBar(Window window, boolean enable) {
        WindowManager.LayoutParams p = window.getAttributes();
        if (enable) {
            p.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        } else {
            p.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        window.setAttributes(p);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}
