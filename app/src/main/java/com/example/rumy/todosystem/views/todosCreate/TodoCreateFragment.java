package com.example.rumy.todosystem.views.todosCreate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rumy.todosystem.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodoCreateFragment extends android.app.Fragment {


    public TodoCreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_create, container, false);
    }

    public static TodoCreateFragment instance() {
        return new TodoCreateFragment();
    }
}
