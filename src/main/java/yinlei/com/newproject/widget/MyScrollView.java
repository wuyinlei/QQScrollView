package yinlei.com.newproject.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 在这里主要是分离一个接口回调  用来检测我们的滑动 然后实时的获取滑动的距离
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: MyScrollView.java
 * @author: 若兰明月
 * @date: 2016-08-18 22:32
 */

public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 滑动接口回调
     */
    public interface ScrollViewLinstener {
        void onScrollViewScrollChanged(int x, int y, int oldl, int oldy);
    }

    private ScrollViewLinstener mLinstener;

    /**
     * 对外提供设置监听的方法
     */
    public void setLinstener(ScrollViewLinstener linstener) {
        mLinstener = linstener;
    }

    /**
     * 重写滑动变化的函数
     *
     * @param l    Current horizontal scroll origin.
     * @param t    Current vertical scroll origin.
     * @param oldl Previous horizontal scroll origin.
     * @param oldt Previous vertical scroll origin.
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mLinstener != null) {
            mLinstener.onScrollViewScrollChanged(l, t, oldl, oldt);
        }
    }
}
