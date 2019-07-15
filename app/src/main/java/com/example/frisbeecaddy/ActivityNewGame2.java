package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ActivityNewGame2 extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game2);

        ArrayList<NewGameCourseItem> courseList = new ArrayList<>();
        courseList.add(new NewGameCourseItem(false, "Mukkula Koko 18 (Abc)", "Holes:", "18", "Par:", "61"));
        courseList.add(new NewGameCourseItem(false, "Mukkula Koko 18 (Abc)", "Holes:", "18", "Par:", "61"));
        courseList.add(new NewGameCourseItem(false, "Mukkula Koko 18 (Abc)", "Holes:", "18", "Par:", "61"));

        mRecyclerView = findViewById(R.id.recyclerViewChooseCourse);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new NewGameCourseAdapter(courseList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Back button
     **/
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivityNewGame2.this, ActivityNewGame.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
