package com.example.rumy.todosystem.views.todoDetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.example.rumy.todosystem.BaseDrawerActivity;
import com.example.rumy.todosystem.R;
import com.example.rumy.todosystem.models.Todo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


public class TodoDetailsActivity extends BaseDrawerActivity {

    private TodoDetailsFragment mTodoDetailFragment;
    private FirebaseFirestore db;
    private String todo_description;
    private boolean todo_isActive;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_details);
        mToolbar = findViewById(R.id.drawer_toolbar);

        Intent intent = getIntent();
        String todo_title = intent.getStringExtra("TODO_TITLE");
        final String[] pid = {""};

        mTodoDetailFragment = TodoDetailsFragment.instance();

        db = FirebaseFirestore.getInstance();
        db.collection("todos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot document : task.getResult()) {

                            if (document.get("title").equals(todo_title)) {
                                todo_description = document.getString("description");
                                todo_isActive = document.getBoolean("isActive");
                                pid[0] = document.getId();
                                mTodoDetailFragment.setTodo(pid[0], todo_title, todo_description, todo_isActive);
                                return;
                            }
                        }
                    }
                });

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mTodoDetailFragment)
                .commit();
    }

    @Override
    protected long getIdentifier() {
        return 0;
    }

    @Override
    protected Toolbar getDrawerToolbar() {
        return mToolbar;
    }
}
