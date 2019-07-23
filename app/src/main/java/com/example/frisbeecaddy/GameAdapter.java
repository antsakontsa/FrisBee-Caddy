package com.example.frisbeecaddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {
    private ArrayList<GameItem> mGameList;

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextPlayer, mTextPar;
        public ImageView mImageMinus, mImagePlus;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextPlayer = itemView.findViewById(R.id.gameNameRecycler);
            mTextPar = itemView.findViewById(R.id.gameParNumberRecycler);
            mImageMinus = itemView.findViewById(R.id.game_minus_btn);
            mImagePlus = itemView.findViewById(R.id.game_plus_btn);
        }
    }

    public GameAdapter(ArrayList<GameItem> gameList) {
        mGameList = gameList;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item, parent, false);
        GameViewHolder evh = new GameViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        GameItem currentItem = mGameList.get(position);

        holder.mTextPlayer.setText(currentItem.getText1());
        holder.mTextPar.setText(currentItem.getText2());
        holder.mImageMinus.setImageResource(currentItem.getImageMinus());
        holder.mImagePlus.setImageResource(currentItem.getImagePlus());
    }

    @Override
    public int getItemCount() {
        return mGameList.size();
    }
}
