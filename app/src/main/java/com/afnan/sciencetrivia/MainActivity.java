package com.afnan.sciencetrivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //field variables
    private  MediaPlayer mediaPlayer;
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creates and starts the media player play background music
        mediaPlayer = MediaPlayer.create(this, R.raw.quiz);
        mediaPlayer.start();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Listener for the start button which takes to the QuizView activity
        //that hosts the fragments
        start = findViewById(R.id.btnStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizView.class);
                startActivity(intent);
            }
        });


    }

}