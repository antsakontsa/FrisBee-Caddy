package com.example.frisbeecaddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewGamePlayerAdapter extends RecyclerView.Adapter<NewGamePlayerAdapter.NewGamePlayerViewHolder> {
    private ArrayList<NewGamePlayerItem> mNewGamePlayerList;

    public static class NewGamePlayerViewHolder extends RecyclerView.ViewHolder {
        public CheckBox mCheckBox;
        public TextView mName;

        public NewGamePlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.name_checkbox);
            mName = itemView.findViewById(R.id.player_name);
        }
    }

    public NewGamePlayerAdapter(ArrayList<NewGamePlayerItem> newGamePlayerList) {
        mNewGamePlayerList = newGamePlayerList;
    }

    @NonNull
    @Override
    public NewGamePlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_game_player_item, parent, false);
        NewGamePlayerViewHolder evh = new NewGamePlayerViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull final NewGamePlayerViewHolder holder, int position) {
        final NewGamePlayerItem currentItem = mNewGamePlayerList.get(position);

        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (compoundButton.isPressed()) {
                    holder.mCheckBox.setChecked(currentItem.getCheckBox());
                }
            }
        });

        holder.mName.setText(currentItem.getmText());
    }

    @Override
    public int getItemCount() {
        return mNewGamePlayerList.size();
    }
}