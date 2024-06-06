package com.tutupai.nana;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewComponent extends LinearLayout {

    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;

    public MyRecyclerViewComponent(Context context) {
        super(context);
        init(context);
    }

    public MyRecyclerViewComponent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        addView(recyclerView, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    public void setAdapter(MyRecyclerViewAdapter adapter) {
        this.adapter = adapter;
        recyclerView.setAdapter(adapter);
    }

    public void playAudio(int audioResId) {
        MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), audioResId);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        mediaPlayer.start();
    }

    public void setAdapterAndData(MyRecyclerViewAdapter adapter, List<ButtonAudioPairInterface> data) {
        this.adapter = adapter;
        recyclerView.setAdapter(adapter);
    }

    public MyRecyclerViewAdapter getAdapter() {
        return adapter;
    }
}
