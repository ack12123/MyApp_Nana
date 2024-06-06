package com.tutupai.nana;


import java.util.ArrayList;
import java.util.List;

public class ButtonAudioPairNanaSong {

    public static List<NanaSong.ButtonAudioPair> createNanaSongButtonAudioPairs() {
        List<NanaSong.ButtonAudioPair> buttonAudioPairs = new ArrayList<>();
        buttonAudioPairs.add(new NanaSong.ButtonAudioPair(R.string.nanasong1, R.raw.bigbeta));
        buttonAudioPairs.add(new NanaSong.ButtonAudioPair(R.string.nanasong2, R.raw.bigbeta_dj));
        buttonAudioPairs.add(new NanaSong.ButtonAudioPair(R.string.cangjb, R.raw.cangjb));
        return buttonAudioPairs;
    }

    public static List<NanaSong.ButtonAudioPair> createNanaSingButtonAudioPairs() {
        List<NanaSong.ButtonAudioPair> buttonAudioPairs = new ArrayList<>();
        buttonAudioPairs.add(new NanaSong.ButtonAudioPair(R.string.lovelikefirehead, R.raw.lovelikefirehead));
        buttonAudioPairs.add(new NanaSong.ButtonAudioPair(R.string.lovelikefiremain, R.raw.lovelikefiremain));
        return buttonAudioPairs;
    }
}
