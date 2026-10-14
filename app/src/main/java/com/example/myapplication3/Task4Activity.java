package com.example.myapplication3;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Task4Activity extends AppCompatActivity {

    private ArrayList<HashMap<String, Object>> data;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task4);

        ListView listView = findViewById(R.id.listView);

        // 1. 列表数据：One ~ Five + 应用图标
        data = new ArrayList<>();
        String[] items = {"One", "Two", "Three", "Four", "Five"};
        for (String s : items) {
            HashMap<String, Object> item = new HashMap<>();
            item.put("text", s);
            item.put("icon", R.mipmap.ic_launcher);
            data.add(item);
        }

        adapter = new SimpleAdapter(
                this,
                data,
                R.layout.list_item_action_mode,
                new String[]{"icon", "text"},
                new int[]{R.id.itemIcon, R.id.itemText}
        );

        listView.setAdapter(adapter);

        // 2. 开启多选模态：长按进入 ActionMode
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                mode.setTitle(listView.getCheckedItemCount() + " selected");
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.task4_action_mode, menu);
                mode.setTitle("1 selected");
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if (item.getItemId() == R.id.menu_delete) {
                    deleteSelectedItems(listView);
                    mode.finish();
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // ActionMode 关闭时清空选中状态
                listView.clearChoices();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void deleteSelectedItems(ListView listView) {
        android.util.SparseBooleanArray checked = listView.getCheckedItemPositions();
        ArrayList<Integer> positions = new ArrayList<>();

        for (int i = 0; i < checked.size(); i++) {
            if (checked.valueAt(i)) {
                positions.add(checked.keyAt(i));
            }
        }

        // 从后往前删，避免下标错位
        Collections.sort(positions, Collections.reverseOrder());
        for (int pos : positions) {
            data.remove(pos);
        }

        adapter.notifyDataSetChanged();
        listView.clearChoices();
        Toast.makeText(this, "已删除 " + positions.size() + " 项", Toast.LENGTH_SHORT).show();
    }
}
