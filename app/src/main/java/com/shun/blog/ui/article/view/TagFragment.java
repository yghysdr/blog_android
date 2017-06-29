package com.shun.blog.ui.article.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shun.blog.R;

/**
 * 归档
 */
public class TagFragment extends Fragment {

    public TagFragment() {
        // Required empty public constructor
    }

    public static TagFragment newInstance() {
        return new TagFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_archive, container, false);
    }

}
