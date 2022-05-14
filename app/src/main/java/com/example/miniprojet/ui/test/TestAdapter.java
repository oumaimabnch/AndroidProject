package com.example.miniprojet.ui.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.miniprojet.R;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

    ArrayList<String> s;
    TestAdapter(ArrayList<String> s) {
        this.s = s;
    }


    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
       holder.bindView(s.get(position));
    }

    @Override
    public int getItemCount() {
        return s.size();
    }

    static class TestViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView img;
        public TestViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_test);
            tv = itemView.findViewById(R.id.tv_test);

        }

        public void bindView(String s) {
            tv.setText(s);
            /*img.setImageResource(R.drawable.logo);
            Glide.with(img.getContext()).load("").into(img);*/
        }
    }
}
