package com.example.administrator.yyqianghongb.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.yyqianghongb.utils.MyLoge;
import com.google.android.material.appbar.AppBarLayout;

import org.jetbrains.annotations.NotNull;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

/**
 * Created by 杨勇 on 2020/12/3.
 * QQ邮箱：824343111@qq.com
 */
public class AppbarZoomBehavior extends AppBarLayout.Behavior {
    private static final String TAG = "imgTag";
    private static final String TAG2 = "scrollTag";
    private static final String TAG3 = "bottomLayoutTag";
    private static final String TAG4 = "tabTag";
    private View mImageView, mScrollLayout, mBottomLayout, mTab;
    private int mAppbarHeight;//记录AppbarLayout原始高度
    private int mImageViewHeight;//记录ImageView原始高度
    private float mTabHeight;//记录Tab 的高度

    private static final float MAX_ZOOM_HEIGHT = 1000;//放大最大高度
    private static final float DAMPING = 0.85f; //阻尼系数 1为正常，小于则阻尼越大

    private float mTotalDy;//手指在Y轴滑动的总距离
    private float mScaleValue;//图片缩放比例
    private int mLastBottom;//Appbar的变化高度

    private boolean isAnimate;//是否做动画标志



    public AppbarZoomBehavior() {
    }

    public AppbarZoomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * AppBarLayout布局时调用
     *
     * @param parent          父布局CoordinatorLayout
     * @param abl             使用此Behavior的AppBarLayout
     * @param layoutDirection 布局方向
     * @return 返回true表示子View重新布局，返回false表示请求默认布局
     */
    @Override
    public boolean onLayoutChild(@NotNull CoordinatorLayout parent, @NotNull AppBarLayout abl, int layoutDirection) {
        boolean handled = super.onLayoutChild(parent, abl, layoutDirection);
        // 需要在调用过super.onLayoutChild()方法之后获取
        if (mImageView == null) {
            mImageView = parent.findViewWithTag(TAG);
            mScrollLayout = parent.findViewWithTag(TAG2);
            mBottomLayout = parent.findViewWithTag(TAG3);
            mTab = parent.findViewWithTag(TAG4);
            if (mScrollLayout != null && mImageView != null && mBottomLayout != null) {
                init(abl);
            }
            if (mTab != null) {
                mTabHeight = mTab.getHeight();
            }
        }
        return handled;
    }

    /**
     * 进行初始化操作，在这里获取到ImageView的引用，和Appbar的原始高度
     *
     * @param abl
     */
    private void init(AppBarLayout abl) {
        abl.setClipChildren(false);
        mAppbarHeight = abl.getHeight();
        if (mImageView != null) {
            mImageViewHeight = mImageView.getHeight();
        }
    }

    /**
     * 是否处理嵌套滑动
     * 当CoordinatorLayout的子View尝试发起嵌套滚动时调用
     *
     * @param parent            父布局CoordinatorLayout
     * @param child             使用此Behavior的AppBarLayout
     * @param directTargetChild CoordinatorLayout的子View，或者是包含嵌套滚动操作的目标View
     * @param target            发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     * @param nestedScrollAxes  嵌套滚动的方向
     * @return 返回true表示接受滚动
     */
    @Override
    public boolean onStartNestedScroll(@NotNull CoordinatorLayout parent, @NotNull AppBarLayout child, @NotNull View directTargetChild, View target, int nestedScrollAxes, int type) {
        isAnimate = true;
        return true;
    }

