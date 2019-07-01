package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ActivityNewCourse extends AppCompatActivity {
    private ArrayList<NewCourseItem> mNewCourseList;

    private RecyclerView mRecyclerView;
    private NewCourseAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button save, plus, minus;
    private EditText courseName;
    private TextView number, par;

    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);

        createNewCourselist();
        buildRecyclerView();
        setButtons();
        disableMinusBtn();
        disablePlusBtn();
    }

    /**
     * ALL OUTER METHODS GOES UNDER THIS
     ********************************************************************************************/
    private void sortArrayList() {
        Collections.sort(mNewCourseList, new Comparator<NewCourseItem>() {
            @Override
            public int compare(NewCourseItem t1, NewCourseItem t2) {
                return Integer.valueOf(t1.getText1()).compareTo(Integer.valueOf(t2.getText1()));
            }
        });

        mAdapter.notifyDataSetChanged();
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerViewNewCourse);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new NewCourseAdapter(mNewCourseList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new NewCourseAdapter.OnItemClickListener() {
            @Override
            public void onMinusClick(int position) {
                String string = mNewCourseList.get(position).getText2();
                int intString = Integer.valueOf(string);

                if (intString != 2) {
                    intString -= 1;
                    mNewCourseList.get(position).changeText2(Integer.toString(intString));
                    mAdapter.notifyItemChanged(position);
                }
            }

            @Override
            public void onPlusClick(int position) {
                String string = mNewCourseList.get(position).getText2();
                int intString = Integer.valueOf(string);

                if (intString != 5) {
                    intString += 1;
                    mNewCourseList.get(position).changeText2(Integer.toString(intString));
                    mAdapter.notifyItemChanged(position);
                }
            }
        });
    }

    private void createNewCourselist() {
        mNewCourseList = new ArrayList<>();

        number = findViewById(R.id.number_of_holes_number);
        int intNumber = Integer.parseInt(number.getText().toString());
        int i;
        counter = 1;

        for (i = 1; i <= intNumber; i++) {
            mNewCourseList.add(new NewCourseItem(Integer.toString(counter), "3", R.drawable.ic_minus, R.drawable.ic_plus));
            counter += 1;
        }
    }

    private void insertItem(int position) {
        mNewCourseList.add(position, new NewCourseItem(Integer.toString(counter - 1), "3", R.drawable.ic_minus, R.drawable.ic_plus));
        mAdapter.notifyDataSetChanged();
    }

    private void removeItem(int position) {
        mNewCourseList.remove(position);
        mAdapter.notifyDataSetChanged();
    }

    private void disableMinusBtn() {
        number = findViewById(R.id.number_of_holes_number);
        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /** Have to be here even tho it's not containing anything **/
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                number = findViewById(R.id.number_of_holes_number);
                int t = Integer.parseInt(number.getText().toString());

                if (t == 1) {
                    minus.setEnabled(false);
                } else {
                    minus.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                /** Have to be here even tho it's not containing anything **/
            }
        });
    }

    private void disablePlusBtn() {
        number = findViewById(R.id.number_of_holes_number);
        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /** Have to be here even tho it's not containing anything **/
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                number = findViewById(R.id.number_of_holes_number);
                int t = Integer.parseInt(number.getText().toString());

                if (t == 99) {
                    plus.setEnabled(false);
                } else {
                    plus.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                /** Have to be here even tho it's not containing anything **/
            }
        });
    }

    private void setButtons() {
        number = findViewById(R.id.number_of_holes_number);
        save = findViewById(R.id.save_course_btn);
        save.setEnabled(false);

        /** When "plus" button clicked, add 1 to number of holes number **/
        plus = findViewById(R.id.plus_btn);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int t = Integer.parseInt(number.getText().toString());
                number.setText(Integer.toString(t + 1));
                counter++;

                /** Then add item on its position **/
                insertItem(0);
                sortArrayList();
            }
        });

        /** When "minus" button clicked, minus 1 from number of holes number **/
        minus = findViewById(R.id.minus_btn);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int t = Integer.parseInt(number.getText().toString());
                number.setText(Integer.toString(t - 1));
                counter--;

                /** Then remove item on its position **/
                int intNumber = Integer.parseInt(number.getText().toString());
                int position = intNumber;
                removeItem(position);
            }
        });

        /** When "save courses" button been clicked, save and go to ActivityCourses **/
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityNewCourse.this, ActivityCourses.class);
                startActivity(intent);
            }
        });

        /** This enable "save course" button when user insert some input **/
        courseName = findViewById(R.id.course_name_input);
        courseName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /** Have to be here even tho it's not containing anything **/
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String course_input = courseName.getText().toString().trim();

                save.setEnabled(!course_input.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                /** Have to be here even tho it's not containing anything **/
            }
        });
    }
}