package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ActivityGame extends AppCompatActivity {
    private ArrayList<GameItem> mGameItemList;

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
        mGameItemList = new ArrayList<>();

        /** Set selected names into the recyclerview **/
        for (int i = 0; i < ActivityNewGame.mCheckedBoxes.size(); i++) {
            mGameItemList.add(new GameItem(ActivityNewGame.mCheckedBoxes.get(i), "3", R.drawable.ic_minus, R.drawable.ic_plus));
        }

        mRecyclerView = findViewById(R.id.gameRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new GameAdapter(mGameItemList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new GameAdapter.OnItemClickListener() {
            @Override
            public void onMinusClick(int position) {
                String parNum = mGameItemList.get(position).getText2();
                int intParNm = Integer.valueOf(parNum);

                if (intParNm != 1) {
                    intParNm -= 1;
                    mGameItemList.get(position).changeText2(Integer.toString(intParNm));
                    mAdapter.notifyItemChanged(position);
                }
            }

            @Override
            public void onPlusClick(int position) {
                String parNum = mGameItemList.get(position).getText2();
                int intParNm = Integer.valueOf(parNum);

                if (intParNm != 99) {
                    intParNm += 1;
                    mGameItemList.get(position).changeText2(Integer.toString(intParNm));
                    mAdapter.notifyItemChanged(position);
                }
            }
        });
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
