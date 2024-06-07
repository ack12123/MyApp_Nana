package com.tutupai.nana;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.tutupai.nana.MainActivity;
import com.tutupai.nana.R;

import java.util.ArrayList;
import java.util.List;

public class SearchNanaFragment extends Fragment {
    private static final String ARG_SEARCH_TEXT = "search_text";

    public static SearchNanaFragment newInstance(String searchText) {
        SearchNanaFragment fragment = new SearchNanaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SEARCH_TEXT, searchText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        LinearLayout resultsLayout = view.findViewById(R.id.results_layout);

        String searchText = getArguments().getString(ARG_SEARCH_TEXT);

        // 获取所有按钮并筛选匹配的按钮（这里需要实现获取所有按钮的逻辑）
        List<Button> buttons = getAllButtons();
        for (Button button : buttons) {
            if (button.getText().toString().contains(searchText)) {
                TextView textView = new TextView(requireContext());
                textView.setText(button.getText().toString());
                resultsLayout.addView(textView);
            }
        }

        return view;
    }

    private List<Button> getAllButtons() {
        // 实现获取所有按钮的逻辑
        // ...
        return new ArrayList<>();
    }
}