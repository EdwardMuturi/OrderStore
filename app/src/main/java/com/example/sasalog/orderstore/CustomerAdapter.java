package com.example.sasalog.orderstore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sasalog on 9/14/17.
 */

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.NumberViewHolder> {
    private static final String TAG= CustomerAdapter.class.getSimpleName();
    private int customerItems;

    public CustomerAdapter(int numberOfCustomers){
        customerItems= numberOfCustomers;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context= viewGroup.getContext();
        int layoutIdForListItem= R.layout.customer_list_item;
        LayoutInflater inflater= LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately= false;

        View view= inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder= new NumberViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position){
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }
    @Override
    public int getItemCount(){
        return customerItems;
    }

    class NumberViewHolder extends RecyclerView.ViewHolder {
        TextView listItemNumberView;
        public NumberViewHolder(View itemView){
            super(itemView);

            listItemNumberView= (TextView) itemView.findViewById(R.id.customer_item_number);

        }
        void bind(int listIndex){
            listItemNumberView.setText(String.valueOf(listIndex));
        }
    }
}
