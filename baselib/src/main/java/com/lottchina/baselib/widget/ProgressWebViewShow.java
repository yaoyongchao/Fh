package com.lottchina.baselib.widget;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.lottchina.baselib.utils.ScreenUtils;


public class ProgressWebViewShow extends WebView {

    private ProgressBar progressbar;

    private OnTitleListener onTitleListener;

    public OnTitleListener getOnTitleListener() {
        return onTitleListener;
    }

    public void setOnTitleListener(OnTitleListener onTitleListener) {
        this.onTitleListener = onTitleListener;
    }

    public ProgressWebViewShow(Context context) {
        super(context);
    }

    public ProgressWebViewShow(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, ScreenUtils.dip2px(context, 3), 0, 0));
        addView(progressbar);
        //        setWebViewClient(new WebViewClient(){});
        setWebChromeClient(new WebChromeClient());
    }


    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            if(onTitleListener!=null)
                onTitleListener.onTitle(title);
        }

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public interface OnTitleListener{
        public void onTitle(String title);
    }
}
