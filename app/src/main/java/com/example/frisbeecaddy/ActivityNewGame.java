package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ActivityNewGame extends AppCompatActivity {
    private ArrayList<NewGamePlayerItem> mPlayerList;

    private RecyclerView mRecyclerView;
    private NewGamePlayerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        /** This have to be loaded from ActivityPlayers, otherwise cannot render this list when app starts **/
        ActivityPlayers.loadData(this);
        insertNames();
        buildRecyclerView();

        next = findViewById(R.id.button_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityNewGame.this, ActivityNewGame2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    private void insertNames() {
        if (ActivityPlayers.mNameList == null) {
            mPlayerList = new ArrayList<>();
        } else {
            mPlayerList = new ArrayList<>();

            for (int i = 0; i < ActivityPlayers.mNameList.size(); i++) {
                mPlayerList.add(new NewGamePlayerItem(false, ActivityPlayers.mNameList.get(i).getText1()));
            }
        }
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerViewChoosePlayer);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new NewGamePlayerAdapter(mPlayerList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Back button
     **/
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivityNewGame.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}

