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
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        public TextView mCourseName, mHolesTxt, mHolesNm, mParTxt, mParNm;
        public ImageView mDelete;

        public CourseViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mCourseName = itemView.findViewById(R.id.course_name);
            mHolesTxt = itemView.findViewById(R.id.holes_txt);
            mHolesNm = itemView.findViewById(R.id.holes_number);
            mParTxt = itemView.findViewById(R.id.par_txt);
            mParNm = itemView.findViewById(R.id.par_nm);
            mDelete = itemView.findViewById(R.id.img_delete);

            mDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public CoursesAdapter(ArrayList<CoursesItem> courseList) {
        mCourseList = courseList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_item, parent, false);
        CourseViewHolder evh = new CourseViewHolder(v, mListener);

        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        CoursesItem currentItem = mCourseList.get(position);

        holder.mCourseName.setText(currentItem.getCourseName());
        holder.mHolesTxt.setText(currentItem.getHolesTxt());
        holder.mHolesNm.setText(currentItem.getHolesNm());
        holder.mParTxt.setText(currentItem.getParTxt());
        holder.mParNm.setText(currentItem.getParNm());
        holder.mDelete.setImageResource(currentItem.getDelete());
    }

    @Override
    public int getItemCount() {
        return mCourseList.size();
    }
}