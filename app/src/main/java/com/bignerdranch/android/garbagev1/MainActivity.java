// Simon Gjerløv, SGJC@itu.dk
// Mobile App Development 2024 - Week 01, 7 feb.

package com.bignerdranch.android.garbagev1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bignerdranch.android.garbagev1.database.DBCreate;

public class MainActivity extends AppCompatActivity {

    private ItemsDB itemsDB;

    //private static DBCreate mDBCreate = null;
    private static SQLiteDatabase mSQLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ItemsDB.setContext(MainActivity.this);
        //itemsDB = ItemsDB.get();
        //itemsDB.fillItemsDB();

        ItemsDB.initialize(MainActivity.this);

        ;;;Log.i("GarbageV8", "OK 1");
        mSQLiteDatabase = new DBCreate(this).getWritableDatabase();
        ;;;Log.i("GarbageV8", "OK 2");



        EditText etGarbage = findViewById(R.id.et_garbage);

        Button btnWhere = findViewById(R.id.btn_where);
        btnWhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etGarbage.setBackgroundColor(Color.parseColor("#FFFFFF"));
                String txt_what = String.valueOf(etGarbage.getText()); // Why this syntax?
                String txt_message = itemsDB.getWhere(txt_what);
                etGarbage.setText(txt_message);
            }
        });

        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        Button btnListAll = findViewById(R.id.btn_list_all);
        btnListAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        Button btnDel = findViewById(R.id.btn_delete);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DelActivity.class);
                startActivity(intent);
            }
        });
    }
}