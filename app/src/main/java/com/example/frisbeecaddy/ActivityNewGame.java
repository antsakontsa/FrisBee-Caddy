package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ActivityNewGame extends AppCompatActivity {
    private ArrayList<NewGamePlayerItem> mPlayerList;

    private RecyclerView mRecyclerView;
    private NewGamePlayerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        insertNames();
        buildRecyclerView();
    }

    private void insertNames() {
        if (ActivityPlayers.mNameList == null) {
            mPlayerList = new ArrayList<>();
        } else {
            mPlayerList = new ArrayList<>();

            for (int i = 0; i < ActivityPlayers.mNameList.size(); i++) {
                mPlayerList.add(new NewGamePlayerItem(true, ActivityPlayers.mNameList.get(i).getText1()));
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

