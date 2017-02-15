//package com.objectlife.mynewsdetail.toutiao;
//
//import android.annotation.TargetApi;
//import android.content.Context;
//import android.graphics.Canvas;
//import android.os.SystemClock;
//import android.util.AttributeSet;
//import android.webkit.WebView;
//import com.bytedance.common.b.g;
//import com.bytedance.common.utility.Logger;
//import com.ss.android.article.base.feature.detail.view.s;
//import com.ss.android.article.base.feature.detail.view.t;
//import java.util.Iterator;
//import java.util.LinkedList;
//
//@TargetApi(9)
//public class MyWebViewV9 extends ao {
//    int a;
//    int b;
//    int c;
//    s<MyWebViewV9> d;
//    private LinkedList<b> e = new LinkedList();
//    private a f;
//    private c g;
//    private int h;
//    private boolean i;
//    private t j;
//
//    public interface a {
//        void a(int i, int i2);
//    }
//
//    private static class b {
//        int a;
//        long b;
//
//        public b(int i, long j) {
//            this.a = i;
//            this.b = j;
//        }
//    }
//
//    public interface c {
//        void a(int i);
//    }
//
//    public MyWebViewV9(Context context, AttributeSet attributeSet, int i) {
//        super(context, attributeSet, i);
//    }
//
//    public MyWebViewV9(Context context, AttributeSet attributeSet) {
//        super(context, attributeSet);
//    }
//
//    public MyWebViewV9(Context context) {
//        super(context);
//    }
//
//    protected void onDraw(Canvas canvas) {
//        try {
//            super.onDraw(canvas);
//        } catch (Exception e) {
//        }
//    }
//
//    public void setOnOverScrolledListener(s<MyWebViewV9> sVar) {
//        this.d = sVar;
//    }
//
//    public void setWebViewDrawListener(c cVar) {
//        this.g = cVar;
//    }
//
//    public void a() {
//        setOverScrollMode(0);
//        computeVerticalScrollRange();
//    }
//
//    public int getComputedVerticalScrollRange() {
//        return this.c;
//    }
//
//    public int computeVerticalScrollRange() {
//        this.c = super.computeVerticalScrollRange();
//        return this.c;
//    }
//
//    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
//        super.onOverScrolled(i, i2, z, z2);
//        if (this.d != null) {
//            this.d.a(this, i2, z2, this.a, this.b);
//        }
//    }
//
//    protected boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
//        int i9;
//        if (this.j != null) {
//            this.j.a();
//        }
//        this.a = i2;
//        this.b = i6;
//        if (i2 <= 0 || i4 <= 0) {
//            i9 = i8;
//        } else {
//            i9 = 0;
//        }
//        if (this.e.size() >= 10) {
//            this.e.removeFirst();
//        }
//        if (i2 + i4 < i6) {
//            this.e.add(new b(i2, SystemClock.uptimeMillis()));
//            if (Logger.debug()) {
//                Logger.d("MyWebViewV9", "overScrollBy:" + i2 + "/" + i4 + "/" + i6 + "/" + SystemClock.uptimeMillis());
//            }
//        } else if (!this.e.isEmpty()) {
//            Iterator it = this.e.iterator();
//            int i10 = 0;
//            while (it.hasNext()) {
//                i10 = ((b) it.next()).a + i10;
//            }
//            int i11 = (int) (((b) this.e.getLast()).b - ((b) this.e.getFirst()).b);
//            b();
//            if (i11 > 0 && i10 != 0) {
//                i11 = (i10 / i11) * 1000;
//                if (!(this.d == null || i11 == 0)) {
//                    this.d.a(i11);
//                    if (Logger.debug()) {
//                        Logger.d("MyWebViewV9", "overScrollBy: v = " + i11);
//                    }
//                    if (Logger.debug()) {
//                        Logger.d("MyWebViewV9", "overScrollBy:" + i2 + "/" + i4 + "/" + i6 + "/" + SystemClock.uptimeMillis());
//                    }
//                }
//            }
//        }
//        return super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i9, z);
//    }
//
//    public void b() {
//        this.e.clear();
//    }
//
//    public void draw(Canvas canvas) {
//        super.draw(canvas);
//        if (this.i) {
//            int contentHeight = getContentHeight();
//            if (Math.abs(contentHeight - this.h) >= 10) {
//                int i = contentHeight - this.h;
//                this.h = contentHeight;
//                a(i);
//            }
//        }
//        if (this.g != null) {
//            this.g.a(getScaledContentHeight());
//        }
//    }
//
//    public void setContentSizeChangeListener(a aVar) {
//        this.f = aVar;
//    }
//
//    private void a(int i) {
//        if (this.f != null) {
//            this.f.a(this.h, i);
//        }
//    }
//
//    public void setDetectContentSize(boolean z) {
//        if (this.i != z) {
//            this.i = z;
//            if (z) {
//                this.h = getContentHeight();
//            }
//        }
//    }
//
//    public void setOnScrollBarShowListener(t tVar) {
//        this.j = tVar;
//    }
//
//    public int getScaledContentHeight() {
//        return (int) (g.a((WebView) this) * ((float) getContentHeight()));
//    }
//
//    public int computeVerticalScrollOffset() {
//        return super.computeVerticalScrollOffset();
//    }
//
//    public int computeVerticalScrollExtent() {
//        return super.computeVerticalScrollExtent();
//    }
//}