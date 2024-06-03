package com.tutupai.nana;

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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private MediaPlayer mediaPlayer;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 加载布局
        View view = inflater.inflate(R.layout.fragment_nana, container, false);

        FlexboxLayout buttonContainer = view.findViewById(R.id.button_container);
        FlexboxLayout buttonNanaSong = view.findViewById(R.id.button_nanasong);
        FlexboxLayout buttonNanaSing = view.findViewById(R.id.button_nanasing);
        FlexboxLayout buttonLose = view.findViewById(R.id.button_lose);
        View dividerRecover = view.findViewById(R.id.divider_recover);
        View dividerSong = view.findViewById(R.id.divider_song);
        View dividerLose = view.findViewById(R.id.divider_lose);
        View dividerSing = view.findViewById(R.id.divider_sing);

        buttonContainer.setVisibility(View.GONE); // 初始时隐藏 buttonContainer
        buttonNanaSong.setVisibility(View.GONE); // 初始时隐藏 buttonNanaSong
        buttonLose.setVisibility(View.GONE); // 初始时隐藏 buttonLose
        buttonNanaSing.setVisibility(View.GONE); // 初始时隐藏 buttonNanaSing

        setupToggleTextView(view.findViewById(R.id.toggle_button_textview), buttonContainer, dividerRecover, R.string.recover, R.string.recover);
        setupToggleTextView(view.findViewById(R.id.toggle_button_nanasong), buttonNanaSong, dividerSong, R.string.nanasong, R.string.nanasong);
        setupToggleTextView(view.findViewById(R.id.toggle_button_lose), buttonLose, dividerLose, R.string.lose, R.string.lose);
        setupToggleTextView(view.findViewById(R.id.toggle_button_nanasing), buttonNanaSing, dividerSing, R.string.nanasing, R.string.nanasing);

        // 按钮和对应音频资源的配对 - toggle_button_recover
        List<ButtonAudioPair> recoverButtonAudioPairs = new ArrayList<>();
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button2, R.raw.wbl));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button3, R.raw.anqila));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button4, R.raw.myname));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button5, R.raw.bigfail));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button6, R.raw.nps));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button7, R.raw.ymom));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button8, R.raw.xftk));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button9, R.raw.signbad));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button10, R.raw.eatdoor));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button11, R.raw.wym));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button12, R.raw.dirty));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button13, R.raw.annoy));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button14, R.raw.dam));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button15, R.raw.contata));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button16, R.raw.eatshit));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button17, R.raw.makemoney));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button18, R.raw.deserve));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button19, R.raw.eightyyears));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button20, R.raw.gun));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button21, R.raw.study));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button22, R.raw.notnormal));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button23, R.raw.tongue));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button24, R.raw.dontlike));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button25, R.raw.yfmrb));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button26, R.raw.report));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button27, R.raw.fammm));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button28, R.raw.gohome));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button29, R.raw.dsp));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button30, R.raw.grandma));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button31, R.raw.vulgar));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button32, R.raw.ymbtn));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button33, R.raw.battle));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button34, R.raw.bornbaby));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button35, R.raw.umbt));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button36, R.raw.reportaon));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button37, R.raw.wantchild));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button38, R.raw.knowart));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button39, R.raw.mustapologise));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button40, R.raw.cantlook));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.button41, R.raw.nappp));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.dogbite, R.raw.dogbite));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.goaway, R.raw.goaway));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.oldpersonsmell, R.raw.oldpersonsmell));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.awomarenlema, R.raw.awomarenlema));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.bazijixiandehaogaodangyiyang, R.raw.bazijixiandehaogaodangyiyang));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.bierenshuonishule, R.raw.bierenshuonishule));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.buhezaoyaotongliuhewu, R.raw.buhezaoyaotongliuhewu));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.geitalianmianbuyaolian, R.raw.geitalianmianbuyaolian));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.guzilijiubuhuigennimentongliuhewu, R.raw.guzilijiubuhuigennimentongliuhewu));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.mayanimayamalelannidezui, R.raw.mayanimayamalelannidezui));
        recoverButtonAudioPairs.add(new ButtonAudioPair(R.string.mayazenmebuganlaimale, R.raw.mayazenmebuganlaimale));

        // 按钮和对应音频资源的配对 - toggle_button_lose
        List<ButtonAudioPair> loseButtonAudioPairs = new ArrayList<>();
        loseButtonAudioPairs.add(new ButtonAudioPair(R.string.bepolite, R.raw.bepolite));
        loseButtonAudioPairs.add(new ButtonAudioPair(R.string.youngman, R.raw.youngman));
        loseButtonAudioPairs.add(new ButtonAudioPair(R.string.babynofighting, R.raw.babynofighting));
        loseButtonAudioPairs.add(new ButtonAudioPair(R.string.baby, R.raw.baby));

        // 按钮和对应音频资源的配对 - toggle_button_nanasong
        List<ButtonAudioPair> nanaSongButtonAudioPairs = new ArrayList<>();
        nanaSongButtonAudioPairs.add(new ButtonAudioPair(R.string.nanasong1, R.raw.bigbeta));
        nanaSongButtonAudioPairs.add(new ButtonAudioPair(R.string.nanasong2, R.raw.bigbeta_dj));
        nanaSongButtonAudioPairs.add(new ButtonAudioPair(R.string.cangjb, R.raw.cangjb));

        // 按钮和对应音频资源的配对 - toggle_button_nanasing
        List<ButtonAudioPair> nanaSingButtonAudioPairs = new ArrayList<>();
        nanaSingButtonAudioPairs.add(new ButtonAudioPair(R.string.lovelikefirehead, R.raw.lovelikefirehead));
        nanaSingButtonAudioPairs.add(new ButtonAudioPair(R.string.lovelikefiremain, R.raw.lovelikefiremain));

        // 动态添加按钮到 buttonContainer
        addButtonsToContainer(buttonContainer, recoverButtonAudioPairs);

        // 动态添加按钮到 buttonNanaSong
        addButtonsToContainer(buttonNanaSong, nanaSongButtonAudioPairs);

        // 动态添加按钮到 buttonNanaSing
        addButtonsToContainer(buttonNanaSing, nanaSingButtonAudioPairs);

        // 动态添加按钮到 buttonLose
        addButtonsToContainer(buttonLose, loseButtonAudioPairs);

        // 找到 ExtendedFloatingActionButton
        ExtendedFloatingActionButton fab = view.findViewById(R.id.fab);
        // 设置按钮的点击事件
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAudioPlayback();
            }
        });

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

        // 在这里使用不同的插值器
        animator.setInterpolator(new AccelerateInterpolator());

        // 尝试不同的持续时间
        animator.setDuration(500); // 根据需要调整持续时间
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

        // 在这里使用不同的插值器
        animator.setInterpolator(new DecelerateInterpolator());

        // 尝试不同的持续时间
        animator.setDuration(500); // 根据需要调整持续时间
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
            button.setText(pair.buttonText);
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

    public void searchButton(String keyword) {
        Log.d("FirstFragment", "Received search keyword: " + keyword);

        ArrayList<Button> buttons = getAllButtonsInFragment();

        for (Button button : buttons) {
            if (button.getText().toString().contains(keyword)) {
                Log.d("FirstFragment", "Button with text \"" + button.getText() + "\" contains the keyword \"" + keyword + "\". Showing button.");
                // 如果按钮的文本包含搜索关键词，就显示这个按钮
                showButton(button);
            } else {
                Log.d("FirstFragment", "Button with text \"" + button.getText() + "\" does not contain the keyword \"" + keyword + "\". Hiding button.");
                // 否则，隐藏这个按钮
                hideButton(button);
            }
        }
    }


    private void findButtonsInCardViews(View view, ArrayList<Button> buttons) {
        if (view instanceof Button) {
            buttons.add((Button) view);
        } else if (view instanceof CardView) {
            CardView cardView = (CardView) view;
            ViewGroup cardViewContent = (ViewGroup) cardView.getChildAt(0); // Assuming the button is the first child of CardView

            for (int i = 0; i < cardViewContent.getChildCount(); i++) {
                findButtonsInCardViews(cardViewContent.getChildAt(i), buttons);
            }
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;

            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                findButtonsInCardViews(viewGroup.getChildAt(i), buttons);
            }
        }
    }


    public interface OnSearchListener {
        void onSearch(String keyword);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context); // 必须调用父类的方法

        if (context instanceof OnSearchListener) {
            OnSearchListener onSearchListener = (OnSearchListener) context; // 将 context 强制转换为 OnSearchListener
        } else {
            throw new RuntimeException(context.toString() + " must implement OnSearchListener");
        }
    }

    private ArrayList<Button> getAllButtonsInFragment() {
        ArrayList<Button> buttons = new ArrayList<>();

        // 获取 FirstFragment 的根视图
        View rootView = getView();

        if (rootView != null) {
            // 递归地找到所有的按钮
            findButtonsInCardViews(rootView, buttons);
        }

        return buttons;
    }


    private void findButtons(View view, ArrayList<Button> buttons) {
        if (view instanceof Button) {
            buttons.add((Button) view);
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;

            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                findButtons(viewGroup.getChildAt(i), buttons);
            }
        }
    }

    private void showButton(Button button) {
        // 将按钮设置为可见
        button.setVisibility(View.VISIBLE);
    }

    private void hideButton(Button button) {
        // 将按钮设置为不可见
        button.setVisibility(View.GONE);
    }


    private static class ButtonAudioPair {
        final int buttonText;
        final int audioResId;

        ButtonAudioPair(int buttonText, int audioResId) {
            this.buttonText = buttonText;
            this.audioResId = audioResId;
        }
    }
}