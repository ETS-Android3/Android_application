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

public class ReceiveRequestAdapter extends RecyclerView.Adapter<ReceiveRequestAdapter.Holder> {

    private Context context;
    private List<String> list = new ArrayList<>();
    private String myPhoneNum;
    private String friend;
    private int index;

    public ReceiveRequestAdapter(Context context, List<String> list, String myPhoneNum, String friend) {
        this.context = context;
        this.list = list;
        this.myPhoneNum=myPhoneNum;
        this.friend=friend;
    }
    public ReceiveRequestAdapter(int index, Context context, List<String> list, String myPhoneNum, String friend) {

        this.index = index;
        this.context = context;
        this.list = list;
        this.myPhoneNum=myPhoneNum;
        this.friend=friend;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receive_request_item_view, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        int itemposition = position;
        holder.button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FriendRequestDBHelper SDB = new FriendRequestDBHelper(context.getApplicationContext());
                SDB.acceptRequest(index, myPhoneNum, friend);
                SDB.acceptRequest(index, friend, myPhoneNum);
                list.remove(itemposition);
                notifyDataSetChanged();
            }
        });


        //holder.name.setText(list.get(itemposition).name);
        holder.number.setText(list.get(itemposition));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        //public TextView name;
        public TextView number;
        public Button button;

        public Holder(View view){
            super(view);
            //name = (TextView) view.findViewById(R.id.name);
            number = (TextView) view.findViewById(R.id.receive_request_number);
            button = (Button) view.findViewById(R.id.accept_request_button);
        }
    }
}