package com.example.lin.mt.activitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        // 获取启动SecondActivity时传递过来的数据
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        Log.d(TAG, data);

        // 返回数据
        Button button_2 = findViewById(R.id.button_2);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("data_return", "Hello First Activity");
                setResult(RESULT_OK, intent1);
                finish();
            }
        });
    }

    // 重写该方法，实现用户按下返回键将数据返回
    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent();
        intent1.putExtra("data_return", "Hello First Activity");
        setResult(RESULT_OK, intent1);
        finish();
    }
}
