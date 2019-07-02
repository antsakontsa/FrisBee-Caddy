package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ActivityCourses extends AppCompatActivity {
    private Button add_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        setButtons();
    }

    private void setButtons() {
        /** When "add new course" button been clicked, go to ActivityNewCourse **/
        add_course = findViewById(R.id.add_new_course_btn);
        add_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityCourses.this, ActivityNewCourse.class);
                startActivity(intent);
            }
        });
    }
}
