//package com.objectlife.mynewsdetail.toutiao;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.os.Build.VERSION;
//import android.support.v4.view.ViewCompat;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.view.VelocityTracker;
//import android.view.View;
//import android.view.View.MeasureSpec;
//import android.view.ViewConfiguration;
//import android.view.ViewGroup;
//import android.view.ViewGroup.LayoutParams;
//import android.view.ViewGroup.MarginLayoutParams;
//import android.view.ViewParent;
//import android.view.animation.Interpolator;
//import android.webkit.WebView;
//import android.widget.ListView;
//import android.widget.RelativeLayout;
//import android.widget.Scroller;
//import com.bytedance.common.utility.Logger;
//import com.bytedance.common.utility.j;
//import com.networkbench.agent.impl.NBSAppAgent;
//import com.ss.android.article.base.feature.detail.view.t;
//import com.ss.android.article.base.ui.MyWebViewV9;
//import com.ss.android.article.news.R;
//import com.ss.android.common.lib.MobClickCombiner;
//import com.ss.android.common.util.LoadUrlUtils;
//import com.xiaomi.mipush.sdk.MiPushClient;
//
//public class DetailScrollView extends ViewGroup {
//    private static final Interpolator o = new a();
//    private boolean A = false;
//    private boolean B = false;
//    private int C;
//    private int D;
//    private int E;
//    private boolean F = false;
//    private boolean G = false;
//    private boolean H = false;
//    private boolean I;
//    private boolean J;
//    private int K;
//    private float L = NBSAppAgent.DEFAULT_LOCATION_UPDATE_DISTANCE_IN_METERS;
//    Scroller a;
//    WebView b;
//    MyWebViewV9 c;
//    ListView d;
//    RelativeLayout e;
//    View f;
//    View g;
//    View h;
//    public boolean i = true;
//    a j;
//    int k;
//    int l;
//    boolean m = false;
//    Runnable n = new e(this);
//    private float p;
//    private int q = 1;
//    private boolean r = false;
//    private int s;
//    private boolean t = false;
//    private int u = 300;
//    private int v;
//    private int w;
//    private int x;
//    private VelocityTracker y;
//    private int z = -1;
//
//    public interface a {
//        void a();
//
//        void a(int i);
//
//        void a(boolean z);
//    }
//
//    public DetailScrollView(Context context, AttributeSet attributeSet, int i) {
//        super(context, attributeSet, i);
//        a(context);
//    }
//
//    public DetailScrollView(Context context, AttributeSet attributeSet) {
//        super(context, attributeSet);
//        a(context);
//    }
//
//    public DetailScrollView(Context context) {
//        super(context);
//        a(context);
//    }
//
//    private void a(Context context) {
//        setMotionEventSplittingEnabled(false);
//        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
//        this.C = viewConfiguration.getScaledTouchSlop();
//        this.E = viewConfiguration.getScaledMaximumFlingVelocity();
//        this.D = viewConfiguration.getScaledMinimumFlingVelocity();
//        this.k = (int) j.b(context, 80.0f);
//        this.l = (int) j.b(context, 3.0f);
//        this.p = 300.0f / j.b(context, 420.0f);
//    }
//
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//        this.a = new Scroller(getContext());
//        this.b = (WebView) findViewById(R.id.top_webview);
//        this.d = (ListView) findViewById(R.id.bottom_listview);
//        this.e = (RelativeLayout) findViewById(R.id.bottom_listview_layout);
//        this.f = findViewById(R.id.night_mode_overlay);
//        if (VERSION.SDK_INT >= 9) {
//            if (this.b instanceof MyWebViewV9) {
//                this.c = (MyWebViewV9) this.b;
//                this.c.setOnOverScrolledListener(new b(this));
//                this.c.a();
//                this.c.setContentSizeChangeListener(new c(this));
//            }
//            if (this.d instanceof MyListViewV9) {
//                ((MyListViewV9) this.d).setOnOverScrolledListener(new d(this));
//                ((MyListViewV9) this.d).c();
//            }
//        }
//        setLayoutType(this.q);
//    }
//
//    void a(View view, int i, boolean z, int i2, int i3) {
//        if (this.m && !this.H && !this.G && VERSION.SDK_INT >= 9 && this.a.isFinished()) {
//            if (view == this.b) {
//                if (getScrollY() == 0 && view.getScrollY() >= 0 && z && i2 > 0) {
//                    c(false);
//                    this.i = true;
//                }
//                this.w = i3;
//            } else if (view != this.d || getScrollY() != getWebViewHeight()) {
//            } else {
//                if (i < 0) {
//                    this.i = false;
//                    c(true);
//                } else if (view.getScrollY() == 0 && z && i2 < 0) {
//                    c(true);
//                    this.i = false;
//                }
//            }
//        }
//    }
//
//    private void a(String str) {
//        MobClickCombiner.onEvent(getContext(), "detail", str);
//    }
//
//    public void a(View view, View view2) {
//        this.g = view;
//        this.h = view2;
//    }
//
//    public void setShowBottomViewOnFirstLayout(boolean z) {
//        this.F = z;
//    }
//
//    public void setLayoutType(int i) {
//        this.q = i;
//        if (i != 1) {
//            setVerticalScrollBarEnabled(false);
//        }
//    }
//
//    public void setDisallowIntercept(boolean z) {
//        this.r = z;
//        if (z) {
//            setVerticalScrollBarEnabled(false);
//        }
//    }
//
//    public void setDisableInfoLayer(boolean z) {
//        this.G = z;
//        if (this.G) {
//            setVerticalScrollBarEnabled(false);
//        }
//    }
//
//    public void setDisableScrollOver(boolean z) {
//        this.H = z;
//        if (this.H) {
//            setVerticalScrollBarEnabled(false);
//        }
//    }
//
//    public boolean a() {
//        return a(true);
//    }
//
//    boolean a(boolean z) {
//        int scrollY = getScrollY();
//        boolean z2 = scrollY < getWebViewHeight() / 2;
//        scrollY = z2 ? getWebViewHeight() - scrollY : -scrollY;
//        a(z2 ? "handle_open_drawer" : "handle_close_drawer");
//        a(z, z2, scrollY);
//        return z2;
//    }
//
//    private void a(boolean z, boolean z2, int i) {
//        boolean z3 = false;
//        if (this.q != 2) {
//            this.a.abortAnimation();
//            b(z2);
//            if (!(z2 || this.d == null || this.d.getVisibility() != 0)) {
//                this.d.setSelection(0);
//            }
//            if (z) {
//                a(i);
//            } else {
//                scrollBy(0, i);
//            }
//            if (!z2) {
//                z3 = true;
//            }
//            this.i = z3;
//            if (this.j != null) {
//                this.j.a(z2);
//            }
//        }
//    }
//
//    public void b(boolean z) {
//        if (this.b != null && this.b.getVisibility() == 0) {
//            int contentHeight;
//            Object obj = this.w > 0 ? 1 : null;
//            if (z) {
//                contentHeight = obj != null ? this.w : (int) (((float) this.b.getContentHeight()) * this.b.getScale());
//                this.x = this.b.getScrollY();
//            } else {
//                contentHeight = this.x;
//            }
//            if (obj == null && this.b.getSettings().getJavaScriptEnabled()) {
//                StringBuilder stringBuilder = new StringBuilder("javascript:window.scrollTo(");
//                stringBuilder.append(this.b.getScrollX());
//                stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
//                stringBuilder.append(contentHeight);
//                stringBuilder.append(");");
//                LoadUrlUtils.loadUrl(this.b, stringBuilder.toString());
//            } else {
//                this.b.scrollTo(this.b.getScrollX(), contentHeight);
//            }
//            if (this.b instanceof MyWebViewV9) {
//                ((MyWebViewV9) this.b).b();
//            }
//        }
//    }
//
//    public void b() {
//        if (getVisibility() == 0) {
//            int scrollY = getScrollY();
//            if (scrollY > 20) {
//                a(true, false, -scrollY);
//            }
//        }
//    }
//
//    public void c() {
//        if (getVisibility() == 0) {
//            int scrollY = getScrollY();
//            a("handle_open_drawer");
//            a(true, true, getWebViewHeight() - scrollY);
//        }
//    }
//
//    public void a(int i) {
//        int i2 = 200;
//        int scrollX = getScrollX();
//        int scrollY = getScrollY();
//        int abs = (int) (((float) Math.abs(i)) * this.p);
//        if (abs >= 200) {
//            if (abs > 350) {
//                i2 = 350;
//            } else {
//                i2 = abs;
//            }
//        }
//        if (this.H) {
//            abs = 0;
//        } else {
//            abs = i2;
//        }
//        this.a.startScroll(scrollX, scrollY, 0, i, abs);
//        ViewCompat.postInvalidateOnAnimation(this);
//        h();
//    }
//
//    public void setMyOnChangedListener(a aVar) {
//        this.j = aVar;
//    }
//
//    public void onScrollChanged(int i, int i2, int i3, int i4) {
//        super.onScrollChanged(i, i2, i3, i4);
//        if (this.j != null) {
//            this.j.a(i2);
//        }
//    }
//
//    private int getCappedCurVelocity() {
//        return Math.min((int) this.a.getCurrVelocity(), this.E);
//    }
//
//    public void computeScroll() {
//        int cappedCurVelocity;
//        if (!this.a.isFinished() && this.a.computeScrollOffset()) {
//            int webViewHeight = getWebViewHeight();
//            int scrollX = getScrollX();
//            int scrollY = getScrollY();
//            int currX = this.a.getCurrX();
//            int currY = this.a.getCurrY();
//            if (currY <= scrollY || scrollY < webViewHeight || !(this.d instanceof MyListViewV9)) {
//                if (currY < scrollY && scrollY <= 0 && this.b.getVisibility() == 0) {
//                    cappedCurVelocity = getCappedCurVelocity();
//                    if (cappedCurVelocity != 0) {
//                        this.a.forceFinished(true);
//                        this.b.flingScroll(0, -cappedCurVelocity);
//                        if (Logger.debug()) {
//                            Logger.d("DetailScrollView", "computeScroll, abort fling, WebView start fling, velocity = " + (-cappedCurVelocity) + ", scrollY = " + scrollY);
//                            return;
//                        }
//                        return;
//                    }
//                }
//            } else if (this.d.getVisibility() == 0) {
//                int cappedCurVelocity2 = getCappedCurVelocity();
//                if (cappedCurVelocity2 != 0 && ((MyListViewV9) this.d).a(cappedCurVelocity2)) {
//                    this.a.forceFinished(true);
//                    if (Logger.debug()) {
//                        Logger.d("DetailScrollView", "computeScroll, abort fling, ListView start fling, velocity = " + cappedCurVelocity2 + ", scrollY = " + scrollY);
//                        return;
//                    }
//                    return;
//                }
//            }
//            cappedCurVelocity = Math.max(0, Math.min(currY, webViewHeight));
//            if (!(scrollX == currX && scrollY == cappedCurVelocity)) {
//                scrollTo(currX, cappedCurVelocity);
//                if (cappedCurVelocity == webViewHeight) {
//                    this.i = false;
//                }
//            }
//            ViewCompat.postInvalidateOnAnimation(this);
//            if (this.I && this.c != null) {
//                this.c.setDetectContentSize(false);
//            }
//        } else if (this.I && this.c != null) {
//            cappedCurVelocity = getScrollY();
//            MyWebViewV9 myWebViewV9 = this.c;
//            boolean z = this.q == 1 && this.c.getVisibility() == 0 && cappedCurVelocity > 0 && cappedCurVelocity < getWebViewHeight();
//            myWebViewV9.setDetectContentSize(z);
//        }
//    }
//
//    private int getScrollRange() {
//        return getWebViewHeight() * 2;
//    }
//
//    private void e() {
//        if (this.y == null) {
//            this.y = VelocityTracker.obtain();
//        } else {
//            this.y.clear();
//        }
//    }
//
//    private void f() {
//        if (this.y == null) {
//            this.y = VelocityTracker.obtain();
//        }
//    }
//
//    private void g() {
//        if (this.y != null) {
//            this.y.recycle();
//            this.y = null;
//        }
//    }
//
//    private void c(boolean z) {
//        if (!this.A && this.m && this.z != -1) {
//            if (z) {
//                scrollTo(0, getWebViewHeight() - this.l);
//            } else {
//                scrollTo(0, this.l);
//            }
//            Logger.d("DetailScrollView", "startDragAsClampedY:" + z);
//            this.B = true;
//        }
//    }
//
//    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
//        boolean dispatchTouchEvent;
//        if (motionEvent.getActionMasked() == 0) {
//            this.K = 0;
//        }
//        try {
//            dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
//        } catch (Exception e) {
//            e.printStackTrace();
//            dispatchTouchEvent = false;
//        }
//        switch (motionEvent.getAction()) {
//            case 0:
//                this.m = true;
//                break;
//            case 1:
//            case 3:
//                this.m = false;
//                break;
//        }
//        return dispatchTouchEvent;
//    }
//
//    public void requestDisallowInterceptTouchEvent(boolean z) {
//    }
//
//    private void a(MotionEvent motionEvent) {
//    }
//
//    /* JADX WARNING: inconsistent code. */
//    /* Code decompiled incorrectly, please refer to instructions dump. */
//    public boolean onInterceptTouchEvent(MotionEvent r8) {
//        /*
//        r7 = this;
//        r4 = -1;
//        r0 = 1;
//        r1 = 0;
//        r2 = r7.H;
//        if (r2 != 0) goto L_0x0015;
//    L_0x0007:
//        r2 = r7.G;
//        if (r2 != 0) goto L_0x0015;
//    L_0x000b:
//        r2 = r7.r;
//        if (r2 != 0) goto L_0x0015;
//    L_0x000f:
//        r2 = android.os.Build.VERSION.SDK_INT;
//        r3 = 9;
//        if (r2 >= r3) goto L_0x0017;
//    L_0x0015:
//        r0 = r1;
//    L_0x0016:
//        return r0;
//    L_0x0017:
//        r7.a(r8);
//        r2 = r8.getActionMasked();
//        r3 = 2;
//        if (r2 != r3) goto L_0x0025;
//    L_0x0021:
//        r2 = r7.A;
//        if (r2 != 0) goto L_0x0016;
//    L_0x0025:
//        r2 = r8.getActionMasked();
//        switch(r2) {
//            case 0: goto L_0x0125;
//            case 1: goto L_0x014a;
//            case 2: goto L_0x002f;
//            case 3: goto L_0x014a;
//            case 4: goto L_0x002c;
//            case 5: goto L_0x0155;
//            case 6: goto L_0x0168;
//            default: goto L_0x002c;
//        };
//    L_0x002c:
//        r0 = r7.A;
//        goto L_0x0016;
//    L_0x002f:
//        r2 = r7.z;
//        if (r2 == r4) goto L_0x002c;
//    L_0x0033:
//        r3 = r8.findPointerIndex(r2);
//        if (r3 != r4) goto L_0x0058;
//    L_0x0039:
//        r0 = "DetailScrollView";
//        r1 = new java.lang.StringBuilder;
//        r1.<init>();
//        r3 = "Invalid pointerId=";
//        r1 = r1.append(r3);
//        r1 = r1.append(r2);
//        r2 = " in onInterceptTouchEvent";
//        r1 = r1.append(r2);
//        r1 = r1.toString();
//        com.bytedance.common.utility.Logger.w(r0, r1);
//        goto L_0x002c;
//    L_0x0058:
//        r2 = r8.getX(r3);
//        r2 = (int) r2;
//        r2 = r8.getY(r3);
//        r3 = (int) r2;
//        r2 = r7.v;
//        r2 = r3 - r2;
//        r4 = java.lang.Math.abs(r2);
//        r2 = r7.A;
//        if (r2 != 0) goto L_0x00e9;
//    L_0x006e:
//        r2 = r7.a;
//        r2 = r2.isFinished();
//        if (r2 == 0) goto L_0x00e9;
//    L_0x0076:
//        r5 = r7.getScrollY();
//        r2 = r7.b;
//        r2 = r2.getBottom();
//        r2 = r2 - r5;
//        if (r3 <= r2) goto L_0x008b;
//    L_0x0083:
//        r2 = r0;
//    L_0x0084:
//        if (r5 != 0) goto L_0x008d;
//    L_0x0086:
//        if (r2 != 0) goto L_0x008d;
//    L_0x0088:
//        r7.v = r3;
//        goto L_0x002c;
//    L_0x008b:
//        r2 = r1;
//        goto L_0x0084;
//    L_0x008d:
//        r2 = r7.getWebViewHeight();
//        if (r5 != r2) goto L_0x00e9;
//    L_0x0093:
//        r2 = r7.C;
//        if (r4 <= r2) goto L_0x00e5;
//    L_0x0097:
//        r2 = r7.v;
//        if (r3 <= r2) goto L_0x00e5;
//    L_0x009b:
//        r2 = r7.d;
//        r2 = r2.getScrollY();
//        if (r2 != 0) goto L_0x00e5;
//    L_0x00a3:
//        r2 = r7.g;
//        if (r2 == 0) goto L_0x00e5;
//    L_0x00a7:
//        r2 = r7.g;
//        r2 = r2.getTop();
//        if (r2 != 0) goto L_0x00e5;
//    L_0x00af:
//        r2 = r7.g;
//        r2 = r2.getParent();
//        r5 = r7.d;
//        if (r2 != r5) goto L_0x00e5;
//    L_0x00b9:
//        r7.B = r0;
//        r2 = com.bytedance.common.utility.Logger.debug();
//        if (r2 == 0) goto L_0x00e5;
//    L_0x00c1:
//        r2 = "DetailScrollView";
//        r5 = new java.lang.StringBuilder;
//        r5.<init>();
//        r6 = "force pendingDrag ";
//        r5 = r5.append(r6);
//        r5 = r5.append(r3);
//        r6 = " ";
//        r5 = r5.append(r6);
//        r6 = r7.v;
//        r5 = r5.append(r6);
//        r5 = r5.toString();
//        com.bytedance.common.utility.Logger.v(r2, r5);
//    L_0x00e5:
//        r2 = r7.B;
//        if (r2 == 0) goto L_0x002c;
//    L_0x00e9:
//        r2 = r7.A;
//        if (r2 != 0) goto L_0x0108;
//    L_0x00ed:
//        r2 = r7.B;
//        if (r2 == 0) goto L_0x0108;
//    L_0x00f1:
//        r7.e();
//        r1 = r7.y;
//        r1.addMovement(r8);
//        r7.A = r0;
//        r7.v = r3;
//        r1 = r7.getParent();
//        if (r1 == 0) goto L_0x002c;
//    L_0x0103:
//        r1.requestDisallowInterceptTouchEvent(r0);
//        goto L_0x002c;
//    L_0x0108:
//        r7.B = r1;
//        r1 = r7.C;
//        if (r4 <= r1) goto L_0x002c;
//    L_0x010e:
//        r7.A = r0;
//        r7.v = r3;
//        r7.f();
//        r1 = r7.y;
//        r1.addMovement(r8);
//        r1 = r7.getParent();
//        if (r1 == 0) goto L_0x002c;
//    L_0x0120:
//        r1.requestDisallowInterceptTouchEvent(r0);
//        goto L_0x002c;
//    L_0x0125:
//        r7.B = r1;
//        r2 = r8.getY();
//        r2 = (int) r2;
//        r7.v = r2;
//        r2 = r8.getPointerId(r1);
//        r7.z = r2;
//        r7.e();
//        r2 = r7.y;
//        r2.addMovement(r8);
//        r2 = r7.a;
//        r2 = r2.isFinished();
//        if (r2 != 0) goto L_0x0148;
//    L_0x0144:
//        r7.A = r0;
//        goto L_0x002c;
//    L_0x0148:
//        r0 = r1;
//        goto L_0x0144;
//    L_0x014a:
//        r7.B = r1;
//        r7.A = r1;
//        r7.z = r4;
//        r7.g();
//        goto L_0x002c;
//    L_0x0155:
//        r0 = r8.getActionIndex();
//        r1 = r8.getY(r0);
//        r1 = (int) r1;
//        r7.v = r1;
//        r0 = r8.getPointerId(r0);
//        r7.z = r0;
//        goto L_0x002c;
//    L_0x0168:
//        r7.b(r8);
//        r0 = r7.z;
//        r0 = r8.findPointerIndex(r0);
//        r0 = r8.getY(r0);
//        r0 = (int) r0;
//        r7.v = r0;
//        goto L_0x002c;
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.detail.feature.detail.view.DetailScrollView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
//    }
//
//    @SuppressLint({"ClickableViewAccessibility"})
//    public boolean onTouchEvent(MotionEvent motionEvent) {
//        boolean z = false;
//        if (this.H || this.G || VERSION.SDK_INT < 9) {
//            return false;
//        }
//        a(motionEvent);
//        f();
//        this.y.addMovement(motionEvent);
//        int scrollY;
//        boolean z2;
//        int yVelocity;
//        int webViewHeight;
//        switch (motionEvent.getActionMasked()) {
//            case 0:
//                this.B = false;
//                if (getChildCount() != 0) {
//                    if (this.a.isFinished()) {
//                        scrollY = getScrollY();
//                        if (scrollY == 0 || scrollY == getWebViewHeight()) {
//                            this.A = false;
//                            return false;
//                        }
//                    }
//                    z2 = !this.a.isFinished();
//                    this.A = z2;
//                    if (z2) {
//                        ViewParent parent = getParent();
//                        if (parent != null) {
//                            parent.requestDisallowInterceptTouchEvent(true);
//                        }
//                    }
//                    if (!this.a.isFinished()) {
//                        this.a.abortAnimation();
//                    }
//                    this.v = (int) motionEvent.getY();
//                    this.z = motionEvent.getPointerId(0);
//                    break;
//                }
//                return false;
//            case 1:
//                this.B = false;
//                if (this.A) {
//                    VelocityTracker velocityTracker = this.y;
//                    velocityTracker.computeCurrentVelocity(1000, (float) this.E);
//                    yVelocity = (int) velocityTracker.getYVelocity(this.z);
//                    if (getChildCount() > 0) {
//                        scrollY = getScrollY();
//                        webViewHeight = getWebViewHeight();
//                        if (scrollY == 0) {
//                            scrollY = this.b.getScrollY();
//                            if (yVelocity > 0) {
//                                if (scrollY + webViewHeight < this.c.getComputedVerticalScrollRange()) {
//                                    this.b.flingScroll(0, -yVelocity);
//                                    z = true;
//                                }
//                                z2 = z;
//                            } else if (scrollY > 0) {
//                                this.b.flingScroll(0, -yVelocity);
//                                z2 = true;
//                            }
//                            if (!(z2 || this.K == 0 || !(this.d instanceof MyListViewV9))) {
//                                ((MyListViewV9) this.d).a(-yVelocity);
//                                z2 = true;
//                            }
//                            if (!z2) {
//                                b(-yVelocity);
//                            }
//                        }
//                        z2 = false;
//                        ((MyListViewV9) this.d).a(-yVelocity);
//                        z2 = true;
//                        if (z2) {
//                            b(-yVelocity);
//                        }
//                        break;
//                    }
//                    this.z = -1;
//                    i();
//                    break;
//                }
//                break;
//            case 2:
//                scrollY = motionEvent.findPointerIndex(this.z);
//                if (scrollY == -1) {
//                    Logger.w("DetailScrollView", "Invalid pointerId=" + this.z + " in onTouchEvent");
//                    break;
//                }
//                yVelocity = (int) motionEvent.getY(scrollY);
//                scrollY = this.v - yVelocity;
//                if (!this.A && Math.abs(scrollY) > this.C) {
//                    ViewParent parent2 = getParent();
//                    if (parent2 != null) {
//                        parent2.requestDisallowInterceptTouchEvent(true);
//                    }
//                    this.A = true;
//                    scrollY = scrollY > 0 ? scrollY - this.C : scrollY + this.C;
//                }
//                if (this.A) {
//                    this.B = false;
//                    this.v = yVelocity;
//                    webViewHeight = getScrollY();
//                    yVelocity = getScrollRange();
//                    int i = webViewHeight + scrollY;
//                    if ((scrollY > 0 && i >= yVelocity) || (scrollY < 0 && i <= 0)) {
//                        this.y.clear();
//                    }
//                    int webViewHeight2 = getWebViewHeight();
//                    int scrollY2 = this.b.getScrollY();
//                    if (scrollY < 0) {
//                        if (this.K < 0) {
//                            if (scrollY >= this.K) {
//                                this.K -= scrollY;
//                                this.d.setSelectionFromTop(0, this.K);
//                                break;
//                            }
//                            scrollY -= this.K;
//                            this.d.setSelectionFromTop(0, 0);
//                            this.K = 0;
//                        }
//                        yVelocity = scrollY;
//                        if (i < 0) {
//                            yVelocity = 0 - webViewHeight;
//                        }
//                        if (yVelocity != 0) {
//                            scrollBy(0, yVelocity);
//                        }
//                        if (yVelocity == 0 && scrollY < 0 && webViewHeight == 0 && scrollY2 > 0) {
//                            if (scrollY < (-scrollY2)) {
//                                scrollY = -scrollY2;
//                            }
//                            this.b.scrollBy(0, scrollY);
//                            break;
//                        }
//                    } else if (scrollY > 0) {
//                        boolean z3;
//                        if (webViewHeight == 0 && this.c != null) {
//                            yVelocity = this.c.getComputedVerticalScrollRange();
//                            if (scrollY2 + webViewHeight2 < yVelocity) {
//                                if ((scrollY + scrollY2) + webViewHeight2 > yVelocity) {
//                                    yVelocity = (yVelocity - scrollY2) - webViewHeight2;
//                                } else {
//                                    yVelocity = scrollY;
//                                }
//                                this.b.scrollBy(0, yVelocity);
//                                z3 = true;
//                                if (!z3) {
//                                    if (i > webViewHeight2) {
//                                        scrollY = webViewHeight2 - webViewHeight;
//                                        this.K -= i - webViewHeight2;
//                                        this.d.setSelectionFromTop(0, this.K);
//                                    }
//                                    if (scrollY != 0) {
//                                        scrollBy(0, scrollY);
//                                    }
//                                    if (scrollY + webViewHeight == getWebViewHeight()) {
//                                        this.i = false;
//                                        break;
//                                    }
//                                }
//                            }
//                        }
//                        z3 = false;
//                        if (z3) {
//                            if (i > webViewHeight2) {
//                                scrollY = webViewHeight2 - webViewHeight;
//                                this.K -= i - webViewHeight2;
//                                this.d.setSelectionFromTop(0, this.K);
//                            }
//                            if (scrollY != 0) {
//                                scrollBy(0, scrollY);
//                            }
//                            if (scrollY + webViewHeight == getWebViewHeight()) {
//                                this.i = false;
//                            }
//                        }
//                    }
//                }
//                break;
//            case 3:
//                this.B = false;
//                if (this.A) {
//                    this.z = -1;
//                    i();
//                    break;
//                }
//                break;
//            case 5:
//                scrollY = motionEvent.getActionIndex();
//                this.v = (int) motionEvent.getY(scrollY);
//                this.z = motionEvent.getPointerId(scrollY);
//                break;
//            case 6:
//                b(motionEvent);
//                this.v = (int) motionEvent.getY(motionEvent.findPointerIndex(this.z));
//                break;
//        }
//        return true;
//    }
//
//    private void h() {
//        this.t = true;
//        this.s = getScrollY();
//        postDelayed(this.n, (long) this.u);
//    }
//
//    private void b(MotionEvent motionEvent) {
//        int actionIndex = motionEvent.getActionIndex();
//        if (motionEvent.getPointerId(actionIndex) == this.z) {
//            actionIndex = actionIndex == 0 ? 1 : 0;
//            this.v = (int) motionEvent.getY(actionIndex);
//            this.z = motionEvent.getPointerId(actionIndex);
//            if (this.y != null) {
//                this.y.clear();
//            }
//        }
//    }
//
//    protected void b(int i) {
//        if (getChildCount() > 0 && !this.H && !this.G) {
//            int min;
//            int i2;
//            int scrollX = getScrollX();
//            int scrollY = getScrollY();
//            if (i > 0) {
//                min = Math.min(i, this.E);
//            } else {
//                min = Math.max(i, -this.E);
//            }
//            int max = ((this.d instanceof MyListViewV9) && ((MyListViewV9) this.d).b()) ? Integer.MAX_VALUE : Math.max(0, getWebViewHeight());
//            if (this.b.getVisibility() == 0) {
//                i2 = -((int) (((float) this.b.getContentHeight()) * this.b.getScale()));
//            } else {
//                i2 = 0;
//            }
//            this.a.fling(scrollX, scrollY, 0, min, 0, 0, i2, max);
//            ViewCompat.postInvalidateOnAnimation(this);
//            h();
//            Logger.d("DetailScrollView", "start fling, velocityY = " + min);
//        }
//    }
//
//    private void i() {
//        this.A = false;
//        g();
//    }
//
//    private boolean a(View view) {
//        return view != null && (view == this.b || view == this.e || view == this.f);
//    }
//
//    protected void onMeasure(int i, int i2) {
//        int i3 = 0;
//        int size = MeasureSpec.getSize(i);
//        int mode = MeasureSpec.getMode(i);
//        int size2 = MeasureSpec.getSize(i2);
//        int mode2 = MeasureSpec.getMode(i2);
//        if (mode == 0 || mode2 == 0) {
//            size2 = 0;
//        } else {
//            mode = MeasureSpec.makeMeasureSpec(size, 1073741824);
//            mode2 = MeasureSpec.makeMeasureSpec(size2, 1073741824);
//            int childCount = getChildCount();
//            while (i3 < childCount) {
//                View childAt = getChildAt(i3);
//                if (a(childAt)) {
//                    childAt.measure(mode, mode2);
//                }
//                i3++;
//            }
//            i3 = size;
//        }
//        setMeasuredDimension(i3, size2);
//    }
//
//    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
//        int childCount = getChildCount();
//        int i5 = i3 - i;
//        int i6 = i4 - i2;
//        for (int i7 = 0; i7 < childCount; i7++) {
//            View childAt = getChildAt(i7);
//            if (childAt == this.b || childAt == this.f) {
//                childAt.layout(0, 0, i5, c(i6));
//            } else if (childAt != this.e) {
//                childAt.layout(0, 0, 0, 0);
//            } else if (this.q == 2) {
//                childAt.layout(0, 0, i5, i6);
//            } else {
//                childAt.layout(0, c(i6), i5, c(i6) + i6);
//            }
//        }
//        if (this.F) {
//            this.F = false;
//            post(new f(this));
//        }
//    }
//
//    private int getWebViewHeight() {
//        return c(getHeight());
//    }
//
//    private int c(int i) {
//        if (this.b == null) {
//            return i;
//        }
//        int i2 = this.b.getLayoutParams().height;
//        if (i2 > 0) {
//            return i2;
//        }
//        return i;
//    }
//
//    protected boolean checkLayoutParams(LayoutParams layoutParams) {
//        return layoutParams instanceof MarginLayoutParams;
//    }
//
//    protected LayoutParams generateDefaultLayoutParams() {
//        return new MarginLayoutParams(-1, -1);
//    }
//
//    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
//        return new MarginLayoutParams(getContext(), attributeSet);
//    }
//
//    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
//        return new MarginLayoutParams(layoutParams.width, layoutParams.height);
//    }
//
//    public boolean d() {
//        return this.t;
//    }
//
//    public void setEnableDetectContentSizeChange(boolean z) {
//        this.I = z;
//        if (!z && this.c != null) {
//            this.c.setDetectContentSize(false);
//        }
//    }
//
//    public void setVerticalScrollBarEnabled(boolean z) {
//        boolean z2 = false;
//        int i = 1;
//        if (this.r || this.G || this.H || this.q != 1) {
//            z = false;
//        }
//        if (this.J != z) {
//            t gVar;
//            super.setVerticalScrollBarEnabled(z);
//            boolean isVerticalScrollBarEnabled = isVerticalScrollBarEnabled();
//            setWillNotDraw(!isVerticalScrollBarEnabled);
//            if (isVerticalScrollBarEnabled) {
//                gVar = new g(this);
//            } else {
//                gVar = null;
//            }
//            if (this.c != null) {
//                this.c.setVerticalScrollBarEnabled(!isVerticalScrollBarEnabled);
//                this.c.setOnScrollBarShowListener(gVar);
//            }
//            if (this.d instanceof MyListViewV9) {
//                ListView listView = this.d;
//                if (!isVerticalScrollBarEnabled) {
//                    z2 = true;
//                }
//                listView.setVerticalScrollBarEnabled(z2);
//                ((MyListViewV9) this.d).setOnScrollBarShowListener(gVar);
//            }
//            this.J = isVerticalScrollBarEnabled;
//            if (isVerticalScrollBarEnabled && this.d != null) {
//                if (this.d.isSmoothScrollbarEnabled()) {
//                    i = 100;
//                }
//                this.L = Math.max(NBSAppAgent.DEFAULT_LOCATION_UPDATE_DISTANCE_IN_METERS, j.b(getContext(), 100.0f) / ((float) i));
//            }
//        }
//    }
//
//    protected int computeVerticalScrollExtent() {
//        if (!this.J || this.c == null) {
//            return super.computeVerticalScrollExtent();
//        }
//        int computeVerticalScrollExtent = this.c.computeVerticalScrollExtent();
//        if (!(this.d instanceof MyListViewV9)) {
//            return computeVerticalScrollExtent;
//        }
//        return (int) ((((float) ((MyListViewV9) this.d).computeVerticalScrollExtent()) * this.L) + ((float) computeVerticalScrollExtent));
//    }
//
//    protected int computeVerticalScrollOffset() {
//        if (!this.J || this.c == null) {
//            return super.computeVerticalScrollOffset();
//        }
//        int computeVerticalScrollOffset = this.c.computeVerticalScrollOffset();
//        if (getScrollY() <= 0 || !(this.d instanceof MyListViewV9)) {
//            return computeVerticalScrollOffset;
//        }
//        return (int) ((((float) ((MyListViewV9) this.d).computeVerticalScrollOffset()) * this.L) + ((float) computeVerticalScrollOffset));
//    }
//
//    protected int computeVerticalScrollRange() {
//        if (!this.J || this.c == null) {
//            return super.computeVerticalScrollRange();
//        }
//        int computedVerticalScrollRange = this.c.getComputedVerticalScrollRange();
//        if (!(this.d instanceof MyListViewV9)) {
//            return computedVerticalScrollRange;
//        }
//        return (int) ((((float) ((MyListViewV9) this.d).computeVerticalScrollRange()) * this.L) + ((float) computedVerticalScrollRange));
//    }
//}