package com.myself.app.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myself.app.R;
import com.myself.app.view.adapter.RecyclerGridAdapter;
import com.myself.app.view.adapter.RecyclerListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Recycler View
 * Created by Administrator on 2018/1/11 0011.
 */

public class RecyclerViewFragment extends Fragment {
    private static final String TAG = "RecyclerViewFragment";

    private RecyclerView recyclerview1, recyclerview2;
    private List<String> listData, gridData;

    public RecyclerViewFragment() {
        listData = new ArrayList<>();
        gridData = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        recyclerview1 = (RecyclerView) view.findViewById(R.id.recyclerview1);
        recyclerview2 = (RecyclerView) view.findViewById(R.id.recyclerview2);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();

        RecyclerListAdapter recyclerListAdapter = new RecyclerListAdapter(getActivity(), listData);
        recyclerview1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
        recyclerview1.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerview1.setAdapter(recyclerListAdapter);

        RecyclerGridAdapter recyclerGridAdapter = new RecyclerGridAdapter(getActivity(), gridData);
        recyclerview2.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.VERTICAL));
        recyclerview2.setAdapter(recyclerGridAdapter);
    }

    private void initData() {
        for (int i = 0; i < 40; i++) {
            listData.add(String.valueOf(i).concat("项"));
            gridData.add(String.valueOf(i).concat("项"));
        }
    }
}
