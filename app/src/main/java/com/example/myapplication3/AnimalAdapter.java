package com.example.myapplication3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {

    // 点击回调：把点击事件交给 Activity 处理
    public interface OnItemClickListener {
        void onItemClick(Animal animal);
    }

    private final List<Animal> data;
    private final OnItemClickListener listener;

    public AnimalAdapter(List<Animal> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_animal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Animal animal = data.get(position);
        holder.image.setImageResource(animal.getImageRes());
        holder.name.setText(animal.getName());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(animal));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    // ViewHolder：把 itemView 里的控件引用存起来，滚动时复用，不用反复 findViewById
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.itemImage);
            name = itemView.findViewById(R.id.itemName);
        }
    }
}
