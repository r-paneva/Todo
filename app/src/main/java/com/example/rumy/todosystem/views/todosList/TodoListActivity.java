package com.example.rumy.todosystem.views.todosList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.rumy.todosystem.BaseDrawerActivity;
import com.example.rumy.todosystem.R;
import com.example.rumy.todosystem.uiutility.Navigator;
import com.example.rumy.todosystem.views.todoDetail.TodoDetailsActivity;
import com.example.rumy.todosystem.views.todoDetail.TodoDetailsFragment;

public class TodoListActivity extends BaseDrawerActivity implements Navigator {

    public static final long IDENTIFIER = 1;
    public TodoListFragment mTodoListFragment;
    private TodoDetailsFragment mTodoDetailsFragment;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        mTodoListFragment = TodoListFragment.instance();
        mTodoListFragment.setNavigator(this);
        mToolbar = findViewById(R.id.drawer_toolbar);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mTodoListFragment)
                .commit();
    }

    @Override
    protected long getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    protected Toolbar getDrawerToolbar() {
        return mToolbar;
    }

    @Override
    public void navigateWith(String todo) {
        Intent intent = new Intent(
                this,
                TodoDetailsActivity.class
        );

        intent.putExtra("TODO_TITLE", todo);
        startActivity(intent);

    }
}
