package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ActivityNewGame2 extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private NewGameCourseAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<NewGameCourseItem> mCourseList;
    private ArrayList<String> mNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game2);

        /** This have to be loaded from ActivityCourses, otherwise cannot render this list when app starts **/
        ActivityCourses.loadData(this);
        insertNames();
        buildRecyclerView();

        /** Keep checked names from previous activity **/
        mNameList = new ArrayList<>();
        mNameList = getIntent().getStringArrayListExtra("CHECKEDITEMS");
    }

    private void insertNames() {
        if (ActivityCourses.mCourseList == null) {
            mCourseList = new ArrayList<>();
        } else {
            mCourseList = new ArrayList<>();

            for (int i = 0; i < ActivityCourses.mCourseList.size(); i++) {
                mCourseList.add(new NewGameCourseItem(false, ActivityCourses.mCourseList.get(i).getCourseName(), "Holes:", ActivityCourses.mCourseList.get(i).getHolesNm(), "Par:", ActivityCourses.mCourseList.get(i).getParNm()));
            }
        }
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerViewChooseCourse);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new NewGameCourseAdapter(mCourseList);

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