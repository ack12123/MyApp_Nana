package com.tutupai.nana;

import android.os.Bundle;
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
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.tutupai.nana.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private FirstFragment firstFragment;
    private ViewPager2 viewPager;
    private MyFragmentStateAdapter pagerAdapter;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 使用binding来设置ContentView
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 初始化ViewPager2和Adapter
        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new MyFragmentStateAdapter(this);

        // 添加Fragments到Adapter
        pagerAdapter.addFragment(new FirstFragment());
        pagerAdapter.addFragment(new NanaLose());
        pagerAdapter.addFragment(new NanaSong());

        viewPager.setAdapter(pagerAdapter);


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
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    viewPager.setCurrentItem(0);
                    return true;
                } else if (itemId == R.id.action_photo) {
                    viewPager.setCurrentItem(1);
                    return true;
                } else if (itemId == R.id.action_profile) {
                    viewPager.setCurrentItem(2);
                    return true;
                }
                return false;
            }
        });

        // 设置ViewPager2的页面切换监听器
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });

        // 默认显示第一个Fragment
        if (savedInstanceState == null) {
            viewPager.setCurrentItem(0);
        }
    }
}

