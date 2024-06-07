package com.tutupai.nana;

public class ButtonAudioPair implements ButtonAudioPairInterface {
    private final int buttonTextResId;
    private final int audioResId;

    public ButtonAudioPair(int buttonTextResId, int audioResId) {
        this.buttonTextResId = buttonTextResId;
        this.audioResId = audioResId;
    }

    @Override
    public String getButtonText() {
        // 这个方法将返回资源ID的字符串表示，用于调试或日志记录
        return Integer.toString(buttonTextResId);
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
