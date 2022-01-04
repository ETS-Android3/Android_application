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
    private List<String> nameList = new ArrayList<>();
    private List<String> numberList = new ArrayList<>();
    private List<String> friendList = new ArrayList<>();
    private String myPhoneNum;
    private String friend;
    private int index;

    public ReceiveRequestAdapter(Context context, List<String> nameList, List<String> numberList,String myPhoneNum, String friend) {
        this.context = context;
        this.nameList = nameList;
        this.numberList = numberList;
        this.myPhoneNum=myPhoneNum;
        this.friend=friend;
    }
    public ReceiveRequestAdapter(int index, Context context, List<String> nameList, List<String> numberList,String myPhoneNum, List<String> friendList) {

        this.index = index;
        this.context = context;
        this.nameList = nameList;
        this.numberList = numberList;
        this.myPhoneNum = myPhoneNum;
        this.friendList=friendList;
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
                SDB.acceptRequest(index, myPhoneNum, friendList.get(itemposition));
                SDB.acceptRequest(index, friendList.get(itemposition), myPhoneNum);
                System.out.println(myPhoneNum);
                System.out.println(friend);
                System.out.println(itemposition);
                //System.out.println(nameList.get(itemposition));
                //System.out.println(numberList.get(itemposition));
                friendList.remove(itemposition);
                nameList.remove(itemposition);
                numberList.remove(itemposition);
                notifyDataSetChanged();
            }
        });

        //System.out.println(nameList);
        //System.out.println(numberList);
        holder.name.setText(nameList.get(itemposition));
        holder.number.setText(numberList.get(itemposition));
    }



    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        //public TextView name;
        public TextView number;
        public Button button;
        public TextView name;

        public Holder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.receive_request_name);
            number = (TextView) view.findViewById(R.id.receive_request_number);
            button = (Button) view.findViewById(R.id.accept_request_button);
        }
    }
}