package com.example.lab2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BreedAdapter extends RecyclerView.Adapter <BreedAdapter.BreedViewHolder> {

    private Context mContext;
    private List<Breed> mBreedArray;

    public BreedAdapter(Context mContext, List<Breed> mBreedArray) {
        this.mContext = mContext;
        this.mBreedArray = mBreedArray;
    }

    @NonNull
    @Override
    public BreedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup, false);
        return new BreedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreedViewHolder breedViewHolder, int i) {
        Breed curItem = mBreedArray.get(i);
        String name = curItem.getName();
        String temperament = curItem.getTemperament();
        String description = curItem.getDescription();
        int id = curItem.getId();

        breedViewHolder.mName.setText(name);
        breedViewHolder.mTemperament.setText("Temperament: " + temperament);
        breedViewHolder.mDescription.setText("Description: " + description);
        breedViewHolder.mId.setText("Id: " + id);

    }

    @Override
    public int getItemCount() {
        return mBreedArray.size();
    }

    public class BreedViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public TextView mTemperament;
        public TextView mDescription;
        public TextView mId;

        public BreedViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.textViewBreedName);
            mTemperament = itemView.findViewById(R.id.textViewTemperament);
            mDescription = itemView.findViewById(R.id.textViewDescription);
            mId = itemView.findViewById(R.id.textViewId);
        }
    }
}
