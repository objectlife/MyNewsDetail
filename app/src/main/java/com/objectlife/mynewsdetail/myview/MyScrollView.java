package com.objectlife.mynewsdetail.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.objectlife.mynewsdetail.MyAdapter;
import com.objectlife.mynewsdetail.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by objectlife on 2/13/17.
 */

public class MyScrollView extends ScrollView {

    private LinearLayout mRootLayout;
    private MyWebView mWebView;
    private MyListView mListView;

    private View mHeaderLayout;

    private float fLastRawY, fMoveRawY;

    private int mHeaderHeight = 0;

    public MyScrollView(Context context) {
        super(context);
        initChildView(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initChildView(context);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initChildView(context);
    }

    private void initChildView(Context context) {

        mRootLayout = new LinearLayout(context);
        mRootLayout.setOrientation(LinearLayout.VERTICAL);
        mRootLayout.setLayoutParams(new ScrollView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        mHeaderLayout = LayoutInflater.from(context).inflate(R.layout.layout_header, null);
        mWebView = (MyWebView) mHeaderLayout.findViewById(R.id.my_webview);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.loadUrl("http://www.csdn.net/");
        mRootLayout.addView(mHeaderLayout);

        mListView = new MyListView(context);
        mListView.setVerticalScrollBarEnabled(false);
        mListView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, context.getResources().getDisplayMetrics().heightPixels));
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("index>>" + i);
        }
        mListView.setAdapter(new MyAdapter(datas));

        mRootLayout.addView(mListView);
        addView(mRootLayout);
        setVerticalScrollBarEnabled(true);


        mListView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        MyScrollView.this.requestDisallowInterceptTouchEvent(true);
                        fLastRawY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        fMoveRawY = event.getRawY();
                        float moveDis = fMoveRawY - fLastRawY;
                        if (moveDis > 0) { //向下滑
                            final int position = mListView.getFirstVisiblePosition();
                            if (position == 0 && mListView.getChildAt(position).getTop() == 0) { // 滑动到顶部了
                                MyScrollView.this.requestDisallowInterceptTouchEvent(false);
                            }
                        } else { // 向上滑
                            MyScrollView.this.requestDisallowInterceptTouchEvent(MyScrollView.this.getScrollY() > mHeaderHeight);
                        }
                        fLastRawY = fMoveRawY;
                        break;
                    default:
                        MyScrollView.this.requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });

        mHeaderLayout.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                final int height = bottom - top;
                if (mHeaderHeight < height) {
                    mHeaderHeight = height;
                }
                Log.e("mHeaderLayout", "onLayoutChange: 高度>>" + mHeaderHeight);
            }
        });
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
