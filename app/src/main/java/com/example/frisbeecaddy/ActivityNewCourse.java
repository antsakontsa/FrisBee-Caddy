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
    private TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);

        setButtons();
    }

    /**
     * ALL OUTER METHODS GOES UNDER THIS
     ********************************************************************************************/
    public void setButtons() {

        number = findViewById(R.id.number_of_holes_number);

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
