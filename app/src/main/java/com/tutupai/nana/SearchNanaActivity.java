package com.tutupai.nana;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SearchNanaActivity extends AppCompatActivity implements MyRecyclerViewAdapter.AudioPlayer {

    private List<ButtonAudioPairInterface> allButtons;
    private MyRecyclerViewAdapter buttonAdapter;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_nana);

        // 设置状态栏颜色
        int nightModeFlags = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.secondary));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.secondary));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        // 初始化 RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvButtons);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 使用 FlexboxLayoutManager 替代 LinearLayoutManager
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexWrap(FlexWrap.WRAP); // 允许换行
        layoutManager.setJustifyContent(JustifyContent.FLEX_START); // 按起始位置排列
        recyclerView.setLayoutManager(layoutManager);

        // 设置自定义的 ItemDecoration
        int space = 16; // 设置间距大小
        recyclerView.addItemDecoration(new SpacesItemDecoration(space));


        // 创建 MyRecyclerViewAdapter 实例
        buttonAdapter = new MyRecyclerViewAdapter(this, this, new ArrayList<>());

        // 设置 RecyclerView 的适配器
        recyclerView.setAdapter(buttonAdapter);

        // 获取所有按钮数据
        allButtons = ButtonAudioPairFactory.getAllButtons();

        // 获取搜索输入框
        EditText searchInput = findViewById(R.id.search_input);

        // 设置搜索输入框的监听器
        searchInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String searchText = searchInput.getText().toString();
                updateRecyclerView(searchText);
                return true;
            }
            return false;
        });

        // 初始更新适配器的数据为空列表
        updateRecyclerView(null);

        // 设置 ExtendedFloatingActionButton 的点击监听器
        ExtendedFloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> stopAudio());
    }

    private void updateRecyclerView(String searchText) {
        List<ButtonAudioPairInterface> matchedButtons = searchButtons(searchText);
        buttonAdapter.setData(matchedButtons);
    }

    private List<ButtonAudioPairInterface> searchButtons(String searchText) {
        List<ButtonAudioPairInterface> matchedButtons = new ArrayList<>();
        if (searchText != null && !searchText.isEmpty()) {
            for (ButtonAudioPairInterface button : allButtons) {
                String buttonText = getString(button.getButtonTextResId());
                if (buttonText.toLowerCase().contains(searchText.toLowerCase())) {
                    matchedButtons.add(button);
                }
            }
        }
        return matchedButtons;
    }

    @Override
    public void playAudioFromFragment(int audioResId) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, audioResId);
        mediaPlayer.start();
    }

    @Override
    public void stopAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
