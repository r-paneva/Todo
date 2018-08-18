package com.example.rumy.todosystem.views.todosList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rumy.todosystem.R;
import com.example.rumy.todosystem.models.Todo;
import com.example.rumy.todosystem.repositories.FirebaseRepository;
import com.example.rumy.todosystem.repositories.base.Repository;
import com.example.rumy.todosystem.uiutility.Navigator;
import com.google.firebase.firestore.FirebaseFirestore;


public class TodoListFragment extends android.app.Fragment implements AdapterView.OnItemClickListener {


    private Navigator mNavigator;
    private ListView mTodoListView;
    private ArrayAdapter<String> mTodoAdapter;
    private FirebaseFirestore mDb;
    private Repository<Todo> mTodoRepository;

    public TodoListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);


        mDb = FirebaseFirestore.getInstance();

        mTodoListView = view.findViewById(R.id.lv_todos);
        mTodoAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);

        mTodoListView.setAdapter(mTodoAdapter);
        mTodoListView.setOnItemClickListener(this);
        mTodoRepository = new FirebaseRepository<>(Todo.class);

        mTodoRepository.getAll(todos -> {
            for (Todo todo : todos) {
                mTodoAdapter.add(todo.title);
            }

        });

        return view;
    }

    public static TodoListFragment instance() {
        return new TodoListFragment();
    }

    public void setNavigator(Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String todo = mTodoAdapter.getItem(i);
        mNavigator.navigateWith(todo);
    }

}
