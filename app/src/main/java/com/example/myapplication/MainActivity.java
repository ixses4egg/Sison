package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.databaseUsers.ConstantsUser;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences; //новий спосіб зберігати данні
    private String wordForYou;

    private TextView tvNameTable;
    private RecyclerView rcView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        tvNameTable = findViewById(R.id.tvNameTable);

        sharedPreferences = getSharedPreferences("USERS", MODE_PRIVATE);//новий спосіб зберігати данні
        wordForYou = sharedPreferences.getString(ConstantsUser.WORDS_FOR_YOU,"Enter your name");
        tvNameTable.setText(wordForYou);

    }

    public void onClickToTargets(View view) {
        Intent intent = new Intent(MainActivity.this, TargetActivity.class);
        startActivity(intent);
    }

    public void onClickEditBase(View view) {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

}