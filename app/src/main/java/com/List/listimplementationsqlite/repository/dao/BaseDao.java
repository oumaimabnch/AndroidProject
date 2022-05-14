package com.List.listimplementationsqlite.repository.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.List.listimplementationsqlite.repository.DataBaseOpenHelper;
import com.List.listimplementationsqlite.repository.DataBaseOpenHelper;


public class BaseDao {


    protected SQLiteDatabase getReadableDatabase(Context context)
    {
        return DataBaseOpenHelper.getInstance(context).getReadableDatabase();
    }

    protected SQLiteDatabase getWritableDatabase(Context context)
    {
        return DataBaseOpenHelper.getInstance(context).getWritableDatabase();
    }
}
