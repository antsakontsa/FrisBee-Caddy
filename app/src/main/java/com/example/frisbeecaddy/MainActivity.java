package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button players, courses, newGame, resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** When "new game" button been clicked, go to ActivityNewGame **/
        newGame = findViewById(R.id.new_game_btn);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityNewGame.class);
                startActivity(intent);
            }
        });

        /** When "new game" button been clicked, go to ActivityNewGame **/
        resume = findViewById(R.id.resume_btn);
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityResume.class);
                startActivity(intent);
            }
        });

        /** When "courses" button been clicked, go to ActivityPlayers **/
        courses = findViewById(R.id.courses_btn);
        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityCourses.class);
                startActivity(intent);
            }
        });

        /** When "players" button been clicked, go to ActivityPlayers **/
        players = findViewById(R.id.players_btn);
        players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityPlayers.class);
                startActivity(intent);
            }
        });
    }
}
