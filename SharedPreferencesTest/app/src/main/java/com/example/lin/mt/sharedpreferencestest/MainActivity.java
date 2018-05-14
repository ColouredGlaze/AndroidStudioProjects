package com.example.lin.mt.sharedpreferencestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSaveData;

    private Button btnRestoreData;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSaveData = findViewById(R.id.save_data);
        btnRestoreData = findViewById(R.id.restore_data);
        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", "MT-Lin");
                editor.putInt("age", 23);
                editor.putBoolean("girlfriend", false);
                editor.apply();
            }
        });
        btnRestoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                String name = sharedPreferences.getString("name", "");
                int age = sharedPreferences.getInt("age", 0);
                boolean girlfriend = sharedPreferences.getBoolean("girlfriend", true);
                Log.d(TAG, "name: " + name);
                Log.d(TAG, "age: " + age);
                Log.d(TAG, "girlfriend: " + girlfriend);
            }
        });
    }
}
