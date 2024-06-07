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

public class NanaSong extends Fragment implements MyRecyclerViewAdapter.AudioPlayer {

    private MediaPlayer mediaPlayer;
    private RecyclerView recyclerViewSing;
    private RecyclerView recyclerViewSong;
    private RecyclerView recyclerViewAI;
    private List<ButtonAudioPairInterface> buttonAudioPairsSing;
    private List<ButtonAudioPairInterface> buttonAudioPairsSong;
    private List<ButtonAudioPairInterface> buttonAudioPairsAI;
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
        return inflater.inflate(R.layout.fragment_nanasong, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 初始化数据和设置 RecyclerView
        if (buttonAudioPairsSing == null) {
            buttonAudioPairsSing = ButtonAudioPairFactory.createNanaSingButtonAudioPairs();
        }

        if (buttonAudioPairsSong == null) {
            buttonAudioPairsSong = ButtonAudioPairFactory.createNanaSongButtonAudioPairs();
        }

        if (buttonAudioPairsAI == null) {
            buttonAudioPairsAI = ButtonAudioPairFactory.createNanaAIButtonAudioPairs();
        }


        // 初始化第一个 RecyclerView
        recyclerViewSing = view.findViewById(R.id.rvButtons);
        if (recyclerViewSing != null) {
            FlexboxLayoutManager layoutManagerSing = new FlexboxLayoutManager(getContext());
            layoutManagerSing.setFlexWrap(FlexWrap.WRAP);
            layoutManagerSing.setJustifyContent(JustifyContent.FLEX_START);
            recyclerViewSing.setLayoutManager(layoutManagerSing);

            int space = 16;
            recyclerViewSing.addItemDecoration(new SpacesItemDecoration(space));

            MyRecyclerViewAdapter adapterSing = new MyRecyclerViewAdapter(this, buttonAudioPairsSing);
            recyclerViewSing.setAdapter(adapterSing);
        }

        // 初始化第二个 RecyclerView
        recyclerViewSong = view.findViewById(R.id.rvButtons2);
        if (recyclerViewSong != null) {
            FlexboxLayoutManager layoutManagerSong = new FlexboxLayoutManager(getContext());
            layoutManagerSong.setFlexWrap(FlexWrap.WRAP);
            layoutManagerSong.setJustifyContent(JustifyContent.FLEX_START);
            recyclerViewSong.setLayoutManager(layoutManagerSong);

            int space = 16;
            recyclerViewSong.addItemDecoration(new SpacesItemDecoration(space));

            MyRecyclerViewAdapter adapterSong = new MyRecyclerViewAdapter(this, buttonAudioPairsSong);
            recyclerViewSong.setAdapter(adapterSong);
        }

        // 初始化第三个 RecyclerView
        recyclerViewAI = view.findViewById(R.id.rvButtons3);
        if (recyclerViewAI != null) {
            FlexboxLayoutManager layoutManagerSong = new FlexboxLayoutManager(getContext());
            layoutManagerSong.setFlexWrap(FlexWrap.WRAP);
            layoutManagerSong.setJustifyContent(JustifyContent.FLEX_START);
            recyclerViewAI.setLayoutManager(layoutManagerSong);

            int space = 16;
            recyclerViewAI.addItemDecoration(new SpacesItemDecoration(space));

            MyRecyclerViewAdapter adapterAI = new MyRecyclerViewAdapter(this, buttonAudioPairsAI);
            recyclerViewAI.setAdapter(adapterAI);
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
