package com.example.firstproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class FirstTabAdapter extends RecyclerView.Adapter<FirstTabAdapter.Holder> {

    private Context context;
    private List<ContactsVO> list = new ArrayList<>();
int[] images = new int[]{
        R.drawable.profile1,
        R.drawable.profile2,
        R.drawable.profile3,
        R.drawable.profile4,
        R.drawable.profile5,
        R.drawable.profile6,
        R.drawable.profile7,
        R.drawable.profile8,
};

    public FirstTabAdapter(Context context, List<ContactsVO> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_tab_item_view, parent, false);
        Holder holder = new Holder(view);

        int imageRd = (int) (Math.random() * images.length);
        holder.profile.setImageResource(images[imageRd]);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        int itemposition = position;
        holder.name.setText(list.get(itemposition).name);
        holder.number.setText(list.get(itemposition).number);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView number;
        public CircleImageView profile;

        public Holder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            number = (TextView) view.findViewById(R.id.number);
            profile = (CircleImageView) view.findViewById(R.id.circle_iv);
        }
    }
}
