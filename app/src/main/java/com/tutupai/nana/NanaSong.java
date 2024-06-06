package com.tutupai.nana;

import static androidx.compose.ui.semantics.SemanticsPropertiesKt.collapse;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class NanaSong extends Fragment {

    private MediaPlayer mediaPlayer;

    public static class ButtonAudioPair {
        private final int buttonTextResId;
        private final int audioResId;

        public ButtonAudioPair(int buttonTextResId, int audioResId) {
            this.buttonTextResId = buttonTextResId;
            this.audioResId = audioResId;
        }

        public int getButtonTextResId() {
            return buttonTextResId;
        }

        public int getAudioResId() {
            return audioResId;
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 加载布局
        View view = inflater.inflate(R.layout.fragment_nanasong, container, false);


        FlexboxLayout buttonNanaSong = view.findViewById(R.id.button_nanasong);
        FlexboxLayout buttonNanaSing = view.findViewById(R.id.button_nanasing);

        View dividerSong = view.findViewById(R.id.divider_song);
        View dividerSing = view.findViewById(R.id.divider_sing);

      
        buttonNanaSong.setVisibility(View.GONE); // 初始时隐藏 buttonNanaSong
        buttonNanaSing.setVisibility(View.GONE); // 初始时隐藏 buttonNanaSing

        setupToggleTextView(view.findViewById(R.id.toggle_button_nanasong), buttonNanaSong, dividerSong, R.string.nanasong, R.string.nanasong);
        setupToggleTextView(view.findViewById(R.id.toggle_button_nanasing), buttonNanaSing, dividerSing, R.string.nanasing, R.string.nanasing);

        // 获取按钮-音频配对列表
        List<ButtonAudioPair> nanaSongButtonAudioPairs = ButtonAudioPairNanaSong.createNanaSongButtonAudioPairs();
        List<ButtonAudioPair> nanaSingButtonAudioPairs = ButtonAudioPairNanaSong.createNanaSingButtonAudioPairs();

        // 动态添加按钮到各自的容器
        addButtonsToContainer(buttonNanaSong, nanaSongButtonAudioPairs);
        addButtonsToContainer(buttonNanaSing, nanaSingButtonAudioPairs);

        return view;
    }

    private void setupToggleTextView(TextView textView, FlexboxLayout flexboxLayout, View divider, int collapseTextResId, int expandTextResId) {
        textView.setVisibility(View.VISIBLE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonContainerVisibility((TextView) v, flexboxLayout, divider, collapseTextResId, expandTextResId);
            }
        });
    }

    private void toggleButtonContainerVisibility(TextView textView, FlexboxLayout flexboxLayout, View divider, int collapseTextResId, int expandTextResId) {
        if (flexboxLayout.getVisibility() == View.GONE) {
            expand(flexboxLayout);
            divider.setVisibility(View.VISIBLE);
            textView.setText(collapseTextResId); // 展开时的文本
        } else {
            collapse(flexboxLayout);
            divider.setVisibility(View.INVISIBLE);
            textView.setText(expandTextResId); // 收起时的文本
        }
    }

    private void expand(final View v) {
        v.measure(View.MeasureSpec.makeMeasureSpec(((View) v.getParent()).getWidth(), View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        final int targetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);

        ValueAnimator animator = ValueAnimator.ofInt(0, targetHeight);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                v.getLayoutParams().height = (int) animation.getAnimatedValue();
                v.requestLayout();
            }
        });
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density) / 2); // 减少持续时间
        animator.start();
    }

    private void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        ValueAnimator animator = ValueAnimator.ofInt(initialHeight, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                v.getLayoutParams().height = value;
                v.requestLayout();
                if (value == 0) {
                    v.setVisibility(View.GONE);
                }
            }
        });
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density) / 2); // 减少持续时间
        animator.start();
    }


    private void playAudio(int audioResId) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(getContext(), audioResId);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        mediaPlayer.start();
    }

    private void stopAudioPlayback() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void addButtonsToContainer(FlexboxLayout container, List<ButtonAudioPair> buttonAudioPairs) {
        for (ButtonAudioPair pair : buttonAudioPairs) {
            MaterialButton button = new MaterialButton(requireActivity(), null, com.google.android.material.R.attr.materialButtonStyle);
            button.setText(pair.buttonTextResId);
            button.setTextColor(getResources().getColor(R.color.my_button_text_color));

            // 设置按钮的LayoutParams
            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(
                    FlexboxLayout.LayoutParams.WRAP_CONTENT,
                    FlexboxLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 10, 10, 10); // 设置按钮之间的间距
            button.setLayoutParams(params);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playAudio(pair.audioResId);
                }
            });
            container.addView(button);
        }
    }
}