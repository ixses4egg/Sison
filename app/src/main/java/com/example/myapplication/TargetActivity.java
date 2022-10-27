package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.myapplication.DataBase.MyDataBaseManager;
import com.example.myapplication.adapter.ListItem;
import com.example.myapplication.adapter.MainAdapter;
import com.example.myapplication.databaseUsers.ConstantsUser;

import java.util.List;

public class TargetActivity extends AppCompatActivity {

    private MyDataBaseManager myDataBaseManager;
    private SharedPreferences sharedPreferences;
    private String name;
    private TextView tvTitleApp;

    private RecyclerView rcView;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        init();
    }

    private void init() {
        myDataBaseManager = new MyDataBaseManager(this);
        rcView = findViewById(R.id.rcView);
        mainAdapter = new MainAdapter(this);

        tvTitleApp = findViewById(R.id.tvTitleApp);

        rcView.setLayoutManager(new LinearLayoutManager(this));
        getItemTouchHelper().attachToRecyclerView(rcView);
        rcView.setAdapter(mainAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        myDataBaseManager.openDataBase();
        mainAdapter.updateAdapter(myDataBaseManager.getFromDb());

        sharedPreferences = getSharedPreferences("USERS", MODE_PRIVATE);//новий спосіб зберігати данні
        name = sharedPreferences.getString(ConstantsUser.NAME_USER,"Name");
        String startHours = sharedPreferences.getString(ConstantsUser.START_HOURS,"0");
        String workHours = sharedPreferences.getString(ConstantsUser.WORK_HOURS,"0");
        String txt = name + "        " + startHours + "  from  " + workHours;
        tvTitleApp.setText(txt);
    }

    public void onClickAdd(View view) {

        Intent intent = new Intent(TargetActivity.this, EditNameActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDataBaseManager.closeDb();
    }

    private ItemTouchHelper getItemTouchHelper() {
        return new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT |  ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                mainAdapter.removeItem(viewHolder.getAdapterPosition(), myDataBaseManager);
            }
        });
    }

    public void onClickMenu(View view) {
        Intent intent = new Intent(TargetActivity.this, MainActivity.class);
        startActivity(intent);
    }
}