package com.myself.app.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.myself.app.R;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Administrator on 2017/10/31 0031.
 */

public class RefreshActivity extends Activity {
    private List<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltorefresh);

        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("hello " + i);
        }
        SwipeMenuListView refresh_listview = (SwipeMenuListView) this.findViewById(R.id.refresh_listview);
        refresh_listview.setAdapter(adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(15);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                deleteItem.setTitle("Delete");
                deleteItem.setTitleSize(15);
                deleteItem.setTitleColor(Color.WHITE);
                // set a icon
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        refresh_listview.setMenuCreator(creator);
        refresh_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(RefreshActivity.this, "haha", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        // Right
//        refresh_listview.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);

        // Left
//        refresh_listview.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

        refresh_listview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int i, SwipeMenu swipeMenu, int index) {
                Toast.makeText(RefreshActivity.this, swipeMenu.getMenuItem(index).getTitle(), Toast.LENGTH_SHORT).show();
                return false;// false : close the menu; true : not close the menu
            }
        });
        refresh_listview.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });
        PtrClassicFrameLayout fragment_rotate_header_with_text_view_frame = (PtrClassicFrameLayout) this.findViewById(R.id.fragment_rotate_header_with_text_view_frame);
        fragment_rotate_header_with_text_view_frame.setLastUpdateTimeRelateObject(this);

        // TODO 使用此接口可以进行上拉加载，下拉刷新
        fragment_rotate_header_with_text_view_frame.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(final PtrFrameLayout ptrFrameLayout) {
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            list.add("load more " + i);
                        }
                        adapter.notifyDataSetChanged();
                        ptrFrameLayout.refreshComplete();
                    }
                }, 1500);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout ptrFrameLayout) {
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            list.add("good bye " + i);
                        }
                        adapter.notifyDataSetChanged();
                        ptrFrameLayout.refreshComplete();
                    }
                }, 1500);
            }

        });
        // TODO 此接口只有下拉刷新功能
//        fragment_rotate_header_with_text_view_frame.setPtrHandler(new PtrDefaultHandler() {
//            @Override
//            public void onRefreshBegin(final PtrFrameLayout ptrFrameLayout) {
//                ptrFrameLayout.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        for (int i = 0; i < 10; i++) {
//                            list.add("good bye " + i);
//                        }
//                        adapter.notifyDataSetChanged();
//                        ptrFrameLayout.refreshComplete();
//                    }
//                }, 1000);
//            }
//
//        });
        // 所有侧滑控件冲突解决方法
        fragment_rotate_header_with_text_view_frame.disableWhenHorizontalMove(true);
//        fragment_rotate_header_with_text_view_frame.autoRefresh(true);
//        fragment_rotate_header_with_text_view_frame.autoRefresh();
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
