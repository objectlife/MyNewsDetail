//package com.objectlife.mynewsdetail.toutiao;
//
//import android.annotation.SuppressLint;
//import android.annotation.TargetApi;
//import android.content.Context;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Build.VERSION;
//import android.os.SystemClock;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.ViewConfiguration;
//import android.widget.AbsListView;
//import android.widget.AbsListView.OnScrollListener;
//import android.widget.ListView;
//import android.widget.OverScroller;
//import com.bytedance.common.utility.Logger;
//import com.ss.android.article.base.feature.detail.view.s;
//import com.ss.android.article.base.feature.detail.view.t;
//import com.umeng.message.proguard.n;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//
//@TargetApi(9)
//public class MyListViewV9 extends ListView {
//    private static final boolean d = (VERSION.SDK_INT >= 21);
//    int a;
//    int b;
//    s<MyListViewV9> c;
//    private OverScroller e;
//    private Method f;
//    private Method g;
//    private Object h;
//    private long i;
//    private OnScrollListener j = new h(this);
//    private OnScrollListener k;
//    private t l;
//
//    public MyListViewV9(Context context, AttributeSet attributeSet, int i) {
//        super(context, attributeSet, i);
//        a();
//    }
//
//    public MyListViewV9(Context context, AttributeSet attributeSet) {
//        super(context, attributeSet);
//        a();
//    }
//
//    public MyListViewV9(Context context) {
//        super(context);
//        a();
//    }
//
//    private boolean d() {
//        return VERSION.SDK_INT >= 14;
//    }
//
//    @SuppressLint({"NewApi"})
//    protected void a() {
//        super.setOnScrollListener(this.j);
//        if (d()) {
//            setFriction(ViewConfiguration.getScrollFriction());
//            e();
//            try {
//                Field declaredField = AbsListView.class.getDeclaredField("mFlingRunnable");
//                declaredField.setAccessible(true);
//                this.h = declaredField.get(this);
//                Class cls = Class.forName("android.widget.AbsListView$FlingRunnable");
//                this.g = cls.getDeclaredMethod(n.j, new Class[]{Integer.TYPE});
//                this.g.setAccessible(true);
//                declaredField = cls.getDeclaredField("mScroller");
//                declaredField.setAccessible(true);
//                this.e = (OverScroller) declaredField.get(this.h);
//                this.f = AbsListView.class.getDeclaredMethod("reportScrollStateChange", new Class[]{Integer.TYPE});
//                this.f.setAccessible(true);
//            } catch (Throwable th) {
//                th.printStackTrace();
//                this.e = null;
//                this.h = null;
//                this.g = null;
//                this.f = null;
//                if (Logger.debug()) {
//                    Logger.d("MyListViewV9", "init, exception:" + Log.getStackTraceString(th));
//                }
//            }
//        }
//    }
//
//    @SuppressLint({"NewApi"})
//    public boolean a(int i) {
//        boolean z = true;
//        if (!b()) {
//            return false;
//        }
//        if (d) {
//            fling(i);
//        } else if (this.f == null || this.g == null) {
//            z = false;
//        } else {
//            try {
//                this.f.invoke(this, new Object[]{Integer.valueOf(2)});
//                this.g.invoke(this.h, new Object[]{Integer.valueOf(i)});
//            } catch (Throwable th) {
//                th.printStackTrace();
//                if (Logger.debug()) {
//                    Logger.d("MyListViewV9", "tryFling, exception:" + Log.getStackTraceString(th));
//                }
//                z = false;
//            }
//        }
//        return z;
//    }
//
//    public boolean b() {
//        return (!(this.h == null || this.f == null || this.g == null) || d) && getVisibility() == 0;
//    }
//
//    public void setOnOverScrolledListener(s<MyListViewV9> sVar) {
//        this.c = sVar;
//    }
//
//    public void c() {
//        setOverScrollMode(0);
//    }
//
//    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
//        super.onOverScrolled(i, i2, z, z2);
//        if (this.c != null) {
//            this.c.a(this, i2, z2, this.a, this.b);
//        }
//    }
//
//    @SuppressLint({"NewApi"})
//    protected boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
//        int i9;
//        this.a = i2;
//        this.b = i6;
//        if (i2 >= 0 || i4 >= 0) {
//            i9 = i8;
//        } else {
//            i9 = 0;
//        }
//        if (d() && this.c != null && i2 < 0 && getFirstVisiblePosition() == 0 && i4 == 0) {
//            int i10 = 0;
//            if (this.e != null) {
//                i10 = (int) (-this.e.getCurrVelocity());
//                if (Logger.debug()) {
//                    Logger.d("MyListViewV9", "overScrollBy:canUseOverScroller: " + i10);
//                }
//            }
//            if (i10 == 0) {
//                i10 = 0;
//                long uptimeMillis = SystemClock.uptimeMillis() - this.i;
//                if (uptimeMillis > 0) {
//                    i10 = (int) (1000 / uptimeMillis);
//                }
//                if (i10 <= 0) {
//                    i10 = 0;
//                } else if (i10 > 60) {
//                    i10 = 60;
//                }
//                i10 *= i2;
//            }
//            if (i10 != 0) {
//                this.c.a(i10);
//            }
//            if (Logger.debug()) {
//                Logger.d("MyListViewV9", "overScrollBy:" + i2 + "/" + i4 + "/" + i6 + "/" + i10);
//            }
//        }
//        this.i = SystemClock.uptimeMillis();
//        return super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i9, z);
//    }
//
//    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
//        if (getVisibility() != 0) {
//            return false;
//        }
//        return super.onInterceptTouchEvent(motionEvent);
//    }
//
//    public boolean onTouchEvent(MotionEvent motionEvent) {
//        if (getVisibility() != 0) {
//            return false;
//        }
//        return super.onTouchEvent(motionEvent);
//    }
//
//    protected void a(AbsListView absListView, int i, int i2, int i3) {
//        if (this.k != null) {
//            this.k.onScroll(absListView, i, i2, i3);
//        }
//        if (this.l != null) {
//            this.l.a();
//        }
//    }
//
//    public void setOnScrollListener(OnScrollListener onScrollListener) {
//        this.k = onScrollListener;
//    }
//
//    public void setOnScrollBarShowListener(t tVar) {
//        this.l = tVar;
//    }
//
//    public int computeVerticalScrollRange() {
//        return super.computeVerticalScrollRange();
//    }
//
//    public int computeVerticalScrollOffset() {
//        return super.computeVerticalScrollOffset();
//    }
//
//    public int computeVerticalScrollExtent() {
//        return super.computeVerticalScrollExtent();
//    }
//
//    private void e() {
//        try {
//            Field declaredField = Class.forName(AbsListView.class.getName()).getDeclaredField("mEdgeGlowTop");
//            declaredField.setAccessible(true);
//            Object obj = declaredField.get(this);
//            Class cls = Class.forName(obj.getClass().getName());
//            Field declaredField2 = cls.getDeclaredField("mGlow");
//            declaredField2.setAccessible(true);
//            declaredField2.set(obj, new ColorDrawable(0));
//            Field declaredField3 = cls.getDeclaredField("mEdge");
//            declaredField3.setAccessible(true);
//            declaredField3.set(obj, new ColorDrawable(0));
//        } catch (Exception e) {
//        }
//    }
//}