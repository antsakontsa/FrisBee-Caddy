package com.example.frisbeecaddy;

import android.content.Context;
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

    private GameAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onMinusClick(int position);

        void onPlusClick(int position);
    }

    public void setOnItemClickListener(GameAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public Context context;

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextPlayer, mTextPar, mTotalTxt, mNumberTotal;
        public ImageView mImageMinus, mImagePlus;

        public GameViewHolder(@NonNull View itemView, final GameAdapter.OnItemClickListener listener) {
            super(itemView);
            mTextPlayer = itemView.findViewById(R.id.gameNameRecycler);
            mTextPar = itemView.findViewById(R.id.gameParNumberRecycler);
            mImageMinus = itemView.findViewById(R.id.game_minus_btn);
            mImagePlus = itemView.findViewById(R.id.game_plus_btn);
            mTotalTxt = itemView.findViewById(R.id.game_total_txt);
            mNumberTotal = itemView.findViewById(R.id.game_total_number);

            mImageMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            listener.onMinusClick(position);
                        }
                    }
                }
            });

            mImagePlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            listener.onPlusClick(position);
                        }
                    }
                }
            });
        }
    }

    public GameAdapter(ArrayList<GameItem> gameList) {
        mGameList = gameList;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item, parent, false);
        GameViewHolder evh = new GameViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull final GameViewHolder holder, int position) {
        final GameItem currentItem = mGameList.get(position);

        holder.mTextPlayer.setText(currentItem.getText1());
        holder.mTextPar.setText(currentItem.getText2());

        /** Set background for item numbers **/
        if (Integer.parseInt(holder.mTextPar.getText().toString()) == 1) {
            holder.mTextPar.setBackgroundResource(R.drawable.border_box_yellow);
        } else if (Integer.parseInt(holder.mTextPar.getText().toString()) < Integer.parseInt(ActivityGame.mParNm.getText().toString())) {
            holder.mTextPar.setBackgroundResource(R.drawable.border_box_blue);
        } else if (Integer.parseInt(holder.mTextPar.getText().toString()) > Integer.parseInt(ActivityGame.mParNm.getText().toString())) {
            holder.mTextPar.setBackgroundResource(R.drawable.border_box_red);
        } else if (Integer.parseInt(holder.mTextPar.getText().toString()) == Integer.parseInt(ActivityGame.mParNm.getText().toString())) {
            holder.mTextPar.setBackgroundResource(R.drawable.border_box_green);
        }

        holder.mImageMinus.setImageResource(currentItem.getImageMinus());
        holder.mImagePlus.setImageResource(currentItem.getImagePlus());
        holder.mTotalTxt.setText(currentItem.getText3());
        holder.mNumberTotal.setText(currentItem.getText4());
    }

    @Override
    public int getItemCount() {
        return mGameList.size();
    }
}