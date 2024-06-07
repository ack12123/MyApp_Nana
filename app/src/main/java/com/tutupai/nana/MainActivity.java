package com.tutupai.nana;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.tutupai.nana.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;
    private final List<MyRecyclerViewAdapter.AudioPlayer> audioPlayerFragments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewPager = findViewById(R.id.viewPager);
        MyFragmentStateAdapter pagerAdapter = new MyFragmentStateAdapter(this);

        FirstFragment firstFragment = new FirstFragment();
        NanaLose nanaLose = new NanaLose();
        NanaSong nanaSong = new NanaSong();

        pagerAdapter.addFragment(firstFragment);
        pagerAdapter.addFragment(nanaLose);
        pagerAdapter.addFragment(nanaSong);

        // 设置状态栏颜色
        int nightModeFlags = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.secondary));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.secondary));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        viewPager.setAdapter(pagerAdapter);

        // 添加实现了 AudioPlayer 的 Fragment 实例
        audioPlayerFragments.add(firstFragment);
        audioPlayerFragments.add(nanaLose);
        audioPlayerFragments.add(nanaSong);

        EditText searchButton = findViewById(R.id.search_input);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchInput.getText().toString();
                SearchNanaFragment fragment = SearchNanaFragment.newInstance(searchText);
                openFragment(fragment);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ImageButton menuIcon = findViewById(R.id.menu_icon);
        menuIcon.setOnClickListener(view -> {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        ExtendedFloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> stopAllAudio());

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
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
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });

        if (savedInstanceState == null) {
            viewPager.setCurrentItem(0);
        }
    }

    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void stopAllAudio() {
        for (MyRecyclerViewAdapter.AudioPlayer audioPlayerFragment : audioPlayerFragments) {
            audioPlayerFragment.stopAudio();
        }
    }

    public void stopOtherFragmentsAudio(MyRecyclerViewAdapter.AudioPlayer currentFragment) {
        for (MyRecyclerViewAdapter.AudioPlayer audioPlayerFragment : audioPlayerFragments) {
            if (audioPlayerFragment != currentFragment) {
                audioPlayerFragment.stopAudio();
            }
        }
    }
}
