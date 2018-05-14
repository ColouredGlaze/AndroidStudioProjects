package com.example.lin.mt.providertest;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAddBookData;

    private Button btnUpdateBookData;

    private Button btnDeleteBookData;

    private Button btnQueryBookData;

    private String newId;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddBookData = findViewById(R.id.add_book_data);
        btnUpdateBookData = findViewById(R.id.update_book_data);
        btnDeleteBookData = findViewById(R.id.delete_book_data);
        btnQueryBookData = findViewById(R.id.query_book_data);
        btnAddBookData.setOnClickListener(this);
        btnUpdateBookData.setOnClickListener(this);
        btnDeleteBookData.setOnClickListener(this);
        btnQueryBookData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ContentValues contentValues = new ContentValues();
        switch (v.getId()){
            case R.id.add_book_data:
                Uri uriAddBook = Uri.parse("content://com.example.lin.mt.databasetest.provider/book");
                contentValues.put("name", "The Da Provider Code");
                contentValues.put("author", "DanBrow");
                contentValues.put("pages", 66);
                contentValues.put("price", 16.96);
                Uri newUri = getContentResolver().insert(uriAddBook, contentValues);
                newId = newUri.getPathSegments().get(1);
                contentValues.clear();
                Toast.makeText(this, "Add finish", Toast.LENGTH_SHORT).show();
                break;
            case R.id.update_book_data:
                Uri uriUpdateBook = Uri.parse("content://com.example.lin.mt.databasetest.provider/book/" + newId);
                contentValues.put("name", "The Da Provider Update Code");
                contentValues.put("author", "DanBrow");
                contentValues.put("pages", 369);
                contentValues.put("price", 36.96);
                getContentResolver().update(uriUpdateBook, contentValues, null,null);
                contentValues.clear();
                Toast.makeText(this, "Update finish", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete_book_data:
                Uri uriDeleteBook = Uri.parse("content://com.example.lin.mt.databasetest.provider/book/" + newId);
                getContentResolver().delete(uriDeleteBook, null,null);
                Toast.makeText(this, "Delete finish", Toast.LENGTH_SHORT).show();
                break;
            case R.id.query_book_data:
                Uri uriQueryBook = Uri.parse("content://com.example.lin.mt.databasetest.provider/book");
                Cursor cursor = getContentResolver().query(uriQueryBook, null,
                        null, null, null);
                if (cursor.moveToFirst()){
                    do {
                        Log.d(TAG, "name: " + cursor.getString(cursor.getColumnIndex("name")));
                        Log.d(TAG, "author: " + cursor.getString(cursor.getColumnIndex("author")));
                        Log.d(TAG, "pages: " + cursor.getInt(cursor.getColumnIndex("pages")));
                        Log.d(TAG, "price: " + cursor.getDouble(cursor.getColumnIndex("price")));
                    } while (cursor.moveToNext());
                }
                Toast.makeText(this, "Query finish", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
