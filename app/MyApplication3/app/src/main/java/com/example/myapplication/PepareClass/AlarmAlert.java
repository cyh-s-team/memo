package com.example.myapplication.PepareClass;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;

import com.example.myapplication.Maindesign;
import com.example.myapplication.R;


public class AlarmAlert extends Activity {//闹钟提醒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String remindMsg = bundle.getString("remindMsg");
        if (bundle.getBoolean("ring")) {
            Maindesign.mediaPlayer = MediaPlayer.create(this, R.raw.ring);//音乐铃声的设计
            try {
                Maindesign.mediaPlayer.setLooping(true);
                Maindesign.mediaPlayer.prepare();
            } catch (Exception e) {
                setTitle(e.getMessage());
            }
           Maindesign.mediaPlayer.start();//播放音乐
        }
        if (bundle.getBoolean("shake")) {
            Maindesign.vibrator = (Vibrator) getApplication().getSystemService(
                    Service.VIBRATOR_SERVICE);
            Maindesign.vibrator.vibrate(new long[] { 1000, 100, 100, 1000 }, -1);//按照指定的模式去震动
        }
        new AlertDialog.Builder(AlarmAlert.this)
                .setIcon(R.drawable.icon)
                .setTitle("提醒")
                .setMessage(remindMsg)
                .setPositiveButton("关 闭",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                AlarmAlert.this.finish();
                                if (Maindesign.mediaPlayer != null)
                                    Maindesign.mediaPlayer.stop();
                                if (Maindesign.vibrator != null)
                                    Maindesign.vibrator.cancel();
                            }
                        }).show();

    }
}

