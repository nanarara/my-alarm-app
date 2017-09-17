package com.nara.alarm;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by nara.yoon on 2017-08-21.
 */

public class AlarmReceiver extends BroadcastReceiver {

    Context mContext;
    PendingIntent pendingIntent;
    MediaPlayer player;
    String u;

    @Override
    public void onReceive(final Context context, Intent intent) {
        mContext = context;

        PowerManager powerManager = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");

        if(MainActivity.IS_TAG_ON)
          Log.d(MainActivity.TAG, getClass() + "onReceive");

        u = intent.getStringExtra("URI");

        Log.d(MainActivity.TAG, getClass() + "uri ok ? " + u);
        if(u!=null){
            Uri uri = Uri.parse(u);
            Log.d(MainActivity.TAG, getClass() + "uri : " + uri);
            player = new MediaPlayer();

            try{
                player.setDataSource(context, uri);
                player.setAudioStreamType(AudioManager.STREAM_RING);
                player.setLooping(true);
                player.prepare();
            }catch (Exception e){
                Log.d(MainActivity.TAG, getClass() + "error : " + e);
            }

            player.start();
        }


        wakeLock.acquire();

        Toast toast = Toast.makeText(mContext, "알람이 울립니다", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 200);
        toast.show();

        wakeLock.release();
        notification();


        Intent i = new Intent(mContext, AlarmDialog.class);
        i.putExtra("URI", u);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, i, PendingIntent.FLAG_ONE_SHOT);
        try {
            pendingIntent.send();
        }catch (Exception e){

        }


//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialogInterface) {
//                player.stop();
//
//                Log.d(MainActivity.TAG, getClass() + " onDismiss");
//
//                Intent intent = new Intent("AlarmReceiver");
//                PendingIntent servicePending = PendingIntent.getBroadcast(context, 111, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//                Toast.makeText(context, "알람해제", Toast.LENGTH_SHORT).show();
//                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
//                alarmManager.cancel(servicePending);
//            }
//        });
    }

    void notification() {
        Intent intent = new Intent();
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        BitmapFactory.decodeResource(mContext.getResources(), android.R.drawable.ic_dialog_alert);

        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext).setSmallIcon(android.R.drawable.ic_menu_gallery)
                .setContentTitle("알람")
                .setContentText("띵띵")
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());

    }
}
