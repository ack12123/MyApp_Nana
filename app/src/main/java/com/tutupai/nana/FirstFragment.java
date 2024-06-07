package com.tutupai.nana;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.List;

public class FirstFragment extends Fragment implements MyRecyclerViewAdapter.AudioPlayer {

    private MediaPlayer mediaPlayer;
    private RecyclerView recyclerView;
    private List<ButtonAudioPairInterface> buttonAudioPairs;
    private MainActivity mainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nana, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (buttonAudioPairs == null) {
            buttonAudioPairs = ButtonAudioPairFactory.createRecoverButtonAudioPairs();
        }

        if (recyclerView == null) {
            recyclerView = view.findViewById(R.id.rvButtons);
            FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
            layoutManager.setFlexWrap(FlexWrap.WRAP);
            layoutManager.setJustifyContent(JustifyContent.FLEX_START);
            recyclerView.setLayoutManager(layoutManager);
            int space = 16;
            recyclerView.addItemDecoration(new SpacesItemDecoration(space));
            MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, buttonAudioPairs);
            recyclerView.setAdapter(adapter);
        }

        // 添加 RecyclerView 的滚动监听器
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // 检测滑动方向并应用动画
                if (dx > 0) { // 向右滑动
                    AnimationUtils.applySlideInAnimation(recyclerView, AnimationUtils.AnimationDirection.RIGHT);
                } else if (dx < 0) { // 向左滑动
                    AnimationUtils.applySlideInAnimation(recyclerView, AnimationUtils.AnimationDirection.LEFT);
                }
            }
        });
    }

    @Override
    public void playAudioFromFragment(int audioResId) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        if (mainActivity != null) {
            mainActivity.stopOtherFragmentsAudio(this);
        }
        mediaPlayer = MediaPlayer.create(getContext(), audioResId);
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
        mediaPlayer.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void stopAudio() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
