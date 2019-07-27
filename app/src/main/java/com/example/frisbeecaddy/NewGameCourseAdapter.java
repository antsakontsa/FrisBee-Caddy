package com.example.frisbeecaddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewGameCourseAdapter extends RecyclerView.Adapter<NewGameCourseAdapter.NewGameCourseViewHolder> {
    private ArrayList<NewGameCourseItem> mCourseList;
    private NewGamePlayerAdapter.OnItemsCheckStateListener checkStateListener;

    public void setOnItemsCheckStateListener(NewGamePlayerAdapter.OnItemsCheckStateListener checkStateListener) {
        this.checkStateListener = checkStateListener;
    }

    public NewGameCourseItem getSelectedItem() {
        return mCourseList.get(selectedPosition);
    }

    private int checkedItems = 0;

    private int selectedPosition = -1; /** No selection by default **/

    public static class NewGameCourseViewHolder extends RecyclerView.ViewHolder {
        public RadioButton mRadioButton;
        public TextView mCourseName, mHolesTxt, mHolesNm, mParTxt, mParNm;

        public NewGameCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            mRadioButton = itemView.findViewById(R.id.radioButton);
            mCourseName = itemView.findViewById(R.id.course_name2);
            mHolesTxt = itemView.findViewById(R.id.holes_txt2);
            mHolesNm = itemView.findViewById(R.id.holes_number2);
            mParTxt = itemView.findViewById(R.id.par_txt2);
            mParNm = itemView.findViewById(R.id.par_nm2);
        }
    }

    public NewGameCourseAdapter(ArrayList<NewGameCourseItem> courseList) {
        mCourseList = courseList;
    }

    @NonNull
    @Override
    public NewGameCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_game_course_item, parent, false);
        NewGameCourseViewHolder evh = new NewGameCourseViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull final NewGameCourseViewHolder holder, final int position) {
        final NewGameCourseItem currentItem = mCourseList.get(position);

        /** This can prevent some unwanted actions in some cases **/
        holder.mRadioButton.setOnCheckedChangeListener(null);

        holder.mRadioButton.setChecked(selectedPosition == position);

        holder.mRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                notifyItemChanged(selectedPosition);
                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(selectedPosition);

                /** This part considers checking for "start game" buttons enabling **/
                if (isChecked) {
                    checkedItems++;
                } else {
                    checkedItems--;
                }

                checkStateListener.onItemCheckStateChanged(checkedItems);
            }
        });

        holder.mCourseName.setText(currentItem.getCourseName());
        holder.mHolesTxt.setText(currentItem.getHolesTxt());
        holder.mHolesNm.setText(currentItem.getHolesNm());
        holder.mParTxt.setText(currentItem.getParTxt());
        holder.mParNm.setText(currentItem.getParNm());
    }

    @Override
    public int getItemCount() {
        return mCourseList.size();
    }
}