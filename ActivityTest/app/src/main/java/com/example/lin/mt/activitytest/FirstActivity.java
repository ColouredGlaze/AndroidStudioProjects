package com.example.lin.mt.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    private static final String TAG = "FirstActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
        Button button_1 = findViewById(R.id.button_1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 使用Toast
                // Toast.makeText(FirstActivity.this, "You click Button1", Toast.LENGTH_SHORT);

                // 销毁一个活动
                // finish();

                // 使用Intent在活动之间穿梭
                // a.显示Intent
                // Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                // b.隐式Intent
                // 在AndroidManifest.xml配置activity的intent-filter
                // Intent intent = new Intent("com.example.activitytest.ACTION_START");
                // intent.addCategory("com.example.activitytest.MY_CATEGORY");
                // 更多隐式Intent
                // 打开网页
                // Intent intent = new Intent(Intent.ACTION_VIEW);
                // intent.setData(Uri.parse("http://baidu.com"));
                // 打电话
                // Intent intent = new Intent(Intent.ACTION_DIAL);
                // intent.setData(Uri.parse("tel:10086"));
                // 传递数据
                String data = "Hello second Activity";
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("extra_data", data);
                // 启动其他Activity不需要获取其他Activity返回的数据
                // startActivity(intent);
                // 启动其他Activity并获取其他Activity返回的数据，同时需要重写onActivityResult方法
                startActivityForResult(intent, 2);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 2:
                if (resultCode == RESULT_OK){
                    String returnData = data.getStringExtra("data_return");
                    Log.d(TAG, returnData);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_1:
                Toast.makeText(this, "You click item_1", Toast.LENGTH_SHORT);
                break;
            case R.id.item_2:
                Toast.makeText(this, "you click item_2", Toast.LENGTH_SHORT);
                break;
            default:
                break;
        }
        return true;
    }
}
