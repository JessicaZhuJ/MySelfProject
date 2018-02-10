package com.myself.app.view.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.refreshview.CustomRefreshView;
import com.myself.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom Refresh View
 * 上拉加载下拉刷新
 * Created by Administrator on 2018/1/10 0010.
 */

public class CustomRefreshFragment extends Fragment {
    private static final String TAG = "CustomRefreshFragment";

    private CustomRefreshView custom_refresh_view;
    private List<String> data;
    private RecycleViewAdapter adapter;

    public CustomRefreshFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom_refresh, container, false);
        custom_refresh_view = (CustomRefreshView) view.findViewById(R.id.custom_refresh_view);
        data = new ArrayList<>();

        initData();
        return view;
    }

    private void initData() {
        int position = data.size();
        for (int i = position; i < position + 10; i++) {
            data.add(String.valueOf(i + 1));
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new RecycleViewAdapter();
        custom_refresh_view.setAdapter(adapter);
        custom_refresh_view.setOnLoadListener(new CustomRefreshView.OnLoadListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        adapter.notifyDataSetChanged();
                        //下拉刷新，添加你刷新后的逻辑

                        //加载完成时，隐藏控件下拉刷新的状态
                        custom_refresh_view.complete();
                    }
                }, 1500);
            }

            @Override
            public void onLoadMore() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        adapter.notifyDataSetChanged();
//                        custom_refresh_view.onNoMore();
                        //下拉刷新，添加你刷新后的逻辑

                        //加载完成时，隐藏控件下拉刷新的状态
                        custom_refresh_view.complete();
                    }
                }, 1500);
            }
        });
        custom_refresh_view.setRefreshing(true);
        custom_refresh_view.setEmptyView("没有更多数据");
    }


    private class RecycleViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        public RecycleViewAdapter() {

        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(getActivity(), android.R.layout.simple_list_item_1, null);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            holder.textView.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ItemViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(android.R.id.text1);
        }
    }
}
