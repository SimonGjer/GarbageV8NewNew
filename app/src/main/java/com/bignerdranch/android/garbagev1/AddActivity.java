// Simon Gjerløv, SGJC@itu.dk
package com.bignerdranch.android.garbagev1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private static ItemsDB itemsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        itemsDB = ItemsDB.get();

        EditText et_What = findViewById(R.id.what_item);
        EditText et_Where = findViewById(R.id.where_to_put);

        Button btn_AddItem = findViewById(R.id.btn_add_item);
        btn_AddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_what = et_What.getText().toString();
                String txt_where = et_Where.getText().toString();

                if (txt_what.length() == 0 || txt_where.length() == 0) {
                    Toast.makeText(AddActivity.this, "You have to fill in text in the two fields.", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    itemsDB.addItem(txt_what, txt_where);
                    Toast.makeText(AddActivity.this, "There is now " + itemsDB.getSize() + " elements to sort.", Toast.LENGTH_LONG).show();
                    et_What.setText("");
                    et_Where.setText("");
                }
            }
        });
    }
}