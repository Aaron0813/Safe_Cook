package com.example.administrator.safecook.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.example.administrator.safecook.R;
import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.push.PushConstants;

public class MyPushMessageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
            // 收到广播时,发送一个通知
            String jsonStr = intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING);
            String content = null;
            try {
                // 处理JSON
                JSONObject jsonObject = new JSONObject(jsonStr);
                content = jsonObject.getString("alert");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            Notification notify = new Notification.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("收到一条推送")
                    .setContentText(content)
                    .build();
            manager.notify(1, notify);
        }
    }
}
