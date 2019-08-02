package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class ActivityResume extends AppCompatActivity {
    private ArrayList<ResumeItem> mResumeList;

    private RecyclerView mRecyclerView;
    private ResumeAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        mResumeList = new ArrayList<>();

        mResumeList.add(new ResumeItem(false, "Mukkula Etu9", "08.02.2019"));
        mResumeList.add(new ResumeItem(false, "Mukkula Etu9", "08.02.2019"));
        mResumeList.add(new ResumeItem(false, "Mukkula Etu9", "08.02.2019"));

        buildRecyclerView();
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerViewPlayedGames);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ResumeAdapter(mResumeList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Back button
     **/
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivityResume.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}