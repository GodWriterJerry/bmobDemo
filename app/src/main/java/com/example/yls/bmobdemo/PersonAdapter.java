package com.example.yls.bmobdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yls on 2017/3/6.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>{
    private List<Person> mList;

    public PersonAdapter(List<Person> list){
        mList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            Person person=mList.get(position);
            holder.tv5.setText(person.getName());
            holder.tv6.setText(person.getAddress());
            holder.tv7.setText(String.valueOf(person.getAge()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv5,tv6,tv7;
        public ViewHolder(View itemView) {
            super(itemView);
            tv5= (TextView) itemView.findViewById(R.id.tv_5);
            tv6= (TextView) itemView.findViewById(R.id.tv_6);
            tv7= (TextView) itemView.findViewById(R.id.tv_7);
        }
    }
}
