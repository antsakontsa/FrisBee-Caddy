package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ActivityNewGame extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        ArrayList<NewGamePlayerItem> playerList = new ArrayList<>();
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));
        playerList.add(new NewGamePlayerItem(true, "A"));

        mRecyclerView = findViewById(R.id.recyclerViewChoosePlayer);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new NewGamePlayerAdapter(playerList);

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
