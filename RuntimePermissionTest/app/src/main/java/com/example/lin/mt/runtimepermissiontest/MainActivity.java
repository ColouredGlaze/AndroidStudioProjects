package com.example.lin.mt.runtimepermissiontest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnMakeCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMakeCall = findViewById(R.id.make_call);
        btnMakeCall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.make_call:
                // 判断是否具有打电话的权限
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    // 如果没有该权限,判断是否需要解释：第一次申请权限的时，傲娇不给权限，第二次申请的时候就需要解释一波了
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.CALL_PHONE)) {
                        // 显示解释为什么需要该权限的对话框
                        Toast.makeText(this, "解释一波？", Toast.LENGTH_SHORT).show();
                        // 解释完之后记得申请权限
                    } else {
                        // 申请权限
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }
                    // 没有打电话的权限，申请权限
//                    ActivityCompat.requestPermissions(this,
//                            new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    // 有打电话的权限，直接打电话
                    call();
                }
                break;
        }
    }

    private void call(){
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } catch (SecurityException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // 同意打电话
                    call();
                } else {
                    // 不同意打电话
                    Toast.makeText(this, "You denied the permision", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
