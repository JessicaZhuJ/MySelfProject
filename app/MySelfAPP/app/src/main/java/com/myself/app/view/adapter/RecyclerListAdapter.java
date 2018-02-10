package com.myself.app.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ListItemViewHolder> {
    private static final String TAG = "RecyclerListAdapter";
    private Context context;
    private List<String> data;

    public RecyclerListAdapter(@NonNull Context context, @NonNull List<String> data) {
        this.context = context;
        this.data = data;

    }


    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, android.R.layout.simple_list_item_2, null);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ListItemViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text2);
        }
    }
}
