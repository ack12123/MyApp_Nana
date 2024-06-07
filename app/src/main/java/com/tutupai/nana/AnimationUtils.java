package com.tutupai.nana;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

public class AnimationUtils {

    public enum AnimationDirection {
        LEFT, RIGHT
    }

    public static void applySlideInAnimation(ViewGroup container, AnimationDirection direction) {
        if (container == null || container.getChildCount() == 0) {
            return;
        }

        int childCount = container.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = container.getChildAt(i);
            child.setTranslationY(-child.getHeight());
            child.setAlpha(0);

            ObjectAnimator translationY = ObjectAnimator.ofFloat(child, "translationY", 0);
            ObjectAnimator alpha = ObjectAnimator.ofFloat(child, "alpha", 1);
            translationY.setDuration(500);
            alpha.setDuration(500);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(translationY, alpha);
            animatorSet.setStartDelay(i * 100); // ÒÀ´ÎÑÓ³ÙÆô¶¯
            animatorSet.start();
        }
    }
}
