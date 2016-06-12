package io.github.gaiusandhartsolutions.newevolve;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ErikGaius on 6/11/2016.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    public List<CardData> cardDataList;

    public CardAdapter(List<CardData> cardDataList) {
        this.cardDataList = cardDataList;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_layout, parent, false);
        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        CardData cardData = cardDataList.get(position);
        holder.cardImageView.setImageResource(cardData.image);
        holder.cardTitleText.setText(cardData.title);
        holder.cardContentText.setText(cardData.content);
        if (cardData.clickListener != null) {
            holder.cardImageView.setOnClickListener(cardData.clickListener);
        }
    }

    @Override
    public int getItemCount() {
        return this.cardDataList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        protected ImageView cardImageView;
        protected TextView cardTitleText;
        protected TextView cardContentText;

        public CardViewHolder(View view) {
            super(view);
            cardImageView = (ImageView) view.findViewById(R.id.card_image);
            cardTitleText = (TextView) view.findViewById(R.id.card_title);
            cardContentText = (TextView) view.findViewById(R.id.card_content);
        }
    }
}
