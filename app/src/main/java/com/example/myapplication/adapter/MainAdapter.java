package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.DataBase.MyConstants;
import com.example.myapplication.DataBase.MyDataBaseManager;
import com.example.myapplication.R;
import com.example.myapplication.TargetsOfDayActivity;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private Context context;
    private List<ListItem> mainArray;

    public MainAdapter(Context context) {
        this.context = context;
        mainArray = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_layout, viewGroup,false);
        return new MyViewHolder(view, context, mainArray);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.setData(mainArray.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return mainArray.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle;
        private Context context;
        private List<ListItem> mainArray;

        public MyViewHolder(@NonNull View itemView, Context context, List<ListItem> mainArray) {
            super(itemView);
            this.context = context;
            this.mainArray = mainArray;

            tvTitle = itemView.findViewById(R.id.tvTitle);
            itemView.setOnClickListener(this);
        }
        public void setData(String title) {
            tvTitle.setText(title);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, TargetsOfDayActivity.class);
            intent.putExtra(MyConstants.LIST_ITEM_INTENT, mainArray.get(getAdapterPosition()));
            intent.putExtra(MyConstants.EDIT_STATE, false);
            context.startActivity(intent);
        }
    }

    public void updateAdapter(List<ListItem> newList) {
        mainArray.clear();
        mainArray.addAll(newList);
        notifyDataSetChanged();
    }

    public void removeItem(int pos, MyDataBaseManager dataBaseManager) {
        dataBaseManager.delete(mainArray.get(pos).getId());
        mainArray.remove(pos);
        notifyItemRangeChanged(0, mainArray.size());
        notifyItemRemoved(pos);
    }
}
