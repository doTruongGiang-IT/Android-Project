package com.example.selfie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    Button btn;
    ImageView imageView;
    ConstraintLayout constraintlayout;
    AnimationDrawable animationDrawable;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final String DIRECTORY_PICTURES = "/storage/sdcard0/Pictures";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        constraintlayout = (ConstraintLayout)findViewById(R.id.root);
        animationDrawable = (AnimationDrawable)constraintlayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        imageView = (ImageView)findViewById(R.id.imageView);
        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
                setAlarm();
            }
        });
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle bundle = data.getExtras();
//            Bitmap bitmap = (Bitmap) bundle.get("data");
//            imageView.setImageBitmap(bitmap);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            FileOutputStream fo;
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
//            File destination = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), System.currentTimeMillis() + ".jpg");
            File destination = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), System.currentTimeMillis() + ".jpg");
            try {
                destination.createNewFile();
                fo = new FileOutputStream(destination);
                fo.write(bytes.toByteArray());
                fo.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(thumbnail);

            imageView.setDrawingCacheEnabled(true);
            Bitmap bitmap = imageView.getDrawingCache();
            MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Pictures","Task");
            Toast.makeText(getApplicationContext(), "Image Saved", Toast.LENGTH_LONG).show();
        }
    }

    private void setAlarm() {
        Toast.makeText(this, "Reminder Set!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long timeAtButtonClick = System.currentTimeMillis();
        long seconds = 2000 * 10;
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick + seconds, pendingIntent);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Alarm";
            String description = "Channel for Alarm";
            int important = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyId", name, important);
            channel.setDescription(description);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}

