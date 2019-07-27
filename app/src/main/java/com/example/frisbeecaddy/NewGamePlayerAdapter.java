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
    private OnItemsCheckStateListener checkStateListener;

    private int checkedItems = 0;

    public void setOnItemsCheckStateListener(OnItemsCheckStateListener checkStateListener) {
        this.checkStateListener = checkStateListener;
    }

    public interface OnItemsCheckStateListener {
        void onItemCheckStateChanged(int checkedItemCounter);
    }

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
    public void onBindViewHolder(@NonNull final NewGamePlayerViewHolder holder, final int position) {
        final NewGamePlayerItem currentItem = mNewGamePlayerList.get(position);

        /** In some cases, this will prevent unwanted situations **/
        holder.mCheckBox.setOnCheckedChangeListener(null);

        /** If true, checkbox will be selected, else unselected **/
        holder.mCheckBox.setChecked(currentItem.getCheckBox());

        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                /** set object's last status **/
                currentItem.setSelected(isChecked);

                if (isChecked) {
                    checkedItems++;
                } else {
                    checkedItems--;
                }

                checkStateListener.onItemCheckStateChanged(checkedItems);
            }
        });

        /** Set name **/
        holder.mName.setText(currentItem.getmText());
    }

    @Override
    public int getItemCount() {
        return mNewGamePlayerList.size();
    }
}