package com.example.administrator.yyqianghongb.view.shapeTvPage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

public final class ShapeBuilder {
    private int shapeType = -1;
    private int solidColor = -1;
    private int strokeWidth = -1;
    private int strokeColor = -1;
    private float strokeDashWidth;
    private float strokeDashGap;
    private float cornersRadius;
    private float cornersTopLeftRadius;
    private float cornersTopRightRadius;
    private float cornersBottomLeftRadius;
    private float cornersBottomRightRadius;
    private int gradientAngle = -1;
    private int gradientCenterX;
    private int gradientCenterY;
    private int gradientGradientRadius;
    private int gradientStartColor = -1;
    private int gradientCenterColor = -1;
    private int gradientEndColor = -1;
    private int gradientType;
    private boolean gradientUseLevel;
    private int sizeWidth = -1;
    private int sizeHeight = -1;
    private int selectorPressedColor;
    private int selectorDisableColor;
    private int selectorNormalColor;
    private boolean useSelector;
    public static final int RECTANGLE = 0;
    public static final int OVAL = 1;
    public static final int LINE = 2;
    public static final int RING = 3;
    public static final int TOP_BOTTOM = 0;
    public static final int TR_BL = 1;
    public static final int RIGHT_LEFT = 2;
    public static final int BR_TL = 3;
    public static final int BOTTOM_TOP = 4;
    public static final int BL_TR = 5;
    public static final int LEFT_RIGHT = 6;
    public static final int TL_BR = 7;
    public static final int LINEAR = 0;
    public static final int RADIAL = 1;
    public static final int SWEEP = 2;

    
    public  ShapeBuilder setShapeType(int shapeType) {
        this.shapeType = shapeType;
        return this;
    }

    
    public  ShapeBuilder setShapeSolidColor(int color) {
        this.solidColor = color;
        return this;
    }

    
    public  ShapeBuilder setShapeCornersRadius(float radius) {
        this.cornersRadius = radius;
        return this;
    }

    
    public  ShapeBuilder setShapeCornersTopLeftRadius(float radius) {
        this.cornersTopLeftRadius = radius;
        return this;
    }

    
    public  ShapeBuilder setShapeCornersTopRightRadius(float radius) {
        this.cornersTopRightRadius = radius;
        return this;
    }

    
    public  ShapeBuilder setShapeCornersBottomRightRadius(float radius) {
        this.cornersBottomRightRadius = radius;
        return this;
    }

    
    public  ShapeBuilder setShapeCornersBottomLeftRadius(float radius) {
        this.cornersBottomLeftRadius = radius;
        return this;
    }

    
    public  ShapeBuilder setShapeStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        return this;
    }

    
    public  ShapeBuilder setShapeStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        return this;
    }

    
    public  ShapeBuilder setShapeStrokeDashWidth(float strokeDashWidth) {
        this.strokeDashWidth = strokeDashWidth;
        return this;
    }

    
    public  ShapeBuilder setShapeStrokeDashGap(float strokeDashGap) {
        this.strokeDashGap = strokeDashGap;
        return this;
    }

    
    public  ShapeBuilder setShapeUseSelector(boolean useSelector) {
        this.useSelector = useSelector;
        return this;
    }

    
    public  ShapeBuilder setShapeSelectorPressedColor(int color) {
        this.selectorPressedColor = color;
        return this;
    }

    
    public  ShapeBuilder setShapeSelectorNormalColor(int color) {
        this.selectorNormalColor = color;
        return this;
    }

    
    public  ShapeBuilder setShapeSelectorDisableColor(int color) {
        this.selectorDisableColor = color;
        return this;
    }

    
    public  ShapeBuilder setShapeSizeWidth(int sizeWidth) {
        this.sizeWidth = sizeWidth;
        return this;
    }

    
    public  ShapeBuilder setShapeSizeHeight(int sizeHeight) {
        this.sizeHeight = sizeHeight;
        return this;
    }

    
    public  ShapeBuilder setShapeGradientAngle(int gradientAngle) {
        this.gradientAngle = gradientAngle;
        return this;
    }

    
    public  ShapeBuilder setShapeGradientCenterX(int gradientCenterX) {
        this.gradientCenterX = gradientCenterX;
        return this;
    }

    
    public  ShapeBuilder setShapeGradientCenterY(int gradientCenterY) {
        this.gradientCenterY = gradientCenterY;
        return this;
    }

    
    public  ShapeBuilder setShapeGradientGradientRadius(int gradientGradientRadius) {
        this.gradientGradientRadius = gradientGradientRadius;
        return this;
    }

    
    public  ShapeBuilder setShapeGradientStartColor(int gradientStartColor) {
        this.gradientStartColor = gradientStartColor;
        return this;
    }

    
    public  ShapeBuilder setShapeGradientCenterColor(int gradientCenterColor) {
        this.gradientCenterColor = gradientCenterColor;
        return this;
    }

    
    public  ShapeBuilder setShapeGradientEndColor(int gradientEndColor) {
        this.gradientEndColor = gradientEndColor;
        return this;
    }

    
    public  ShapeBuilder setShapeGradientType(int gradientType) {
        this.gradientType = gradientType;
        return this;
    }

    
    public  ShapeBuilder setShapeGradientUseLevel(boolean gradientUseLevel) {
        this.gradientUseLevel = gradientUseLevel;
        return this;
    }

    public final void init( View targetView,  AttributeSetData attributeSetData) {
        this.setShapeType(attributeSetData.getShapeType());
        this.setShapeCornersRadius(attributeSetData.getCornersRadius());
        this.setShapeCornersTopLeftRadius(attributeSetData.getCornersTopLeftRadius());
        this.setShapeCornersTopRightRadius(attributeSetData.getCornersTopRightRadius());
        this.setShapeCornersBottomRightRadius(attributeSetData.getCornersBottomRightRadius());
        this.setShapeCornersBottomLeftRadius(attributeSetData.getCornersBottomLeftRadius());
        this.setShapeSolidColor(attributeSetData.getSolidColor());
        this.setShapeStrokeColor(attributeSetData.getStrokeColor());
        this.setShapeStrokeWidth(attributeSetData.getStrokeWidth());
        this.setShapeStrokeDashWidth(attributeSetData.getStrokeDashWidth());
        this.setShapeStrokeDashGap(attributeSetData.getStrokeDashGap());
        this.setShapeUseSelector(attributeSetData.isUseSelector());
        this.setShapeSelectorNormalColor(attributeSetData.getSelectorNormalColor());
        this.setShapeSelectorPressedColor(attributeSetData.getSelectorPressedColor());
        this.setShapeSelectorDisableColor(attributeSetData.getSelectorDisableColor());
        this.setShapeSizeWidth(attributeSetData.getSizeWidth());
        this.setShapeSizeHeight(attributeSetData.getSizeHeight());
        this.setShapeGradientType(attributeSetData.getGradientType());
        this.setShapeGradientAngle(attributeSetData.getGradientAngle());
        this.setShapeGradientGradientRadius(attributeSetData.getGradientGradientRadius());
        this.setShapeGradientUseLevel(attributeSetData.isGradientUseLevel());
        this.setShapeGradientCenterX(attributeSetData.getGradientCenterX());
        this.setShapeGradientCenterY(attributeSetData.getGradientCenterY());
        this.setShapeGradientStartColor(attributeSetData.getGradientStartColor());
        this.setShapeGradientCenterColor(attributeSetData.getGradientCenterColor());
        this.setShapeGradientEndColor(attributeSetData.getGradientEndColor());
        this.into(targetView);
    }

    private  void setShapeType(GradientDrawable gradientDrawable) {
        if (this.shapeType != -1) {
            switch(this.shapeType) {
                case RECTANGLE:
                    gradientDrawable.setShape(GradientDrawable.RECTANGLE);
                    break;
                case OVAL:
                    gradientDrawable.setShape(GradientDrawable.OVAL);
                    break;
                case LINE:
                    gradientDrawable.setShape(GradientDrawable.LINE);
                    break;
                case RING:
                    gradientDrawable.setShape(GradientDrawable.RING);
            }
        }

    }

    private  void setSize(GradientDrawable gradientDrawable) {
        if (this.sizeWidth > 0 || this.sizeHeight > 0) {
            gradientDrawable.setSize(this.sizeWidth, this.sizeHeight);
        }

    }

    private  void setBorder(GradientDrawable gradientDrawable) {
        if (this.strokeWidth >= 0) {
            gradientDrawable.setStroke(this.strokeWidth, this.strokeColor, this.strokeDashWidth, this.strokeDashGap);
        }

    }

    private  void setRadius(GradientDrawable gradientDrawable) {
        if (this.shapeType == 0) {
            if (this.cornersRadius != 0.0F) {
                gradientDrawable.setCornerRadius(this.cornersRadius);
            } else if (this.cornersTopLeftRadius != 0.0F || this.cornersTopRightRadius != 0.0F || this.cornersBottomRightRadius != 0.0F || this.cornersBottomLeftRadius != 0.0F) {
                gradientDrawable.setCornerRadii(new float[]{this.cornersTopLeftRadius, this.cornersTopLeftRadius, this.cornersTopRightRadius, this.cornersTopRightRadius, this.cornersBottomRightRadius, this.cornersBottomRightRadius, this.cornersBottomLeftRadius, this.cornersBottomLeftRadius});
            }
        }

    }

    private  void setSolidColor(GradientDrawable gradientDrawable) {
        if (this.gradientStartColor == -1 && this.gradientEndColor == -1) {
            gradientDrawable.setColor(this.solidColor);
        }

    }

    private  void setSelectorColor(GradientDrawable gradientDrawable, int state) {
        if (this.useSelector && state != 0) {
            switch(state) {
                case -16842910:
                    gradientDrawable.setColor(this.selectorDisableColor);
                    break;
                case 16842910:
                    gradientDrawable.setColor(this.selectorNormalColor);
                    break;
                case 16842919:
                    gradientDrawable.setColor(this.selectorPressedColor);
            }
        }

    }

    private  void setGradient(GradientDrawable gradientDrawable) {
        if ((this.gradientStartColor != -1 || this.gradientEndColor != -1) && VERSION.SDK_INT >= 16) {
            if (this.gradientCenterColor == -1) {
                gradientDrawable.setColors(new int[]{this.gradientStartColor, this.gradientEndColor});
            } else {
                gradientDrawable.setColors(new int[]{this.gradientStartColor, this.gradientCenterColor, this.gradientEndColor});
            }

            switch(this.gradientType) {
                case 0:
                    gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
                    if (this.gradientAngle != -1) {
                        gradientDrawable.setOrientation(this.getGradientOrientationByAngle(this.gradientAngle));
                    }
                    break;
                case 1:
                    gradientDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
                    gradientDrawable.setGradientRadius((float)this.gradientGradientRadius);
                    break;
                case 2:
                    gradientDrawable.setGradientType(GradientDrawable.SWEEP_GRADIENT);
            }

            if (this.gradientCenterX != 0 || this.gradientCenterY != 0) {
                gradientDrawable.setGradientCenter((float)this.gradientCenterX, (float)this.gradientCenterY);
            }

            gradientDrawable.setUseLevel(this.gradientUseLevel);
        }

    }

    private  Orientation getGradientOrientationByAngle(int gradientAngle) {
        Orientation orientation = (Orientation)null;
        switch(gradientAngle) {
            case 0:
                orientation = Orientation.LEFT_RIGHT;
                break;
            case 45:
                orientation = Orientation.BL_TR;
                break;
            case 90:
                orientation = Orientation.BOTTOM_TOP;
                break;
            case 135:
                orientation = Orientation.BR_TL;
                break;
            case 180:
                orientation = Orientation.RIGHT_LEFT;
                break;
            case 225:
                orientation = Orientation.TR_BL;
                break;
            case 270:
                orientation = Orientation.TOP_BOTTOM;
                break;
            case 315:
                orientation = Orientation.TL_BR;
        }

        return orientation;
    }

    private  StateListDrawable getSelectorDrawable() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, (Drawable)this.getDrawable(android.R.attr.state_pressed));
        stateListDrawable.addState(new int[]{-android.R.attr.state_enabled}, (Drawable)this.getDrawable(-android.R.attr.state_enabled));
        stateListDrawable.addState(new int[0], (Drawable)this.getDrawable(android.R.attr.state_enabled));
        return stateListDrawable;
    }

    private  GradientDrawable getDrawable(int state) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.setShapeType(gradientDrawable);
        this.setGradient(gradientDrawable);
        this.setSolidColor(gradientDrawable);
        this.setBorder(gradientDrawable);
        this.setRadius(gradientDrawable);
        this.setSize(gradientDrawable);
        this.setSelectorColor(gradientDrawable, state);
        return gradientDrawable;
    }

    public final void into(@Nullable View view) {
        if (view != null) {
            ViewCompat.setBackground(view, this.useSelector ? (Drawable)this.getSelectorDrawable() : (Drawable)this.getDrawable(0));
        }
    }

    private  int dip2px(Context context, float dipValue) {
        Resources var10000 = context.getResources();
        float scale = var10000.getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5F);
    }


    public static final class Companion {
        private Companion() {
        }
    }
}
