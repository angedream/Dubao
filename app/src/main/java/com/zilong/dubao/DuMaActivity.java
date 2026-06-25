package com.zilong.dubao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;

public class DuMaActivity extends AppCompatActivity {
    private AgentWeb mAgentWeb;
    private FrameLayout mFullscreenContainer;
    private View mCustomView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    // 2. 自定义 WebChromeClient
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onPermissionRequest(PermissionRequest permissionRequest) {
            permissionRequest.grant(permissionRequest.getResources());

//            super.onPermissionRequest(permissionRequest);
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            // 进入全屏
            if (mCustomView != null) {
                callback.onCustomViewHidden();
                return;
            }

            mCustomView = view;
            mCustomViewCallback = callback;

            // 将全屏视频 View 添加到容器中
            mFullscreenContainer.addView(view);
            mFullscreenContainer.setVisibility(View.VISIBLE);

            // 隐藏 AgentWeb 和标题栏等
            mAgentWeb.getWebCreator().getWebView().setVisibility(View.GONE);
            // 隐藏你的 Toolbar
//            getSupportActionBar().hide();

            // 可选：强制横屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        @Override
        public void onHideCustomView() {
            // 退出全屏
            if (mCustomView == null) {
                return;
            }

            // 移除全屏视频 View
            mFullscreenContainer.removeView(mCustomView);
            mFullscreenContainer.setVisibility(View.GONE);

            // 恢复 AgentWeb 和标题栏的显示
            mAgentWeb.getWebCreator().getWebView().setVisibility(View.VISIBLE);
            // 恢复显示你的 Toolbar
//            getSupportActionBar().show();

            // 恢复竖屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            // 通知 WebView 全屏已退出
            mCustomViewCallback.onCustomViewHidden();

            mCustomView = null;
            mCustomViewCallback = null;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_du_ma);
        LinearLayout container = findViewById(R.id.web_container);
        mFullscreenContainer = findViewById(R.id.fullscreen_container);

        // 2. 构建 AgentWeb 实例
        mAgentWeb = AgentWeb.with(this) // 传入 Activity
                .setAgentWebParent(container, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)) // 设置父控件和布局参数
                .useDefaultIndicator() // 使用默认进度条
                .setWebChromeClient(mWebChromeClient)
                .createAgentWeb() // 创建 AgentWeb 对象
                .ready() // 准备就绪
                .go(MyConfig.dumaURL);
        // 注意：如果 HTML 中使用了 JavaScript，需要手动启用
        if (mAgentWeb != null && mAgentWeb.getAgentWebSettings() != null) {
            mAgentWeb.getAgentWebSettings().getWebSettings().setJavaScriptEnabled(true);
        }
        WebView webView = mAgentWeb.getWebCreator().getWebView();
        WebSettings settings = webView.getSettings();
        settings.setAllowFileAccess(true);           // 允许访问文件
        settings.setAllowFileAccessFromFileURLs(true);  // 允许 file:// 下的跨文件访问
        settings.setAllowUniversalAccessFromFileURLs(true); // 允许所有 file:// 下的跨文件访问
        settings.setMediaPlaybackRequiresUserGesture(false); // 允许自动播放
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        echo.print("加载完成");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
    }
}