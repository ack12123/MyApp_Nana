package com.tutupai.nana;

public class ButtonAudioPair implements ButtonAudioPairInterface {
    private final int buttonTextResId;
    private final int audioResId;

    public ButtonAudioPair(int buttonTextResId, int audioResId) {
        this.buttonTextResId = buttonTextResId;
        this.audioResId = audioResId;
    }

    @Override
    public int getButtonTextResId() {
        return buttonTextResId;
    }

    @Override
    public int getAudioResId() {
        return audioResId;
    }
}
