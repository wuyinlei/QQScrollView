package yinlei.com.newproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import yinlei.com.newproject.utils.StatusBarUtil;
import yinlei.com.newproject.widget.MyScrollView;

public class MainActivity extends AppCompatActivity implements MyScrollView.ScrollViewLinstener {

    private MyScrollView scrollView;

    private ListView listView;

    private TextView textView;
    private int height;
    private ImageView ivBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImgTransparent(this);
        setContentView(R.layout.activity_main);

        initView();

        ivBanner.setFocusable(true);
        ivBanner.setFocusableInTouchMode(true);
        ivBanner.requestFocus();

        initHeight();
        initData();
    }

    private void initHeight() {
        ViewTreeObserver treeObserver = ivBanner.getViewTreeObserver();
        treeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                textView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = ivBanner.getHeight();

                scrollView.setLinstener(MainActivity.this);
            }
        });
    }

    private void initData() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.data));
        listView.setAdapter(adapter);
    }

    private void initView() {
        scrollView = (MyScrollView) findViewById(R.id.scrollview);
        listView = (ListView) findViewById(R.id.listview);
        textView = (TextView) findViewById(R.id.textview);
        ivBanner = (ImageView) findViewById(R.id.iv_banner);
    }

    @Override
    public void onScrollViewScrollChanged(int x, int y, int oldl, int oldy) {
        if (y <= 0) {
            textView.setBackgroundColor(Color.argb((int) 0, 144, 155, 180));
        } else if (y > 0 && y < height) { //当滑动的距离小于图片的高度  设置颜色背景渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
            textView.setTextColor(Color.argb((int) alpha, 255, 255, 255));
            textView.setBackgroundColor(Color.argb((int) alpha, 144, 155, 180));

        } else {
            textView.setBackgroundColor(Color.argb((int) 255, 144, 155, 180));

        }

    }
}
