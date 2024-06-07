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

public class NanaLose extends Fragment implements MyRecyclerViewAdapter.AudioPlayer {

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
        return inflater.inflate(R.layout.fragment_lose, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Only initialize data and setup RecyclerView if not already done
        if (buttonAudioPairs == null) {
            buttonAudioPairs = ButtonAudioPairFactory.createLoseButtonAudioPairs();
        }

        if (recyclerView == null) {
            recyclerView = view.findViewById(R.id.rvButtons);

            // 使用 FlexboxLayoutManager
            FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
            layoutManager.setFlexWrap(FlexWrap.WRAP); // 允许换行
            layoutManager.setJustifyContent(JustifyContent.FLEX_START); // 按起始位置排列
            recyclerView.setLayoutManager(layoutManager);

            // 设置自定义的 ItemDecoration
            int space = 16; // 设置间距大小
            recyclerView.addItemDecoration(new SpacesItemDecoration(space));

            MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, buttonAudioPairs);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void playAudioFromFragment(int audioResId) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
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

    public void stopAudio() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}