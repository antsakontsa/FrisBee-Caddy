package com.example.frisbeecaddy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
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

        /** Arrow Left Button **/
        mBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHoleNm = findViewById(R.id.gameHoleNumber);

                if (holeCounter > 1) {
                    mForwardArrow.setImageResource(R.drawable.ic_arrow_right);

                    /** Set previous hole number **/
                    holeCounter--;

                    /** Set Hole number X/X **/
                    Intent intent = getIntent();
                    mHoleNm.setText(holeCounter + "/" + intent.getStringExtra("HOLESNM"));

                    /** Set par number (Par: X) according to the hole number **/
                    mParNm = findViewById(R.id.gameParNumber);
                    mParNm.setText(intent.getStringArrayListExtra("PARNUMBERSINDIVIDUALLY").get(holeCounter - 1));

                    /** Display changes **/
                    updateGameArrayList(Integer.parseInt(mParNm.getText().toString()));
                }
            }
        });

        /** Arrow Right Button **/
        mForwardArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHoleNm = findViewById(R.id.gameHoleNumber);

                Intent intent = getIntent();
                int holesNm = Integer.parseInt(intent.getStringExtra("HOLESNM"));

                if (holeCounter == holesNm - 1) {
                    mForwardArrow.setImageResource(R.drawable.ic_finish);

                    /** Set next hole number **/
                    holeCounter++;

                    /** Set Hole number X/X **/
                    mHoleNm.setText(holeCounter + "/" + intent.getStringExtra("HOLESNM"));

                    /** Set par number (Par: X) according to the hole number **/
                    mParNm = findViewById(R.id.gameParNumber);
                    mParNm.setText(intent.getStringArrayListExtra("PARNUMBERSINDIVIDUALLY").get(holeCounter - 1));

                    /** Display changes **/
                    updateGameArrayList(Integer.parseInt(mParNm.getText().toString()));
                } else if (holeCounter < holesNm) {
                    mForwardArrow.setImageResource(R.drawable.ic_arrow_right);

                    /** Set next hole number **/
                    holeCounter++;

                    /** Set Hole number X/X **/
                    mHoleNm.setText(holeCounter + "/" + intent.getStringExtra("HOLESNM"));

                    /** Set par number (Par: X) according to the hole number **/
                    mParNm = findViewById(R.id.gameParNumber);
                    mParNm.setText(intent.getStringArrayListExtra("PARNUMBERSINDIVIDUALLY").get(holeCounter - 1));

                    /** Display changes **/
                    updateGameArrayList(Integer.parseInt(mParNm.getText().toString()));
                }
            }
        });
    }

    private void updateGameArrayList(int parVal) {
        for (GameItem model : mGameItemList) {
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

            mGameItemList.add(new GameItem(ActivityNewGame.mCheckedBoxes.get(i), mParNm.getText().toString(), R.drawable.ic_minus, R.drawable.ic_plus, "TTL :", "0"));
        }

        mRecyclerView = findViewById(R.id.gameRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new GameAdapter(mGameItemList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        /** Set buttons logic **/
        mAdapter.setOnItemClickListener(new GameAdapter.OnItemClickListener() {
            int totalCounter = 0;

            /** When player click minus button **/
            @Override
            public void onMinusClick(int position) {
                /** Take player par number **/
                String parNum = mGameItemList.get(position).getText2();
                int intParNm = Integer.valueOf(parNum);

                if (intParNm != 1) {
                    /** Change that number to new number **/
                    intParNm -= 1;
                    /** Change Counter number **/
                    totalCounter -= 1;

                    /** Find particular item for totalCounter **/
                    String totalCounter = ((TextView) mRecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.game_total_number)).getText().toString();

                    int intTotalCounter = Integer.parseInt(totalCounter);

                    /** Set changed par number to the particular player (where button is clicked) **/
                    mGameItemList.get(position).changeText2(Integer.toString(intParNm));
                    /** Set changed TTL number (totalCounter) to the particular player (where button is clicked) **/
                    mGameItemList.get(position).changeText4(Integer.toString(intTotalCounter - 1));
                    mAdapter.notifyDataSetChanged();
                }
            }

            /** When player click plus button **/
            @Override
            public void onPlusClick(int position) {
                /** Take player par number **/
                String parNum = mGameItemList.get(position).getText2();
                int intParNm = Integer.valueOf(parNum);

                if (intParNm != 99) {
                    /** Change that number to new number **/
                    intParNm += 1;
                    /** Change Counter number **/
                    totalCounter += 1;

                    /** Find particular item for totalCounter **/
                    String totalCounter = ((TextView) mRecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.game_total_number)).getText().toString();

                    int intTotalCounter = Integer.parseInt(totalCounter);

                    /** Set changed par number to the particular player (where button is clicked) **/
                    mGameItemList.get(position).changeText2(Integer.toString(intParNm));
                    /** Set changed TTL number (totalCounter) to the particular player (where button is clicked) **/
                    mGameItemList.get(position).changeText4(Integer.toString(intTotalCounter + 1));
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