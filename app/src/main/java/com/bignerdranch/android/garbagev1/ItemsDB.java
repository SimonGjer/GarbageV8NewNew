// Simon Gjerløv, SGJC@itu.dk
// https://www.geeksforgeeks.org/singleton-class-java/
package com.bignerdranch.android.garbagev1;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import com.bignerdranch.android.garbagev1.database.DBCreate;
import com.bignerdranch.android.garbagev1.database.ItemsDbSchema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import android.database.sqlite.SQLiteDatabase;


public class ItemsDB { // Singleton class

    private static SQLiteDatabase db;

    private static long nItem;
    //private static final String TAG = "ItemsDB";
    private static ItemsDB sItemsDB;
    private static Context context;
    final static String filename = "garbage.txt"; // file located in "assets"-folder
    private HashMap<String, String> itemsDB = new HashMap<String, String>();

    private ItemsDB() {
    }// private constructor

    public static void initialize(Context context) {
        if (db == null) {
            db = new DBCreate(context.getApplicationContext()).getWritableDatabase();
        }
    }

    public static void setContext(Context context) {
        ItemsDB.context = context;
    }

    public static ItemsDB get() {
        if (sItemsDB == null) sItemsDB = new ItemsDB();
        return sItemsDB;
    }

    public void addItem(String txt_What, String txt_Where) {
        /* itemsDB.put(txt_What.toLowerCase().trim(), txt_Where.toLowerCase().trim()); */
        txt_What = txt_What.toLowerCase();
        txt_Where = txt_Where.toLowerCase();
        ;;; Log.i("GarbageV8", "addItem: " + txt_What + " " + txt_Where);
        ;;; Log.i("GarbageV8", "db = " + db);
        if (existItem(txt_What)) delItem(txt_What);
        db.execSQL("INSERT INTO Items (what, whereC) VALUES ('" + txt_What + "', '" + txt_Where + "')");
    }

    public boolean existItem(String txt_What) {
        txt_What = txt_What.toLowerCase();
        ;;; Log.i("GarbageV8", "existItem: " + txt_What);
        Cursor cursor = db.rawQuery("SELECT * FROM " + ItemsDbSchema.ItemTable.NAME + " WHERE WHAT = '" + txt_What + "'", null);
        ;;; Log.i("GarbageV8", "cursor.getCount() = " + cursor.getCount());
        boolean fExist = cursor.getCount() > 0;
        cursor.close();
        return fExist;
    }

    public boolean delItem(String txt_What) {
        txt_What = txt_What.toLowerCase();
        db.execSQL("DELETE FROM Items WHERE what = '" + txt_What + "'");
        return true;

        /*
        txt_What = txt_What.toLowerCase().trim(); // Technically not necessary
        Log.d(TAG, "ContainsKey: " + itemsDB.containsKey(txt_What));
        if (! itemsDB.containsKey(txt_What)) return false;
        itemsDB.remove(txt_What);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Map.Entry<String, String> item : itemsDB.entrySet()) {
                writer.write(item.getKey() + ", " + item.getValue());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            Log.wtf(TAG, "IOException: " + e);
            ;;; return true; // *** Temporary hack as the file "garbage.txt" is read-only and cannot be overwritten.
        }
        return true;

         */
    }

    public long getSize() {
        nItem = DatabaseUtils.queryNumEntries(db, ItemsDbSchema.ItemTable.NAME);
        return nItem;
        //return itemsDB.size();
    }

    public void fillItemsDB() {
        try {
            //if (Math.random() < 2.0) return;
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
            String line;
            while ((line = reader.readLine()) != null) {
                String txtWhat = line.split(",")[0].trim();
                String txtWhere = line.split(",")[1].trim();
                itemsDB.put(txtWhat, txtWhere);
            }
            reader.close();
        } catch (IOException e) {
        }
    }

    public String getWhere(String txt_What) {
        final String txt_notFound = "not found";
        final String txt_insert = " should be placed in: ";
        final String txtLowC_what = txt_What.toLowerCase().trim();

        final String txt_Where = itemsDB.get(txtLowC_what);
        if (txt_Where != null) return txt_What + txt_insert + txt_Where;
        else return txt_What + txt_insert + txt_notFound;
    }

    public String getAll_txt() {
        String txt = "";
        Cursor cursor = db.query(ItemsDbSchema.ItemTable.NAME,
                null, null, null,
                null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String what = cursor.getString(0);
            String where = cursor.getString(1);
            Log.i("GarbageV8", "what, where = " + what + ", " + where);
            txt += what + " : " + where + '\n';
            cursor.moveToNext();
        }
        cursor.close();

/*
        String txt = "";
        for (String key : itemsDB.keySet()) { // .values()
            txt += key + " : " + itemsDB.get(key) + '\n';
        }
 */
        return txt;
    }
}
