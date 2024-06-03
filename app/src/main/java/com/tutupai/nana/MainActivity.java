package com.tutupai.nana;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.tutupai.nana.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnSearchListener {
    private FirstFragment firstFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 使用binding来设置ContentView
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 检查深色模式
        int nightModeFlags = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;

        if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.secondary));
            // 设置状态栏时钟颜色为白色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.secondary));
            // 设置状态栏时钟颜色为黑色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.secondary));

        // 初始化搜索框
        EditText searchInput = findViewById(R.id.search_input);

        // 获取 FirstFragment 的实例
        firstFragment = (FirstFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        // 设置搜索框的监听器
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 当用户输入文字时，调用 FirstFragment 中的搜索按钮方法，并传递搜索关键词
                Log.d("MainActivity", "Search keyword: " + charSequence.toString());
                if (firstFragment != null) {
                    firstFragment.searchButton(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        // 禁用ActionBarDrawerToggle的图标
        toggle.setDrawerIndicatorEnabled(false);

        // 将DrawerLayout的监听器设置为ActionBarDrawerToggle
        drawer.addDrawerListener(toggle);
        // 同步汉堡菜单图标的状态
        toggle.syncState();

        // 为自定义汉堡菜单图标设置点击事件
        ImageButton menuIcon = findViewById(R.id.menu_icon);
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 打开或关闭抽屉
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });


        // 设置BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation); // 确保这里的ID与布局文件中的ID匹配
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    selectedFragment = new FirstFragment();
                    firstFragment = (FirstFragment) selectedFragment;
                } else if (itemId == R.id.action_photo) {
                    selectedFragment = new NAPhoto();
                } else if (itemId == R.id.action_profile) {
                    selectedFragment = new About();
                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }
                return true;
            }
        });

        // 默认显示第一个Fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new FirstFragment())
                    .commit();
        }
    }


    @Override
    public void onSearch(String keyword) {
        if (firstFragment != null) {
            firstFragment.searchButton(keyword);
        }
    }
}

