package com.example.firstproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ThirdTabMainAdapter extends RecyclerView.Adapter<ThirdTabMainAdapter.Holder> {

    private Context context;
    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<String> numberList = new ArrayList<>();
    private String myPhoneNum;
    private String friend;

    public ThirdTabMainAdapter(Context context, ArrayList<String> nameList, ArrayList<String> numberList) {
        this.context = context;
        this.nameList = nameList;
        this.numberList = numberList;

    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.third_tab_main_item_view, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.name.setText(nameList.get(position));
        System.out.println(nameList.get(position));
        holder.number.setText(numberList.get(position));
        System.out.println(numberList.get(position));
    }

    @Override
    public int getItemCount() {
        return nameList.size();

    }

    public class Holder extends RecyclerView.ViewHolder{
        //public TextView name;
        public TextView name;
        public TextView number;

        public Holder(View view){
            super(view);
            //name = (TextView) view.findViewById(R.id.name);
            name = (TextView) view.findViewById(R.id.thirdTabMainName);
            number = (TextView) view.findViewById(R.id.thirdTabMainNumber);
        }
    }
}