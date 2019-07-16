package com.example.frisbeecaddy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ActivityCourses extends AppCompatActivity {
    public static ArrayList<CoursesItem> mCourseList;
    private RecyclerView mRecyclerView;
    private CoursesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button add_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        loadData(this);
        buildRecyclerView();
        addItem();
        sortArrayList();
        setButtons();
        saveData();
    }

    public void addItem() {
        /** This data comes from ActivityNewCourse **/
        if (getIntent().getStringExtra("COURSENAME") != null) {
            /** Store new course items in arraylist **/
            ArrayList<NewCourseItem> itemArray = (ArrayList<NewCourseItem>) getIntent().getSerializableExtra("COURSELIST");

            /** Count par number for item **/
            int parCount = 0;
            for (int i = 0; i < itemArray.size(); i++) {
                parCount += Integer.parseInt(itemArray.get(i).getText2());
            }

            /** Receive primitive data from and make new item with that information **/
            mCourseList.add(new CoursesItem(getIntent().getStringExtra("COURSENAME"), "Holes:", getIntent().getStringExtra("HOLENUMBER"), "Par:", Integer.toString(parCount), R.drawable.ic_delete));

            /** Give notification for user that course saved successfully **/
             Toast toast = Toast.makeText(getApplicationContext(),
             "Course: \"" + getIntent().getStringExtra("COURSENAME") + "\" saved successfully", Toast.LENGTH_LONG);
             toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 400);
             toast.show();

            /** When item added to the list go back to main menu **/
            Intent intent = new Intent(ActivityCourses.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
    }

    private void saveData() {
        /** save data to shared pref **/
        SharedPreferences prefs = getSharedPreferences("shared preference3", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        try {
            editor.putString("SharedPrefKey3", ObjectSerializer.serialize(mCourseList));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();
    }

    public static void loadData(Context context) {
        if (mCourseList == null) {
            mCourseList = new ArrayList<>();
        }

        SharedPreferences prefs = context.getSharedPreferences("shared preference3", Context.MODE_PRIVATE);
        try {
            mCourseList = (ArrayList<CoursesItem>) ObjectSerializer.deserialize(prefs.getString("SharedPrefKey3", ObjectSerializer.serialize(new ArrayList<CoursesItem>())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerViewCourses);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CoursesAdapter(mCourseList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CoursesAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    private void sortArrayList() {
        Collections.sort(mCourseList, new Comparator<CoursesItem>() {
            @Override
            public int compare(CoursesItem t1, CoursesItem t2) {
                return t1.getCourseName().compareTo(t2.getCourseName());
            }
        });

        mAdapter.notifyDataSetChanged();
    }

    private void removeItem(final int position) {
        /** Create dialog which ask if user is sure about delete name from list **/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete player");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to delete course: \"" + mCourseList.get(position).getCourseName() + "\"?")
                .setCancelable(false)

                /** If user click "ok" button, delete player from list and save changes **/
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mCourseList.remove(position);
                        mAdapter.notifyDataSetChanged();
                        saveData();
                    }
                })

                /** If user click "cancel" button, name won't delete from list **/
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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

    private void setButtons() {
        /** When "add new course" button been clicked, go to ActivityNewCourse **/
        add_course = findViewById(R.id.add_new_course_btn);
        add_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityCourses.this, ActivityNewCourse.class);
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
        Intent intent = new Intent(ActivityCourses.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}