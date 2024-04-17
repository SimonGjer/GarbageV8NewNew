// Simon Gjerløv, SGJC@itu.dk
package com.bignerdranch.android.garbagev1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {

    private static ItemsDB itemsDB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        itemsDB = ItemsDB.get();
        String txt = itemsDB.getAll_txt();
        TextView tv_listAll = findViewById(R.id.list_all);
        tv_listAll.setText(txt);

        long nItem = itemsDB.getSize();
        TextView tv_listAllHead = findViewById(R.id.list_all_head);
        tv_listAllHead.setText("There are " + nItem + " elements in the list");
    }
}
