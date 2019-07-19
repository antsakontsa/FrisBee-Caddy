package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<MainActivityItem> mMainList;

    private RecyclerView mRecyclerView;
    private MainActivityAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainList = new ArrayList<>();

        mMainList.add(new MainActivityItem("NEW GAME"));
        mMainList.add(new MainActivityItem("RESUME"));
        mMainList.add(new MainActivityItem("COURSES"));
        mMainList.add(new MainActivityItem("PLAYERS"));

        buildRecyclerView();
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.mainRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MainActivityAdapter(mMainList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        /** These are for main menu buttons **/
        ActivityPlayers.loadData(this);
        ActivityCourses.loadData(this);

        mAdapter.setOnItemClickListener(new MainActivityAdapter.OnItemClickListener() {
            @Override
            public void onItemCLick(int position) {
                /** when "new game" button been clicked...**/
                if (position == 0) {
                    /** If courselist and namelist are both empty **/
                    if (ActivityCourses.mCourseList.isEmpty() && ActivityPlayers.mNameList.isEmpty()) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Add at least (1) course and (1) player", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 300);
                        toast.show();

                        /** If only courselist is empty **/
                    } else if (ActivityCourses.mCourseList.isEmpty() && !ActivityPlayers.mNameList.isEmpty()) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Add at least (1) course", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 300);
                        toast.show();

                        /** If only playerlist is empty **/
                    } else if (!ActivityCourses.mCourseList.isEmpty() && ActivityPlayers.mNameList.isEmpty()) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Add at least (1) player", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 300);
                        toast.show();

                        /** If both contains at least 1 item **/
                    } else {
                        /** ...Go to ActivityNewGame **/
                        Intent intent = new Intent(MainActivity.this, ActivityNewGame.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }

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