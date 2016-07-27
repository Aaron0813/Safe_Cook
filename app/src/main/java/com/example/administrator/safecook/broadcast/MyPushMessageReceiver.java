package com.example.administrator.safecook.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.safecook.R;
import com.example.administrator.safecook.activity.MessageDetailActivity;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.push.PushConstants;
import cn.bmob.v3.helper.NotificationCompat;

//
public class MyPushMessageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
//        if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
//            // 收到广播时,发送一个通知
//            String jsonStr = intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING);
//            String content = null;
//            try {
//                // 处理JSON
//                JSONObject jsonObject = new JSONObject(jsonStr);
//                content = jsonObject.getString("alert");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
//            Notification notify = new Notification.Builder(context)
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setContentTitle("收到一条推送")
//                    .setContentText(content)
//                    .build();
//            manager.notify(1, notify);
//        }
        if(intent.getAction().equals(PushConstants.ACTION_MESSAGE)){
            String message = intent.getStringExtra("msg");
            Log.d("bmob", "SafeCook收到消息："+message);
            Toast.makeText(context, "SafeCook收到消息："+message, Toast.LENGTH_SHORT).show();


            //使用cn.bmob.v3.helper包下的NotificationCompat来创建通知栏，也可以使用support_v4里面的NotificationCompat类
            Intent i = new Intent();
            i.setClass(context, MessageDetailActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pi = PendingIntent.getActivity(context, 0, i, 0);
            NotificationCompat.Builder mBuilder =new NotificationCompat.Builder(context)
                    .setTicker("SafeCook收到消息")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("消息")
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_SOUND| Notification.DEFAULT_VIBRATE)
                    .setContentIntent(pi);
            // 发送通知
            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification n = mBuilder.build();
            nm.notify(0, n);
        }
    }
}
