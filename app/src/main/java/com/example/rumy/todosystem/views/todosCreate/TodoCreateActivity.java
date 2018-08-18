package com.example.rumy.todosystem.views.todosCreate;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.rumy.todosystem.BaseDrawerActivity;
import com.example.rumy.todosystem.R;
import com.example.rumy.todosystem.models.Todo;
import com.google.firebase.firestore.FirebaseFirestore;

public class TodoCreateActivity extends BaseDrawerActivity {

    public static final long IDENTIFIER = 2;
    private FirebaseFirestore db;
    private Toolbar mToolbar;
    private TodoCreateFragment mTodoCreateFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_create);

        mToolbar = findViewById(R.id.drawer_toolbar);
        mTodoCreateFragment=TodoCreateFragment.instance();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mTodoCreateFragment)
                .commit();

//        db = FirebaseFirestore.getInstance();
//        Todo item = new Todo("newTodos", "some description blablabla", false);
//        db.collection("todos").add(item);

    }

    @Override
    protected long getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    protected Toolbar getDrawerToolbar() {
        return mToolbar;
    }
}

