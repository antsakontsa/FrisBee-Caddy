package com.example.frisbeecaddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResumeAdapter extends RecyclerView.Adapter<ResumeAdapter.ResumeViewHolder> {
    private ArrayList<ResumeItem> mResumeList;

    public static class ResumeViewHolder extends RecyclerView.ViewHolder {
        public RadioButton mRadioButton;
        public TextView mCourseName, mDate;

        public ResumeViewHolder(@NonNull View itemView) {
            super(itemView);
            mRadioButton = itemView.findViewById(R.id.resumeRadioButton);
            mCourseName = itemView.findViewById(R.id.resumeCourseName);
            mDate = itemView.findViewById(R.id.resumeDate);
        }
    }

    public ResumeAdapter(ArrayList<ResumeItem> resumeList) {
        mResumeList = resumeList;
    }

    @NonNull
    @Override
    public ResumeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.resume_item, parent, false);
        ResumeViewHolder evh = new ResumeViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ResumeViewHolder holder, final int position) {
        final ResumeItem currentItem = mResumeList.get(position);

        holder.mRadioButton.setSelected(currentItem.getRadioButton());
        holder.mCourseName.setText(currentItem.getCourseName());
        holder.mDate.setText(currentItem.getDate());
    }

    @Override
    public int getItemCount() {
        return mResumeList.size();
    }
}