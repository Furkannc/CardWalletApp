package com.example.cardwallet;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {



    private static List<Card> CardList=new ArrayList<>();
    private static Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView cardName;
        private final TextView CardUserName;
        private final TextView CardNum;
        private final ImageView CardIcon;
        private final ConstraintLayout cardView;

        public ViewHolder(View v) {
            super(v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    Intent intent=new Intent(context,CardAdd.class);
                    intent.putExtra("id",CardList.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });
            cardView=(ConstraintLayout) v.findViewById(R.id.Card);
            cardName = (TextView) v.findViewById(R.id.txtCardName);
            CardUserName = (TextView) v.findViewById(R.id.CardUserName);
            CardNum = (TextView) v.findViewById(R.id.CardNum);
            CardIcon = (ImageView) v.findViewById(R.id.CardIcon);
        }

    }

    public CustomAdapter(List<Card> cardList,Context cnt) {
        CardList = cardList;
        context = cnt;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false);

        return new ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        String Number=CardList.get(position).getCardNum().substring(0,4)+" "+CardList.get(position).getCardNum().substring(4,8)+" "+CardList.get(position).getCardNum().substring(8,12)+" "+CardList.get(position).getCardNum().substring(12,16);
        viewHolder.cardName.setText(CardList.get(position).getName());
        viewHolder.CardUserName.setText(CardList.get(position).getCardUserName());
        viewHolder.CardNum.setText(Number);
        if(CardList.get(position).getCardNum().charAt(0) == '5')
            viewHolder.CardIcon.setImageResource(R.drawable.mc);
        else
            viewHolder.CardIcon.setImageResource(R.drawable.visa);

        viewHolder.cardView.setBackgroundResource(CardList.get(position).getColor());
    }

    @Override
    public int getItemCount() {
        return CardList.size();
    }
}