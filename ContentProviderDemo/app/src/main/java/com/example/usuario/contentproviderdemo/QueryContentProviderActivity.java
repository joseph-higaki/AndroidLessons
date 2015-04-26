package com.example.usuario.contentproviderdemo;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;


public class QueryContentProviderActivity extends ListActivity {

    //String[] dictionaryMetadata = {UserDictionary.Words._ID, UserDictionary.Words.WORD, UserDictionary.Words.LOCALE};
    String[] dictionaryMetadata = {ContractProvider._ID, ContractProvider.WORD, ContractProvider.LOCALE};
    String selection = null;
    String[] selectedArguments = {""};

    int[] wordListUIItems = {R.id.txtId, R.id.txtWord, R.id.txtLocale};
    SimpleCursorAdapter simpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_query_content_provider);
        writeWord();
        getWordsFromProvider();
    }

    private void writeWord(){
        Uri newUri;
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContractProvider.LOCALE, "en_US");
        contentValues.put(ContractProvider.WORD, "insert");
        contentValues.put(ContractProvider.FREQUENCY, "100");
        newUri = getContentResolver().insert(ContractProvider.CONTENT_URI, contentValues);
        Log.d("newUri::", ""+ ContentUris.parseId(newUri));
    }

    protected void getWordsFromProvider(){
        //Cursor cursor = getContentResolver().query(UserDictionary.Words.CONTENT_URI, dictionaryMetadata, null, null, null);
        Cursor cursor = getContentResolver().query(ContractProvider.CONTENT_URI, dictionaryMetadata, null, null, null);
        if (cursor == null){
            Log.d("getWordsFromProvider", "Error querying content provider");
        }else if (cursor.getCount() < 1 ){
            Log.d("getWordsFromProvider", "Words not fetched, count: " + cursor.getCount());
        } else {
            simpleCursorAdapter = new SimpleCursorAdapter(getApplicationContext(),
                    R.layout.row_cursor, cursor, dictionaryMetadata, wordListUIItems, 0);
            getListView().setAdapter(simpleCursorAdapter);

            StringBuilder word = new StringBuilder();
            //int index = cursor.getColumnIndex(UserDictionary.Words.WORD);
            int index = cursor.getColumnIndex(ContractProvider.WORD);
            if (cursor != null){
                while (cursor.moveToNext()){
                    word.append(cursor.getString(index));
                }
            }
            Log.d("sdifgsdfyg: ", word.toString());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_query_content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
