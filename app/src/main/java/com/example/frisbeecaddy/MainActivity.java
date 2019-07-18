package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<MainActivityItem> mMainList;

    private RecyclerView mRecyclerView;
    private MainActivityAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button newGame, resume, courses, players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainList = new ArrayList<>();

        mMainList.add(new MainActivityItem("NEW GAME"));
        mMainList.add(new MainActivityItem("RESUME"));
        mMainList.add(new MainActivityItem("COURSES"));
        mMainList.add(new MainActivityItem("PLAYERS"));

        mRecyclerView = findViewById(R.id.mainRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MainActivityAdapter(mMainList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MainActivityAdapter.OnItemClickListener() {
            @Override
            public void onItemCLick(int position) {
                if (position == 0) {
                    /** when "new game" button been clicked, go to ActivityNewGame **/
                    Intent intent = new Intent(MainActivity.this, ActivityNewGame.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } else if (position == 1) {
                    /** when "new game" button been clicked, go to ActivityResume **/
                    Intent intent = new Intent(MainActivity.this, ActivityResume.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } else if (position == 2) {
                    /** when "new game" button been clicked, go to ActivityCourses **/
                    Intent intent = new Intent(MainActivity.this, ActivityCourses.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } else {
                    /** when "new game" button been clicked, go to ActivityPlayers **/
                    Intent intent = new Intent(MainActivity.this, ActivityPlayers.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
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