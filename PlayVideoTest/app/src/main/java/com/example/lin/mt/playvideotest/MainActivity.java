package com.example.lin.mt.playvideotest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnPlay;
    private Button btnPause;
    private Button btnReplay;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlay = findViewById(R.id.play);
        btnPause = findViewById(R.id.pause);
        btnReplay = findViewById(R.id.replay);
        videoView = findViewById(R.id.video_view);
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnReplay.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initVideoPath();
        }
    }

    private void initVideoPath() {
        File file = new File(Environment.getExternalStorageDirectory(),
                "疑犯追踪.Person.of.Interest.S01E01.Chi_Eng.BD-HR.AC3.1024X576.x264-YYeTs人人影视.mkv");
        videoView.setVideoPath(file.getPath());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                if (!videoView.isPlaying()){
                    // 开始播放
                    videoView.start();
                }
                break;
            case R.id.pause:
                if (videoView.isPlaying()){
                    // 暂停播放
                    videoView.pause();
                }
                break;
            case R.id.replay:
                if (videoView.isPlaying()){
                    // 重新播放
                    videoView.resume();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initVideoPath();
                } else {
                    Toast.makeText(this, "you denied permission!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != videoView){
            videoView.suspend();
        }
    }
}
