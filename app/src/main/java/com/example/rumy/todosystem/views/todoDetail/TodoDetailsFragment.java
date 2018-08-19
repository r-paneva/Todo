package com.example.rumy.todosystem.views.todoDetail;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.rumy.todosystem.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class TodoDetailsFragment extends android.app.Fragment {

    private String mTodoTitle;
    private String mTodoDescription;
    private TextView mTodoTextViewTitle;
    private TextView mTodoTextViewDescripton;
    private Button mTodoSetDoneButton;
    private boolean mTodoStat;
    private String pid;
    private Button mTodoDeleteButton;


    public TodoDetailsFragment() {
        // Required empty public constructor
    }

    public void setTodo(String pID, String title, String description, boolean isActive) {
        pid = pID;
        mTodoStat = isActive;
        mTodoTitle = title;
        mTodoDescription = description;
        if (mTodoTextViewTitle == null) {
            return;
        }
        mTodoTextViewTitle.setText(title.toUpperCase());
        mTodoTextViewDescripton.setText(description.toLowerCase());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo_details, container, false);

        mTodoTextViewTitle = view.findViewById(R.id.tv_todo_title);
        mTodoTextViewTitle.setText(mTodoTitle);

        mTodoTextViewDescripton = view.findViewById(R.id.tv_todo_description);
        mTodoTextViewDescripton.setText(mTodoDescription);

        mTodoSetDoneButton = view.findViewById(R.id.btn_set_todo_done);
        ButtonStatText(mTodoStat);

        mTodoSetDoneButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseFirestore.getInstance()
                                .collection("todos")
                                .document(pid)
                                .update("isActive", !mTodoStat);

                        mTodoStat = !mTodoStat;
                        ButtonStatText(mTodoStat);
                    }
                });

//        mTodoDeleteButton = view.findViewById(R.id.btn_delete_todo);
//        mTodoSetDoneButton.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        FirebaseFirestore.getInstance()
//                                .collection("todos")
//                                .document(pid)
//                                .delete();
//                    }
//                }
//        );

        return view;
    }

    private void buttonClicked(View view) {


    }

    public void ButtonStatText(boolean todoStat) {
        String text = "";
        if (todoStat) text = "To do";
        else text = "Done";
        mTodoSetDoneButton.setText(text);
    }

    public static TodoDetailsFragment instance() {
        return new TodoDetailsFragment();
    }

}
