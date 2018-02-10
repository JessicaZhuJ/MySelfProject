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

public class RecyclerGridAdapter extends RecyclerView.Adapter<RecyclerGridAdapter.GridItemViewHolder> {
    private Context context;
    private List<String> data;

    public RecyclerGridAdapter(@NonNull Context context, @NonNull List<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public GridItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, android.R.layout.simple_list_item_1, null);
        return new GridItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GridItemViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class GridItemViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public GridItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
