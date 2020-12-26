package com.example.administrator.yyqianghongb.view.shapeTvPage;

import android.graphics.Color;

/**
 * Created by 杨勇 on 2020/5/28.
 * QQ邮箱：824343111@qq.com  private 
 */
public class AttributeSetData {
        private int shapeType = -1;
        private int solidColor = -1;

        private int strokeWidth = -1;
        private int strokeColor = -1;
        private float strokeDashWidth = 0.0f;
        private float strokeDashGap = 0.0f;

        private float cornersRadius = 0.0f;
        private float cornersTopLeftRadius = 0.0f;
        private float cornersTopRightRadius = 0.0f;
        private float cornersBottomLeftRadius = 0.0f;
        private float cornersBottomRightRadius = 0.0f;

        private int gradientAngle = -1;
        private int gradientCenterX = 0;
        private int gradientCenterY = 0;
        private int gradientGradientRadius = 0;
        private int gradientStartColor = -1;
        private int gradientCenterColor = -1;
        private int gradientEndColor = -1;
        private int gradientType = 0;
        private boolean gradientUseLevel = false;

        private int sizeWidth = -1;
        private int sizeHeight = -1;

        private int selectorPressedColor = 0;
        private int selectorDisableColor = 0;
        private int selectorNormalColor = 0;

        private boolean useSelector = false;

        //////////阴影相关////////
        private boolean showShadow = false;
        private int shadowColor = Color.GRAY;
        private float shadowColorAlpha = 0.2f;
        private float shadowLeftWidth = 0f;
        private float shadowTopWidth = 0f;
        private float shadowRightWidth = 0f;
        private float shadowBottomWidth = 0f;

        private float shadowCornersRadius = 0f;
        private float shadowCornersTopLeftRadius = 0f;
        private float shadowCornersTopRightRadius = 0f;
        private float shadowCornersBottomLeftRadius = 0f;
        private float shadowCornersBottomRightRadius = 0f;

    public int getShapeType() {
        return shapeType;
    }

    public void setShapeType( int shapeType) {
        this.shapeType = shapeType;
    }

    public int getSolidColor() {
        return solidColor;
    }

