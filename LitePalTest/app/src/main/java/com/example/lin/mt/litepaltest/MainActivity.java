package com.example.lin.mt.litepaltest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCreateDatabase;

    private Button btnAddBookData;

    private Button btnUpdateBookData;

    private Button btnDeleteBookData;

    private Button btnQueryBookData;

    private Book bookForUpdate;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookForUpdate = new Book();
        btnCreateDatabase = findViewById(R.id.create_database);
        btnAddBookData = findViewById(R.id.add_book_data);
        btnUpdateBookData = findViewById(R.id.update_book_data);
        btnDeleteBookData = findViewById(R.id.delete_book_data);
        btnQueryBookData = findViewById(R.id.query_book_data);
        btnCreateDatabase.setOnClickListener(this);
        btnAddBookData.setOnClickListener(this);
        btnUpdateBookData.setOnClickListener(this);
        btnDeleteBookData.setOnClickListener(this);
        btnQueryBookData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        bookForUpdate.setName("First Version Book");
        bookForUpdate.setPress("Unknow");
        bookForUpdate.setPrice(136.00);
        bookForUpdate.setPages(96);
        bookForUpdate.setAuthor("Author");
        switch (v.getId()){
            case R.id.create_database:
                Connector.getDatabase();
                Toast.makeText(this, "Create finish", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_book_data:
                Book book = new Book();
                book.setName("The dark");
                book.setAuthor("Dan Brown");
                book.setPages(215);
                book.setPrice(63.90);
                book.save();
                book.setPress("Unknow");
                bookForUpdate.save();
                Toast.makeText(this, "Add finish", Toast.LENGTH_SHORT).show();
                break;
            case R.id.update_book_data:
                bookForUpdate.setName("Update Version Book");
                bookForUpdate.save();
                Book updateAllBook = new Book();
                updateAllBook.setPrice(999);
                updateAllBook.setPress("Anchor");
                // 设置默认值
                updateAllBook.setToDefault("pages");
                updateAllBook.updateAll("name = ? and author = ? ", "Update Version Book", "Author");
                Toast.makeText(this, "Update finish", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete_book_data:
                DataSupport.deleteAll(Book.class, "price < ? ", "66");
                Toast.makeText(this, "Delete finish", Toast.LENGTH_SHORT).show();
                break;
            case R.id.query_book_data:
                List<Book> bookList = DataSupport.findAll(Book.class);
                for (Book bookItem : bookList) {
                    Log.d(TAG, "book name: " + bookItem.getName());
                    Log.d(TAG, "book author: " + bookItem.getAuthor());
                    Log.d(TAG, "book price: " + bookItem.getPrice());
                    Log.d(TAG, "book pages: " + bookItem.getPages());
                    Log.d(TAG, "book press: " + bookItem.getPress());
                }
                Toast.makeText(this, "Query finish", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
