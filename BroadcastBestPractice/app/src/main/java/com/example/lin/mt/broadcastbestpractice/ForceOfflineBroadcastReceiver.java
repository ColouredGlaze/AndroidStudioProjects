package com.example.lin.mt.broadcastbestpractice;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * Created by MT-Lin on 2017/12/14.
 */

public class ForceOfflineBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Warning!");
        builder.setMessage("You are forced to be offline. Please try to login again.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityController.finishAll(); // 销毁所有活动
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent); // 重新启动LoginActivity
            }
        });
        builder.show();
    }
}
