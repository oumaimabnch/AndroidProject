package com.List.listimplementationsqlite.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseOpenHelper extends SQLiteOpenHelper {

    private static DataBaseOpenHelper instance;

    private DataBaseOpenHelper(Context context) {
        super(context, LocalDB.NAME, null, LocalDB.VERSION);
    }

    public static DataBaseOpenHelper getInstance(Context context)
    {
        synchronized (DataBaseOpenHelper.class) {
            if (instance == null) {
                instance = new DataBaseOpenHelper(context);
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LocalDB.create_table_Personne);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DataBaseOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + LocalDB.TablePersonne.TABLE_NAME);
        onCreate(db);
    }



}
