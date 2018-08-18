package com.example.rumy.todosystem;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.rumy.todosystem.views.todosCreate.TodoCreateActivity;
import com.example.rumy.todosystem.views.todosList.TodoListActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public abstract class BaseDrawerActivity extends AppCompatActivity {


    private void setupDrawer() {

        //create the drawer and remember the `Drawer` result object
        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(getDrawerToolbar())
                .addDrawerItems(
                        new PrimaryDrawerItem().withIdentifier(TodoCreateActivity.IDENTIFIER).withName("Create todo").withIcon(android.R.drawable.btn_minus),
                        new PrimaryDrawerItem().withIdentifier(TodoListActivity.IDENTIFIER).withName("Todos list").withIcon(android.R.drawable.btn_minus)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        long identifier = drawerItem.getIdentifier();

                        if (getIdentifier() == identifier) return false;

                        Intent intent = getNextIntent(identifier);
                        if (intent == null) return false;

                        startActivity(intent);
                        return true;
                    }
                })
                .build();
    }

    private Intent getNextIntent(long identifier) {
        if (identifier==TodoListActivity.IDENTIFIER) {
            return new Intent(BaseDrawerActivity.this, TodoListActivity.class);
        }else if(identifier==TodoCreateActivity.IDENTIFIER){
            return new Intent(BaseDrawerActivity.this, TodoCreateActivity.class);        }

        return null;
    }


    protected abstract long getIdentifier();

    protected abstract Toolbar getDrawerToolbar();

    @Override
    protected  void onStart(){
        super.onStart();
        setupDrawer();
    }

}
