package com.example.rumy.todosystem.views.todosCreate;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rumy.todosystem.R;
import com.example.rumy.todosystem.models.Todo;
import com.google.firebase.firestore.FirebaseFirestore;


public class TodoCreateFragment extends android.app.Fragment {


    private TextInputLayout mTodoInputTitle;
    private TextInputLayout mTodoInputDescription;
    private Button mTodoInputButton;
    private FirebaseFirestore db;

    public TodoCreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo_create, container, false);

        mTodoInputTitle = view.findViewById(R.id.et_input_title);
        mTodoInputDescription = view.findViewById(R.id.et_input_description);

        mTodoInputButton = view.findViewById(R.id.btn_create_todo);
//        clearText(mTodoInputTitle);
//        clearText(mTodoInputDescription);

        mTodoInputButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (validateTitle()) {
                            db = FirebaseFirestore.getInstance();
                            Todo item = new Todo(
                                    mTodoInputTitle.getEditText().getText().toString().trim(),
                                    mTodoInputDescription.getEditText().getText().toString(),
                                    true);
                            db.collection("todos").add(item);
                            confirmInput(v);
                            clearText(mTodoInputTitle);
                            clearText(mTodoInputDescription);
                            EditText editText = view.findViewById(R.id.et_title);
                            editText.setText("");
                            editText = view.findViewById(R.id.et_description);
                            editText.setText("");
                        }
                    }
                });

        return view;
    }

    private void clearText(TextInputLayout text) {
        text.setError(null);
        text.clearFocus();
    }

    private boolean validateTitle() {
        String titleInput = mTodoInputTitle.getEditText().getText().toString();

        if (titleInput.trim().isEmpty()) {
            mTodoInputTitle.setError("Field can't be empty");
            return false;
        } else if (titleInput.length() > 20) {
            mTodoInputTitle.setError("Title too long");
            return false;
        } else {
            mTodoInputTitle.setError(null);
            return true;
        }
    }

    public void confirmInput(View view) {
        if (!validateTitle()) {
            return;
        }
        String input = "Title: " + mTodoInputTitle.getEditText().getText().toString();
        input += "\n";
        input += "Description: " + mTodoInputDescription.getEditText().getText().toString();

        Toast.makeText(getContext(), input, Toast.LENGTH_LONG).show();
    }

    public static TodoCreateFragment instance() {
        return new TodoCreateFragment();
    }
}
