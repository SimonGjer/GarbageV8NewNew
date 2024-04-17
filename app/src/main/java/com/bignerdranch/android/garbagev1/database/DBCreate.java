package com.bignerdranch.android.garbagev1.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bignerdranch.android.garbagev1.database.ItemsDbSchema.ItemTable;

public class DBCreate extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    public static final String txtFILENAME = "Garbage.db";

    private static SQLiteDatabase db;

    public DBCreate(Context context) {
        super(context, txtFILENAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        Log.i("GarbageV8", "Creating table");
        db.execSQL("CREATE TABLE " + ItemTable.NAME + "(" + ItemTable.Cols.WHAT + ", " + ItemTable.Cols.WHERE + ")");
        Log.i("GarbageV8", "Table created");

        db.execSQL("INSERT INTO Items (what, whereC) VALUES ('Anteater', 'Zoo')");
        db.execSQL("INSERT INTO Items (what, whereC) VALUES ('Paper', 'paper bin')");

        addItem(db, "coffee", "Irma");
        addItem(db, "carrots", "Netto");
        addItem(db, "milk", "Netto");
        addItem(db, "bread", "bakery");
        addItem(db, "butter", "Irma");

        Cursor cursor = db.query("Items",null, null, null, null, null, null);
        Log.i("GarbageV8", "4");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.i("GarbageV8", cursor.getString(0)+" "+cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
    }

    private void addItem(SQLiteDatabase db, String what, String where) {
        Log.i("GarbageV8", "addItem - OK Before");
        db.execSQL("INSERT INTO Items (what, whereC) VALUES ('" + what + "', '" + where + "')");
        Log.i("GarbageV8", "addItem - OK After");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase getSQLiteDatabase() {
        if (db == null) db = getWritableDatabase();
        return db;
    }
}