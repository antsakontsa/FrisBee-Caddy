package com.example.frisbeecaddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {
    private ArrayList<CoursesItem> mCourseList;

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        public TextView mCourseName, mHolesTxt, mHolesNm;
        public ImageView mDelete;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            mCourseName = itemView.findViewById(R.id.course_name);
            mHolesTxt = itemView.findViewById(R.id.holes);
            mHolesNm = itemView.findViewById(R.id.holes_number);
            mDelete = itemView.findViewById(R.id.img_delete);
        }
    }

    public CoursesAdapter(ArrayList<CoursesItem> courseList) {
        mCourseList = courseList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_item, parent, false);
        CourseViewHolder evh = new CourseViewHolder(v);

        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        CoursesItem currentItem = mCourseList.get(position);

        holder.mCourseName.setText(currentItem.getCourseName());
        holder.mHolesTxt.setText(currentItem.getHolesTxt());
        holder.mHolesNm.setText(currentItem.getHolesNm());
        holder.mDelete.setImageResource(currentItem.getDelete());
    }

    @Override
    public int getItemCount() {
        return mCourseList.size();
    }
}
