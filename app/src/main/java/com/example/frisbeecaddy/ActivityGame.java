package com.example.frisbeecaddy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityGame extends AppCompatActivity {
    private ArrayList<GameItem> mGameItemList;

    private RecyclerView mRecyclerView;
    private GameAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView mCourseName, mHoleNm;
    public static TextView mParNm;
    private ImageView mBackArrow, mForwardArrow;

    private int holeCounter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setLayoutData();
        setButtons();
        buildRecyclerView();
    }

    private void setButtons() {
        mBackArrow = findViewById(R.id.previous);
        mForwardArrow = findViewById(R.id.next);

        Intent intent = getIntent();
        mParNm = findViewById(R.id.gameParNumber);
        mParNm.setText(intent.getStringArrayListExtra("PARNUMBERSINDIVIDUALLY").get(holeCounter - 1));

        mBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHoleNm = findViewById(R.id.gameHoleNumber);

                if (holeCounter > 1) {
                    mForwardArrow.setImageResource(R.drawable.ic_arrow_right);

                    holeCounter--;

                    Intent intent = getIntent();
                    mHoleNm.setText(holeCounter + "/" + intent.getStringExtra("HOLESNM"));

                    mParNm = findViewById(R.id.gameParNumber);
                    mParNm.setText(intent.getStringArrayListExtra("PARNUMBERSINDIVIDUALLY").get(holeCounter - 1));

                    updateGameArrayList(Integer.parseInt(mParNm.getText().toString()));
                }
            }
        });

        mForwardArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHoleNm = findViewById(R.id.gameHoleNumber);

                Intent intent = getIntent();
                int holesNm = Integer.parseInt(intent.getStringExtra("HOLESNM"));

                if (holeCounter == holesNm - 1) {
                    mForwardArrow.setImageResource(R.drawable.ic_finish);

                    holeCounter++;

                    mHoleNm.setText(holeCounter + "/" + intent.getStringExtra("HOLESNM"));

                    mParNm = findViewById(R.id.gameParNumber);
                    mParNm.setText(intent.getStringArrayListExtra("PARNUMBERSINDIVIDUALLY").get(holeCounter - 1));

                    updateGameArrayList(Integer.parseInt(mParNm.getText().toString()));
                } else if (holeCounter < holesNm) {
                    mForwardArrow.setImageResource(R.drawable.ic_arrow_right);

                    holeCounter++;

                    mHoleNm.setText(holeCounter + "/" + intent.getStringExtra("HOLESNM"));

                    mParNm = findViewById(R.id.gameParNumber);
                    mParNm.setText(intent.getStringArrayListExtra("PARNUMBERSINDIVIDUALLY").get(holeCounter - 1));

                    updateGameArrayList(Integer.parseInt(mParNm.getText().toString()));
                }
            }
        });
    }

    private void updateGameArrayList(int parVal) {
        for(GameItem model : mGameItemList) {
            model.changeText2(Integer.toString(parVal));
            mAdapter.notifyDataSetChanged();
        }
    }

    private void setLayoutData() {
        mCourseName = findViewById(R.id.gameCourseName);
        mHoleNm = findViewById(R.id.gameHoleNumber);

        Intent intent = getIntent();
        mCourseName.setText(intent.getStringExtra("COURSENAME"));
        mHoleNm.setText(holeCounter + "/" + intent.getStringExtra("HOLESNM"));
    }

    private void buildRecyclerView() {
        mGameItemList = new ArrayList<>();

        /** Set selected names into the recyclerview **/
        for (int i = 0; i < ActivityNewGame.mCheckedBoxes.size(); i++) {
            mParNm = findViewById(R.id.gameParNumber);
            mGameItemList.add(new GameItem(ActivityNewGame.mCheckedBoxes.get(i), mParNm.getText().toString(), R.drawable.ic_minus, R.drawable.ic_plus));
        }

        mRecyclerView = findViewById(R.id.gameRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new GameAdapter(mGameItemList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        /** Set buttons logic **/
        mAdapter.setOnItemClickListener(new GameAdapter.OnItemClickListener() {
            @Override
            public void onMinusClick(int position) {
                String parNum = mGameItemList.get(position).getText2();
                int intParNm = Integer.valueOf(parNum);

                if (intParNm != 1) {
                    intParNm -= 1;
                    mGameItemList.get(position).changeText2(Integer.toString(intParNm));
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onPlusClick(int position) {
                String parNum = mGameItemList.get(position).getText2();
                int intParNm = Integer.valueOf(parNum);

                if (intParNm != 99) {
                    intParNm += 1;
                    mGameItemList.get(position).changeText2(Integer.toString(intParNm));
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * Back button
     **/
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quit game");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Return to main menu")
                .setCancelable(false)

                /** If user click "yes" go to MainActivity **/
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ActivityGame.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                        Intent getIntent = getIntent();
                        holeCounter = 1;
                        mHoleNm.setText(holeCounter + "/" + getIntent.getStringExtra("HOLESNM"));
                    }
                })

                /** If user click "no" game keeps going **/
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        /** Create Dialog **/
        AlertDialog alert = builder.create();
        alert.show();
        alert.getButton(alert.BUTTON_NEGATIVE).setTextColor(R.style.AlertDialogCustom);
        alert.getButton(alert.BUTTON_POSITIVE).setTextColor(R.style.AlertDialogCustom);
    }
}