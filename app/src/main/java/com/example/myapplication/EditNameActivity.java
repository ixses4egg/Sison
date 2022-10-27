package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import com.example.myapplication.databaseUsers.ConstantsUser;

public class EditNameActivity extends AppCompatActivity {
    private CalendarView cvCalendarNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);

        init();
    }

    private void init() {
        cvCalendarNote = findViewById(R.id.cvCalendarNote);
        cvCalendarNote.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String data = i2 + "." + (i1 + 1) + "." + i;
                Log.d(ConstantsUser.CALENDAR_TAG, data);

                Intent intent = new Intent(EditNameActivity.this, TargetsOfDayActivity.class);
                intent.putExtra("date", data);
                startActivity(intent);

            }
        });
    }

    public void onClickBackToMenu(View view) {
        Intent intent = new Intent(EditNameActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
