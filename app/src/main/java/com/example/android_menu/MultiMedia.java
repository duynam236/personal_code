package com.example.android_menu;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MultiMedia extends AppCompatActivity {
    MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimedia);

        final EditText link = (EditText) findViewById(R.id.url);
        Button playBtn = (Button) findViewById(R.id.play);
        Button stopBtn = (Button) findViewById(R.id.stop);
        Button getLink = (Button) findViewById(R.id.get_link);
        playBtn.setEnabled(false);
        stopBtn.setEnabled(false);

        getLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url_link = link.getText().toString();
                if (!TextUtils.isEmpty(url_link)) {
//                    String url = "https://file-examples.com/storage/fe96e2581762eff5a95d6e6/2017/11/file_example_MP3_700KB.mp3"; // your URL here

                    try {
                        mediaPlayer.setDataSource(url_link);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    playBtn.setEnabled(true);
                } else {
                    Toast.makeText(getApplicationContext(), "Empty music link", Toast.LENGTH_SHORT).show();
                }
            }
        });
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                stopBtn.setEnabled(true);
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                stopBtn.setEnabled(false);
            }
        });


    }
}
