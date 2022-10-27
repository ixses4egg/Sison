package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.databaseUsers.ConstantsUser;

public class ProfileActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences; //новий спосіб зберігати данні
    //private final static String NAME = "name";
    private String name, startHours, workHours, wordsForYou ;


    private EditText edName111112, edWorkhours, edStarthours, edWordsForYou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edName111112 = findViewById(R.id.edName111112);
        edWorkhours = findViewById(R.id.edWorkhours);
        edStarthours = findViewById(R.id.edStarthours);
        edWordsForYou = findViewById(R.id.edWordsForYou);

    }

    private void saveConstants(String name, String wordsForYou, String startHours, String workHours) { //новий спосіб зберігати данні

        sharedPreferences = getSharedPreferences("USERS", MODE_PRIVATE);//новий спосіб зберігати данні
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ConstantsUser.NAME_USER, name);
        editor.putString(ConstantsUser.WORDS_FOR_YOU, wordsForYou);
        editor.putString(ConstantsUser.START_HOURS, startHours);
        editor.putString(ConstantsUser.WORK_HOURS, workHours);
        editor.apply();
    }


    public void onClickSave(View view) {

        name = edName111112.getText().toString();
        startHours = edStarthours.getText().toString();
        workHours = edWorkhours.getText().toString();
        wordsForYou = edWordsForYou.getText().toString();
        saveConstants(name, wordsForYou, startHours, workHours);

        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);

    }

}