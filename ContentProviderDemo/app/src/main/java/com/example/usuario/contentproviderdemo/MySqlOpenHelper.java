package com.example.usuario.contentproviderdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by usuario on 4/25/2015.
 */
public class MySqlOpenHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_MAIN = "create table main "+
            " (_id INTEGER PRIMARY KEY," +
            "word TEXT, " +
            "frequency TEXT, " +
            "locale TEXT)";
    private static final String DBNAME = "PROVIDER";

    MySqlOpenHelper(Context context){
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MAIN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
