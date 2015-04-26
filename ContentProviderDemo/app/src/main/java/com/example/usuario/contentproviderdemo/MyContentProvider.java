package com.example.usuario.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * Created by usuario on 4/25/2015.
 */
public class MyContentProvider extends ContentProvider{
    private static final UriMatcher uriMatcher;
    private MySqlOpenHelper mySqlOpenHelper;
    private SQLiteDatabase db;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.usuario.contentproviderdemo.mycontentproviders", "main", 1);
        uriMatcher.addURI("com.example.usuario.contentproviderdemo.mycontentproviders", "main/#", 2);
    }

    @Override
    public boolean onCreate() {
        mySqlOpenHelper = new MySqlOpenHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        db = mySqlOpenHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case 1:
                cursor = db.query("main", projection, selection, selectionArgs, null, null,sortOrder);

            case 2:
                selection = selection + "_ID" + uri.getLastPathSegment();
                break;
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db = mySqlOpenHelper.getWritableDatabase();
        long id = db.insert("main", null, values);
        Log.d("id", "" + id);
        return uri.withAppendedPath(uri, "" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
