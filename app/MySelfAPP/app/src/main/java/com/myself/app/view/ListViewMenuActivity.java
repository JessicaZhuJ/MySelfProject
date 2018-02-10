package com.myself.app.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.myself.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/2 0002.
 */

public class ListViewMenuActivity extends Activity {
    private SwipeMenuListView swipeMenuListView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_menu);
        final List<String>list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("hello " + i);
        }
        swipeMenuListView = (SwipeMenuListView) this.findViewById(R.id.swipeMenuListView);
        swipeMenuListView.setAdapter(adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
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
                openItem.setTitleSize(14);
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
                deleteItem.setTitleSize(14);
                deleteItem.setTitle("Delete");
                deleteItem.setTitleColor(Color.WHITE);
                // set a icon
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        swipeMenuListView.setMenuCreator(creator);
        // Right
//        swipeMenuListView.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);

        // Left
//        refresh_listview.setSwipeDirection(SwipeMenuListView.DIRECTIO N_LEFT);

        swipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int i, SwipeMenu swipeMenu, int i1) {
                Toast.makeText(ListViewMenuActivity.this,swipeMenu.getMenuItem(i1).getTitle(),Toast.LENGTH_SHORT).show();
                if (TextUtils.equals("Delete",swipeMenu.getMenuItem(i1).getTitle())){
                    list.remove(i);
                    adapter.notifyDataSetChanged();
                }else{
                    return false;// false : close the menu; true : not close the menu
                }
                // true的时候 删除 可以收回，false不能收回
                return true;
            }
        });
        swipeMenuListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
