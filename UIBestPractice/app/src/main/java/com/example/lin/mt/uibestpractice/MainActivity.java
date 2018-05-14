package com.example.lin.mt.uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();

    private Button btnSend;

    private EditText inputText;

    private RecyclerView recyclerView;

    private MsgAdapter msgAdapter;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.msg_rcycler_view);
        initMsgList(); // 初始化消息列表
        btnSend = findViewById(R.id.send);
        inputText = findViewById(R.id.input_text);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        msgAdapter = new MsgAdapter(msgList);
        recyclerView.setAdapter(msgAdapter);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: inputText.getText().toString().length() = "
                        + inputText.getText().toString().length());
                if (!"".equals(inputText.getText().toString())){
                    Msg msg = new Msg(inputText.getText().toString(), Msg.TYPE_SEND);
                    msgList.add(msg);
                    // 当有新消息时，刷新ListView中的显示
                    msgAdapter.notifyItemInserted(msgList.size() - 1);
                    // 将ListView定位到最后一行
                    recyclerView.scrollToPosition(msgList.size() - 1);
                    inputText.setText("");
                } else {
                    Toast.makeText(MainActivity.this,
                            "Please input content!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initMsgList(){
        Msg msg = new Msg("Hello !", Msg.TYPE_RECEIVED);
        msgList.add(msg);
        Msg msg1 = new Msg("Hi !", Msg.TYPE_SEND);
        msgList.add(msg1);
        Msg msg2 = new Msg("Who are you?", Msg.TYPE_SEND);
        msgList.add(msg2);
    }
}
