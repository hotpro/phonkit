package com.yutaohou.phonkit.volume.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.thedazzler.droidicon.badges.FacebookDroidiconBadge;
import com.yutaohou.phonkit.util.BitmapUtil;
import com.yutaohou.phonkit.R;

import java.io.File;

/**
 * Created by yutao on 14-11-28.
 */
public class VolumeActivity extends Activity {
    private static final String TAG = VolumeActivity.class.getSimpleName();
    private Button btnLower;
    private Button btnHigher;
    private AudioManager audioManager;
    private FacebookDroidiconBadge facebookDroidiconBadge;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final int a = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int ca = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        Log.v(TAG, String.format("a: %s, ca: %s", a, ca));

        final int b = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        int cb = audioManager.getStreamVolume(AudioManager.STREAM_RING);

        Log.v(TAG, String.format("b: %s, cb: %s", b, cb));
        btnHigher = (Button) findViewById(R.id.btnHigher);
        btnHigher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, a, 0);
                audioManager.setStreamVolume(AudioManager.STREAM_RING, b, 0);

            }
        });
        btnLower = (Button) findViewById(R.id.btnLower);
        btnLower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, a / 5, 0);
                audioManager.setStreamVolume(AudioManager.STREAM_RING, b / 5, 0);

            }
        });

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                save();
            }
        });

        facebookDroidiconBadge = (FacebookDroidiconBadge) findViewById(R.id.icon);
    }

    private void save() {
        Drawable drawable = facebookDroidiconBadge.getIconicFontDrawable();

        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        String fileName = myDir.getAbsolutePath() +"/1.png";
        Log.v("filename is ",fileName);

        BitmapUtil.saveDrawable(drawable, fileName);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
