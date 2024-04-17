package com.bignerdranch.android.garbagev1.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.bignerdranch.android.garbagev1.database.ItemsDbSchema.ItemTable;;

import com.bignerdranch.android.garbagev1.Item;

public class ItemCursorWrapper extends CursorWrapper {
    public ItemCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Item getItem() {
        String what = getString(getColumnIndex(ItemTable.Cols.WHAT));
        String where = getString(getColumnIndex(ItemTable.Cols.WHERE));
        return new Item(what, where);
    }
}