    /**
     * 在这里做具体的滑动处理
     * 当准备开始嵌套滚动时调用
     *
     * @param coordinatorLayout 父布局CoordinatorLayout
     * @param child             使用此Behavior的AppBarLayout
     * @param target            发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     * @param dx                用户在水平方向上滑动的像素数
     * @param dy                用户在垂直方向上滑动的像素数
     * @param consumed          输出参数，consumed[0]为水平方向应该消耗的距离，consumed[1]为垂直方向应该消耗的距离
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, @NotNull AppBarLayout child, View target, int dx, int dy, int[] consumed, int type) {
        if (mImageView != null && child.getBottom() >= mAppbarHeight && dy < 0 && type == ViewCompat.TYPE_TOUCH) {
            zoomHeaderImageView(child, dx, dy, target);
        } else {
            if (mImageView != null && child.getBottom() > mAppbarHeight && dy > 0 && type == ViewCompat.TYPE_TOUCH) {
                consumed[1] = dy;
                zoomHeaderImageView(child, dx, dy, target);
            } else {
                if (valueAnimator == null || !valueAnimator.isRunning()) {
                    super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
                }

            }
        }

    }


    /**
     * 对ImageView进行缩放处理，对AppbarLayout进行高度的设置
     *
     * @param abl
     * @param dy
     */
    private void zoomHeaderImageView(AppBarLayout abl, int dx, int dy, View target) {
        mTotalDy += -dy * DAMPING;
        mTotalDy = Math.min(mTotalDy, MAX_ZOOM_HEIGHT) ;
        mScaleValue = Math.max(1f, 1f + mTotalDy / MAX_ZOOM_HEIGHT);
        ViewCompat.setScaleX(mImageView, mScaleValue);
        ViewCompat.setScaleY(mImageView, mScaleValue);
        int addHeight = (int) (mImageViewHeight / 2 * (mScaleValue - 1));
        mLastBottom = mAppbarHeight + addHeight;
        MyLoge.addLoge("mLastBottom", mLastBottom + "....");
        abl.setBottom(mLastBottom);
        mScrollLayout.setBottom((int) (mLastBottom - mTabHeight));
        ViewCompat.setTranslationY(mBottomLayout, addHeight);
        if (mTab != null) {
            ViewCompat.setTranslationY(mTab, addHeight);
        }
    }


    /**
     * 处理惯性滑动的情况
     * 当嵌套滚动的子View准备快速滚动时调用
     *
     * @param coordinatorLayout 父布局CoordinatorLayout
     * @param child             使用此Behavior的AppBarLayout
     * @param target            发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     * @param velocityX         水平方向的速度
     * @param velocityY         垂直方向的速度
     * @return 如果Behavior消耗了快速滚动返回true
     */
    @Override
    public boolean onNestedPreFling(@NotNull CoordinatorLayout coordinatorLayout, @NotNull AppBarLayout child, @NotNull View target, float velocityX, float velocityY) {
        if (velocityY > 100) {
            isAnimate = false;
        }
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }


    /**
     * 滑动停止的时候，恢复AppbarLayout、ImageView的原始状态
     * 当定制滚动时调用
     *
     * @param coordinatorLayout 父布局CoordinatorLayout
     * @param abl               使用此Behavior的AppBarLayout
     * @param target            发起嵌套滚动的目标View(即AppBarLayout下面的ScrollView或RecyclerView)
     */
    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, @NotNull AppBarLayout abl, View target, int type) {
        recovery(abl);
        super.onStopNestedScroll(coordinatorLayout, abl, target, type);
    }

    ValueAnimator valueAnimator;

    /**
     * 通过属性动画的形式，恢复AppbarLayout、ImageView的原始状态
     *
     * @param abl
     */
    private void recovery(final AppBarLayout abl) {
        float nowTranslationY = mBottomLayout.getTranslationY();
        if (mTotalDy > 0) {
            mTotalDy = 0;
            if (isAnimate) {
                valueAnimator = ValueAnimator.ofFloat(mScaleValue, 1f).setDuration(220);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        ViewCompat.setScaleX(mImageView, value);
                        ViewCompat.setScaleY(mImageView, value);
                        float deleteHeight = (mLastBottom - mAppbarHeight) * animation.getAnimatedFraction();
                        float translationDeleteHeight = nowTranslationY - (nowTranslationY) * (animation.getAnimatedFraction());
                        abl.setBottom((int) (mLastBottom - deleteHeight));
                        mScrollLayout.setBottom((int) (mLastBottom - mTabHeight - deleteHeight));
                        ViewCompat.setTranslationY(mBottomLayout, translationDeleteHeight);
                        if (mTab != null) {
                            ViewCompat.setTranslationY(mTab, translationDeleteHeight);
                        }
                    }
                });
                valueAnimator.start();
            } else {
                ViewCompat.setScaleX(mImageView, 1f);
                ViewCompat.setScaleY(mImageView, 1f);
                abl.setBottom(mAppbarHeight);
                mScrollLayout.setBottom((int) (mAppbarHeight - mTabHeight));
                ViewCompat.setTranslationY(mBottomLayout, 0f);
                if (mTab != null) {
                    ViewCompat.setTranslationY(mTab, 0f);
                }
            }
        }
    }
}