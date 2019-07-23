package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ActivityGame extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private GameAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        buildRecyclerView();
    }

    private void buildRecyclerView() {
        ArrayList<GameItem> gameList = new ArrayList<>();

        gameList.add(new GameItem("Antti", "3", R.drawable.ic_minus, R.drawable.ic_plus));
        gameList.add(new GameItem("Emi", "3", R.drawable.ic_minus, R.drawable.ic_plus));
        gameList.add(new GameItem("Riina", "3", R.drawable.ic_minus, R.drawable.ic_plus));

        mRecyclerView = findViewById(R.id.gameRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new GameAdapter(gameList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Back button
     **/
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivityGame.this, ActivityNewGame2.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
