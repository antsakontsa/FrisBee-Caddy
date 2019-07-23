package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ActivityNewGame extends AppCompatActivity {
    private ArrayList<NewGamePlayerItem> mPlayerList;
    public static ArrayList<String> mCheckedBoxes;

    private RecyclerView mRecyclerView;
    private NewGamePlayerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        mNext = findViewById(R.id.button_next);
        mNext.setEnabled(false);

        /** This have to be loaded from ActivityPlayers, otherwise cannot render this list when app starts **/
        ActivityPlayers.loadData(this);
        insertNames();
        buildRecyclerView();
        setButtons();
    }

    private void checkCheckBoxes() {
        mCheckedBoxes = new ArrayList<>();

        for (int i = 0; i < mPlayerList.size(); i++) {
            if (mPlayerList.get(i).getCheckBox() == true) {
                mCheckedBoxes.add(mPlayerList.get(i).getmText());
            }
        }
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

        mAdapter.setOnItemsCheckStateListener(new NewGamePlayerAdapter.OnItemsCheckStateListener() {
            @Override
            public void onItemCheckStateChanged(int checkedItemCounter) {
                mNext = findViewById(R.id.button_next);

                if (checkedItemCounter == 0) {
                    mNext.setEnabled(false);
                } else {
                    mNext.setEnabled(true);
                }
            }
        });
    }

    private void setButtons() {
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Get all checked names to the mCheckedBoxes Arraylist **/
                checkCheckBoxes();

                Intent intent = new Intent(ActivityNewGame.this, ActivityNewGame2.class);
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
        Intent intent = new Intent(ActivityNewGame.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}