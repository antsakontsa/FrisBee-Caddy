package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityNewCourse extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button save, plus, minus;
    private TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);

        ArrayList<NewCourseItem> newCourseList = new ArrayList<>();
        newCourseList.add(new NewCourseItem("1", "3", R.drawable.ic_minus, R.drawable.ic_plus));
        newCourseList.add(new NewCourseItem("2", "3", R.drawable.ic_minus, R.drawable.ic_plus));
        newCourseList.add(new NewCourseItem("3", "3", R.drawable.ic_minus, R.drawable.ic_plus));
        newCourseList.add(new NewCourseItem("4", "3", R.drawable.ic_minus, R.drawable.ic_plus));
        newCourseList.add(new NewCourseItem("5", "3", R.drawable.ic_minus, R.drawable.ic_plus));
        newCourseList.add(new NewCourseItem("6", "3", R.drawable.ic_minus, R.drawable.ic_plus));
        newCourseList.add(new NewCourseItem("7", "3", R.drawable.ic_minus, R.drawable.ic_plus));
        newCourseList.add(new NewCourseItem("8", "3", R.drawable.ic_minus, R.drawable.ic_plus));
        newCourseList.add(new NewCourseItem("9", "3", R.drawable.ic_minus, R.drawable.ic_plus));

        mRecyclerView = findViewById(R.id.recyclerViewNewCourse);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new NewCourseAdapter(newCourseList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        setButtons();
    }

    /**
     * ALL OUTER METHODS GOES UNDER THIS
     ********************************************************************************************/
    private void setButtons() {

        number = findViewById(R.id.number_of_holes_number);

        /** When "plus" button clicked, add 1 to number of holes number **/
        plus = findViewById(R.id.plus_btn);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int t = Integer.parseInt(number.getText().toString());

                if (t == 99) {
                    number.setText(String.valueOf(99));
                } else {
                    number.setText(String.valueOf(t+1));
                }
            }
        });

        /** When "minus" button clicked, minus 1 from number of holes number **/
        minus = findViewById(R.id.minus_btn);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int t = Integer.parseInt(number.getText().toString());

                if (t == 1) {
                    number.setText(String.valueOf(1));
                } else {
                    number.setText(String.valueOf(t-1));
                }
            }
        });

        /** When "save courses" button been clicked, save and go to ActivityCourses **/
        save = findViewById(R.id.save_course_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityNewCourse.this, ActivityCourses.class);
                startActivity(intent);
            }
        });
    }
}
