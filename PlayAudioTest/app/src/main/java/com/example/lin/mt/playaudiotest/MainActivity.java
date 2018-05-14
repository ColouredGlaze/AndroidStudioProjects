package com.example.lin.mt.playaudiotest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnPlay;
    private Button btnPause;
    private Button btnStop;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlay = findViewById(R.id.play);
        btnPause = findViewById(R.id.pause);
        btnStop = findViewById(R.id.stop);
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            // 初始化MediaPlayer
            initMediaPlayer();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                if (null != mediaPlayer){
                    mediaPlayer.start();
                }
                break;
            case R.id.pause:
                if (null != mediaPlayer){
                    mediaPlayer.pause();
                }
                break;
            case R.id.stop:
                if (null != mediaPlayer){
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;
            default:
                break;
        }
    }

    private void initMediaPlayer() {
        File file = new File(Environment.getExternalStorageDirectory(), "单身情歌.mp3");
        Log.d(TAG, "initMediaPlayer, fileExits: " + file.exists());
        try {
            Log.d(TAG, "initMediaPlayer, filePath: " + file.getPath());
            // 指定音频文件的路径
            mediaPlayer.setDataSource(file.getPath());
            // 让mediaPlayer进入准备状态
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initMediaPlayer();
                } else {
                    Toast.makeText(this, "you denied permission", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mediaPlayer){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
