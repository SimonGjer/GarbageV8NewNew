// Simon Gjerløv, SGJC@itu.dk
package com.bignerdranch.android.garbagev1;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DelActivity extends AppCompatActivity {

    private static final String TAG = "DelActivity";
    private static ItemsDB itemsDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del);
        itemsDB = ItemsDB.get();

        EditText et_Del_What = findViewById(R.id.what_item_del);

        Button btn_DelItem = findViewById(R.id.btn_del_item);
        btn_DelItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_Del_What = et_Del_What.getText().toString().toLowerCase().trim();

                if (txt_Del_What.length() == 0) {
                    Toast.makeText(DelActivity.this, "You have to type the item you what to delete.", Toast.LENGTH_LONG).show();
                    return;
                }
                boolean fDel = itemsDB.delItem(txt_Del_What);
                ;;; Log.d(TAG, "Bool = " + fDel);
                String txtDel;
                if (fDel) txtDel = "The item " + txt_Del_What + " has been deleted\n" + "There is now " + itemsDB.getSize() + " items in the database.";
                else txtDel = "The item " + txt_Del_What + " is not in the database";
                Toast.makeText(DelActivity.this, txtDel, Toast.LENGTH_LONG).show();
                et_Del_What.setText("");
            }
        });

        Button btn_DelBack = findViewById(R.id.btn_del_back);
        btn_DelBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }
}
