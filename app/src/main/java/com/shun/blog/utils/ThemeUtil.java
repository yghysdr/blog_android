package com.shun.blog.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.AnyRes;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.shun.blog.R;
import com.shun.blog.base.BaseUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yghysdr on 2017/5/23.
 */

public class ThemeUtil extends BaseUtils {

    public static final int bg = 100;
    public static final int bgItem = 101;
    public static final int bgNavBot = 102;
    public static final int primary = 200;
    public static final int txtTitle = 300;
    public static final int txtNav = 301;
    public static final int txtContent = 302;
    public static final int txtDes = 303;
    public static final int txtWarning = 304;
    public static final int txtNavBotOn = 305;
    public static final int txtNavBotOff = 306;
    public static final int txtNavOn = 307;
    public static final int txtNavOff = 308;
    public static final int lineHov = 400;


    @IntDef({bg, bgItem, bgNavBot,
            primary,
            txtTitle, txtContent, txtWarning,
            txtNavBotOn, txtNavBotOff, txtNav,
            txtDes, txtNavOn, txtNavOff,
            lineHov})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorType {
    }

    private static Map<Integer, TypedValue> typedValueMap;
    private static Map<Integer, Map<Integer, TypedValue>> themeMap = new HashMap();
    private static int currentThemeId;
    private static Activity mActivity;

    public static void initTheme(Activity activity, int themeId) {
        currentThemeId = themeId;
        if (!themeMap.containsKey(currentThemeId)) {
            Map<Integer, TypedValue> map = new HashMap<>();
            themeMap.put(currentThemeId, map);
        }
        typedValueMap = themeMap.get(themeId);
        mActivity = activity;
        mActivity.setTheme(themeId);
    }

    public static Map<Integer, TypedValue> getColorMap() {
        if (typedValueMap.size() > 0) {
            return typedValueMap;
        }
        TypedValue bg_type = new TypedValue();
        TypedValue bgNavBot_type = new TypedValue();
        TypedValue bgItem_type = new TypedValue();
        TypedValue primary_type = new TypedValue();
        TypedValue txtNav_type = new TypedValue();
        TypedValue txtTitle_type = new TypedValue();
        TypedValue txtDes_type = new TypedValue();
        TypedValue txtContent_type = new TypedValue();
        TypedValue txtWarning_type = new TypedValue();
        TypedValue txtNavBotOn_type = new TypedValue();
        TypedValue txtNavBotOff_type = new TypedValue();
        TypedValue txtNavOn_type = new TypedValue();
        TypedValue txtNavOff_type = new TypedValue();
        TypedValue lineHov_type = new TypedValue();

        Resources.Theme theme = mActivity.getTheme();

        theme.resolveAttribute(R.attr.color_bg, bg_type, true);
        theme.resolveAttribute(R.attr.color_bg_nav_bot, bgNavBot_type, true);
        theme.resolveAttribute(R.attr.color_bg_item, bgItem_type, true);

        theme.resolveAttribute(R.attr.colorPrimary, primary_type, true);

        theme.resolveAttribute(R.attr.color_txt_title, txtTitle_type, true);
        theme.resolveAttribute(R.attr.color_txt_des, txtDes_type, true);
        theme.resolveAttribute(R.attr.color_txt_nav, txtNav_type, true);
        theme.resolveAttribute(R.attr.color_txt_content, txtContent_type, true);
        theme.resolveAttribute(R.attr.color_txt_warning, txtWarning_type, true);
        theme.resolveAttribute(R.attr.color_txt_nav_bot_on, txtNavBotOn_type, true);
        theme.resolveAttribute(R.attr.color_txt_nav_bot_off, txtNavBotOff_type, true);
        theme.resolveAttribute(R.attr.color_txt_nav_on, txtNavOn_type, true);
        theme.resolveAttribute(R.attr.color_txt_nav_off, txtNavOff_type, true);
        theme.resolveAttribute(R.attr.color_line_hov, lineHov_type, true);

        typedValueMap.put(bg, bg_type);
        typedValueMap.put(bgItem, bgItem_type);
        typedValueMap.put(bgNavBot, bgNavBot_type);
        typedValueMap.put(primary, primary_type);
        typedValueMap.put(txtDes, txtDes_type);
        typedValueMap.put(txtNav, txtNav_type);
        typedValueMap.put(txtTitle, txtTitle_type);
        typedValueMap.put(txtContent, txtContent_type);
        typedValueMap.put(txtWarning, txtWarning_type);
        typedValueMap.put(txtNavBotOn, txtNavBotOn_type);
        typedValueMap.put(txtNavBotOff, txtNavBotOff_type);
        typedValueMap.put(txtNavOn, txtNavOn_type);
        typedValueMap.put(txtNavOff, txtNavOff_type);
        typedValueMap.put(lineHov, lineHov_type);
        return typedValueMap;
    }

    @AnyRes
    public static int getResId(@ColorType int type) {
        return getColorMap().get(type).resourceId;
    }

    public static int getColorId(@ColorType int type) {
        return mApplication.getResources().getColor(getResId(type));
    }

    public static void dealRecycleView(RecyclerView recyclerView) {
        //让 RecyclerView 缓存在 Pool 中的 Item 失效
        //那么，如果是ListView，要怎么做呢？这里的思路是通过反射拿到
        //AbsListView 类中的 RecycleBin 对象，然后同样再用反射去调用 clear 方法
        Class<RecyclerView> recyclerViewClass = RecyclerView.class;
        try {
            Field declaredField = recyclerViewClass.getDeclaredField("mRecycler");
            declaredField.setAccessible(true);
            Method declaredMethod = Class.forName(RecyclerView.Recycler.class.getName()).getDeclaredMethod("clear", (Class<?>[]) new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(declaredField.get(recyclerView), new Object[0]);
            RecyclerView.RecycledViewPool recycledViewPool = recyclerView.getRecycledViewPool();
            recycledViewPool.clear();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static void showAnimation(Activity activity) {
        final View decorView = activity.getWindow().getDecorView();
        Bitmap cacheBitmap = getCacheBitmapFromView(decorView);
        if (decorView instanceof ViewGroup && cacheBitmap != null) {
            final View view = new View(mApplication);
            view.setBackgroundDrawable(new BitmapDrawable(mApplication.getResources(), cacheBitmap));
            ViewGroup.LayoutParams layoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) decorView).addView(view, layoutParam);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
            objectAnimator.setDuration(300);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ((ViewGroup) decorView).removeView(view);
                }
            });
            objectAnimator.start();
        }
    }


    /**
     * 获取一个 View 的缓存视图
     *
     * @param view
     * @return
     */
    public static Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        return bitmap;
    }
}
