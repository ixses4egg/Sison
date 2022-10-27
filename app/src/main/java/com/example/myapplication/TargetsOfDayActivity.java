package com.example.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DataBase.MyConstants;
import com.example.myapplication.DataBase.MyDataBaseManager;
import com.example.myapplication.adapter.ListItem;

public class TargetsOfDayActivity extends AppCompatActivity {

    private FloatingActionButton fbClickSave;
    private EditText edDescOfDay;
    private MyDataBaseManager myDataBaseManager;
    private boolean isEditState = true;
    private ListItem item;

    private TextView tvDataTitle;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_targets_of_day);

        init();
        getMyIntents();
    }
    private void init() {
        myDataBaseManager = new MyDataBaseManager(this);

        edDescOfDay = findViewById(R.id.edDescOfDay);
        fbClickSave = findViewById(R.id.fbClickSave);

        tvDataTitle = findViewById(R.id.tvDataTitle);
        Intent incomingIntent = getIntent();
        title = incomingIntent.getStringExtra("date");
        tvDataTitle.setText(title);
    }
    @Override
    protected void onResume() {
        super.onResume();
        myDataBaseManager.openDataBase();
    }

    private void getMyIntents() {

        Intent i = getIntent();
        if ( i != null) {
            item = (ListItem) i.getSerializableExtra(MyConstants.LIST_ITEM_INTENT);

            isEditState = i.getBooleanExtra(MyConstants.EDIT_STATE, true);

            if (!isEditState) {

                tvDataTitle.setText(item.getTitle());
                edDescOfDay.setText(item.getDesc());

            }
        }
    }

    public void onClickSave(View view) {

        final String desc = edDescOfDay.getText().toString();

        if (title.equals("") || desc.equals("")) {
            Toast.makeText(this, R.string.text_empty, Toast.LENGTH_SHORT).show();
        } else {

            if (isEditState) {

                myDataBaseManager.insertToDb(title, desc);
                Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();

            } else {

                myDataBaseManager.updateItem(title, desc, item.getId());
                Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();

            }

            myDataBaseManager.closeDb();
            finish();

            Intent intent = new Intent(TargetsOfDayActivity.this, TargetActivity.class);
            startActivity(intent);
        }
    }
}
