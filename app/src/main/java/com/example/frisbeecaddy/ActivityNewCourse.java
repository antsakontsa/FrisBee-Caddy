package com.example.frisbeecaddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityNewCourse extends AppCompatActivity {
    private Button save, plus, minus;
    private EditText numberOfHoles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);

        numberOfHoles = findViewById(R.id.number_of_holes_number);

        /** When "minus" button been clicked, minus 1 from number of holes box **/
        minus = findViewById(R.id.minus_btn);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        /** When "plus" button been clicked, add 1 to number of holes box **/
        plus = findViewById(R.id.plus_btn);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
