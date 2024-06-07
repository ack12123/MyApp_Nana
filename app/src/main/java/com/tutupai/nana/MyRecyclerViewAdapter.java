package com.tutupai.nana;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private final List<ButtonAudioPairInterface> mData;
    private final LayoutInflater mInflater;
    private final AudioPlayer audioPlayer;

    // data is passed into the constructor
    MyRecyclerViewAdapter(AudioPlayer audioPlayer, List<ButtonAudioPairInterface> data) {
        this.mInflater = LayoutInflater.from(((Fragment) audioPlayer).getContext());
        this.mData = data;
        this.audioPlayer = audioPlayer;
    }

    interface AudioPlayer {
        void playAudioFromFragment(int audioResId);
        void stopAudio();
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ButtonAudioPairInterface pair = mData.get(position);

        // 设置按钮文本
        holder.myButton.setText(holder.myButton.getContext().getResources().getString(pair.getButtonTextResId()));

        // 设置按钮点击事件
        holder.myButton.setOnClickListener(v -> audioPlayer.playAudioFromFragment(pair.getAudioResId()));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialButton myButton;

        ViewHolder(View itemView) {
            super(itemView);
            myButton = itemView.findViewById(R.id.btn_item);
        }
    }
}
