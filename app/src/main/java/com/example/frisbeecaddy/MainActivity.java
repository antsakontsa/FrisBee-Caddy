package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

public class MainActivity extends AppCompatActivity {
    private Button players, courses, newGame, resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** These are for "new game" button **/
        ActivityPlayers.loadData(this);
        ActivityCourses.loadData(this);

        /** When "new game" button been clicked, go to ActivityNewGame **/
        newGame = findViewById(R.id.new_game_btn);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** If namelist and courselist are both empty **/
                if (ActivityPlayers.mNameList.isEmpty() && ActivityCourses.mCourseList.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "ADD ATLEAST (1) PLAYER AND (1) COURSE", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 400);
                    toast.show();

                    /** If courselist is empty but namelist don't **/
                } else if (!ActivityPlayers.mNameList.isEmpty() && ActivityCourses.mCourseList.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "ADD ATLEAST (1) COURSE", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 400);
                    toast.show();

                    /** If namelist is not empty but courselist don't **/
                } else if (ActivityPlayers.mNameList.isEmpty() && !ActivityCourses.mCourseList.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "ADD ATLEAST (1) PLAYER", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 400);
                    toast.show();

                    /** If both are not empty **/
                } else {
                    Intent intent = new Intent(MainActivity.this, ActivityNewGame.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });

        /** When "new game" button been clicked, go to ActivityResume **/
        resume = findViewById(R.id.resume_btn);
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityResume.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        /** When "courses" button been clicked, go to ActivityCourses **/
        courses = findViewById(R.id.courses_btn);
        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityCourses.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        /** When "players" button been clicked, go to ActivityPlayers **/
        players = findViewById(R.id.players_btn);
        players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityPlayers.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    /**
     * Back button
     **/
    @Override
    public void onBackPressed() {
        /** This empty block disable back button in this Main menu **/
    }
}