    public void setSolidColor( int solidColor) {
        this.solidColor = solidColor;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth( int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor( int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public float getStrokeDashWidth() {
        return strokeDashWidth;
    }

    public void setStrokeDashWidth( float strokeDashWidth) {
        this.strokeDashWidth = strokeDashWidth;
    }

    public float getStrokeDashGap() {
        return strokeDashGap;
    }

    public void setStrokeDashGap( float strokeDashGap) {
        this.strokeDashGap = strokeDashGap;
    }

    public float getCornersRadius() {
        return cornersRadius;
    }

    public void setCornersRadius( float cornersRadius) {
        this.cornersRadius = cornersRadius;
    }

    public float getCornersTopLeftRadius() {
        return cornersTopLeftRadius;
    }

    public void setCornersTopLeftRadius( float cornersTopLeftRadius) {
        this.cornersTopLeftRadius = cornersTopLeftRadius;
    }

    public float getCornersTopRightRadius() {
        return cornersTopRightRadius;
    }

    public void setCornersTopRightRadius( float cornersTopRightRadius) {
        this.cornersTopRightRadius = cornersTopRightRadius;
    }

    public float getCornersBottomLeftRadius() {
        return cornersBottomLeftRadius;
    }

    public void setCornersBottomLeftRadius( float cornersBottomLeftRadius) {
        this.cornersBottomLeftRadius = cornersBottomLeftRadius;
    }

    public float getCornersBottomRightRadius() {
        return cornersBottomRightRadius;
    }

    public void setCornersBottomRightRadius( float cornersBottomRightRadius) {
        this.cornersBottomRightRadius = cornersBottomRightRadius;
    }

    public int getGradientAngle() {
        return gradientAngle;
    }

    public void setGradientAngle( int gradientAngle) {
        this.gradientAngle = gradientAngle;
    }

    public int getGradientCenterX() {
        return gradientCenterX;
    }

    public void setGradientCenterX( int gradientCenterX) {
        this.gradientCenterX = gradientCenterX;
    }

    public int getGradientCenterY() {
        return gradientCenterY;
    }

    public void setGradientCenterY( int gradientCenterY) {
        this.gradientCenterY = gradientCenterY;
    }

    public int getGradientGradientRadius() {
        return gradientGradientRadius;
    }

    public void setGradientGradientRadius( int gradientGradientRadius) {
        this.gradientGradientRadius = gradientGradientRadius;
    }

    public int getGradientStartColor() {
        return gradientStartColor;
    }

    public void setGradientStartColor( int gradientStartColor) {
        this.gradientStartColor = gradientStartColor;
    }

    public int getGradientCenterColor() {
        return gradientCenterColor;
    }

    public void setGradientCenterColor( int gradientCenterColor) {
        this.gradientCenterColor = gradientCenterColor;
    }

    public int getGradientEndColor() {
        return gradientEndColor;
    }

    public void setGradientEndColor( int gradientEndColor) {
        this.gradientEndColor = gradientEndColor;
    }

    public int getGradientType() {
        return gradientType;
    }

    public void setGradientType( int gradientType) {
        this.gradientType = gradientType;
    }

    public boolean isGradientUseLevel() {
        return gradientUseLevel;
    }

    public void setGradientUseLevel( boolean gradientUseLevel) {
        this.gradientUseLevel = gradientUseLevel;
    }

    public int getSizeWidth() {
        return sizeWidth;
    }

    public void setSizeWidth( int sizeWidth) {
        this.sizeWidth = sizeWidth;
    }

    public int getSizeHeight() {
        return sizeHeight;
    }

    public void setSizeHeight( int sizeHeight) {
        this.sizeHeight = sizeHeight;
    }

    public int getSelectorPressedColor() {
        return selectorPressedColor;
    }

    public void setSelectorPressedColor( int selectorPressedColor) {
        this.selectorPressedColor = selectorPressedColor;
    }

    public int getSelectorDisableColor() {
        return selectorDisableColor;
    }

    public void setSelectorDisableColor( int selectorDisableColor) {
        this.selectorDisableColor = selectorDisableColor;
    }

    public int getSelectorNormalColor() {
        return selectorNormalColor;
    }

    public void setSelectorNormalColor( int selectorNormalColor) {
        this.selectorNormalColor = selectorNormalColor;
    }

    public boolean isUseSelector() {
        return useSelector;
    }

    public void setUseSelector( boolean useSelector) {
        this.useSelector = useSelector;
    }

    public boolean isShowShadow() {
        return showShadow;
    }

    public void setShowShadow( boolean showShadow) {
        this.showShadow = showShadow;
    }

    public int getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor( int shadowColor) {
        this.shadowColor = shadowColor;
    }

    public float getShadowColorAlpha() {
        return shadowColorAlpha;
    }

    public void setShadowColorAlpha( float shadowColorAlpha) {
        this.shadowColorAlpha = shadowColorAlpha;
    }

    public float getShadowLeftWidth() {
        return shadowLeftWidth;
    }

    public void setShadowLeftWidth( float shadowLeftWidth) {
        this.shadowLeftWidth = shadowLeftWidth;
    }

    public float getShadowTopWidth() {
        return shadowTopWidth;
    }

    public void setShadowTopWidth( float shadowTopWidth) {
        this.shadowTopWidth = shadowTopWidth;
    }

    public float getShadowRightWidth() {
        return shadowRightWidth;
    }

    public void setShadowRightWidth( float shadowRightWidth) {
        this.shadowRightWidth = shadowRightWidth;
    }

    public float getShadowBottomWidth() {
        return shadowBottomWidth;
    }

    public void setShadowBottomWidth( float shadowBottomWidth) {
        this.shadowBottomWidth = shadowBottomWidth;
    }

    public float getShadowCornersRadius() {
        return shadowCornersRadius;
    }

    public void setShadowCornersRadius( float shadowCornersRadius) {
        this.shadowCornersRadius = shadowCornersRadius;
    }

    public float getShadowCornersTopLeftRadius() {
        return shadowCornersTopLeftRadius;
    }

    public void setShadowCornersTopLeftRadius( float shadowCornersTopLeftRadius) {
        this.shadowCornersTopLeftRadius = shadowCornersTopLeftRadius;
    }

    public float getShadowCornersTopRightRadius() {
        return shadowCornersTopRightRadius;
    }

    public void setShadowCornersTopRightRadius( float shadowCornersTopRightRadius) {
        this.shadowCornersTopRightRadius = shadowCornersTopRightRadius;
    }

    public float getShadowCornersBottomLeftRadius() {
        return shadowCornersBottomLeftRadius;
    }

    public void setShadowCornersBottomLeftRadius( float shadowCornersBottomLeftRadius) {
        this.shadowCornersBottomLeftRadius = shadowCornersBottomLeftRadius;
    }

    public float getShadowCornersBottomRightRadius() {
        return shadowCornersBottomRightRadius;
    }

    public void setShadowCornersBottomRightRadius( float shadowCornersBottomRightRadius) {
        this.shadowCornersBottomRightRadius = shadowCornersBottomRightRadius;
    }
}
