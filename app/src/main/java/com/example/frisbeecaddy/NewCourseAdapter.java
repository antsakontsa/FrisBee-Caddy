package com.example.frisbeecaddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewCourseAdapter extends RecyclerView.Adapter<NewCourseAdapter.NewCourseViewHolder> {
    private ArrayList<NewCourseItem> mNewCourseList;

    public static class NewCourseViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1, mTextView2;
        public ImageView mImageView1, mImageView2;

        public NewCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.hole_number);
            mTextView2 = itemView.findViewById(R.id.par_number);
            mImageView1 = itemView.findViewById(R.id.item_minus_btn);
            mImageView2 = itemView.findViewById(R.id.item_plus_btn);
        }
    }

    public NewCourseAdapter(ArrayList<NewCourseItem> newCourseList) {
        mNewCourseList = newCourseList;
    }

    @NonNull
    @Override
    public NewCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_course_item, parent, false);
        NewCourseViewHolder evh = new NewCourseViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull NewCourseViewHolder holder, int position) {
        NewCourseItem currentItem = mNewCourseList.get(position);

        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
        holder.mImageView1.setImageResource(currentItem.getImageMinus());
        holder.mImageView2.setImageResource(currentItem.getImagePlus());
    }

    @Override
    public int getItemCount() {
        return mNewCourseList.size();
    }
}
