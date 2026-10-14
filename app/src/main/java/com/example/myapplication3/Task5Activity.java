package com.example.myapplication3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Task5Activity extends AppCompatActivity {

    private final List<Animal> animals = new ArrayList<>();
    private AnimalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task5);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Button btnAdd = findViewById(R.id.btnAdd);

        // 1. 初始数据：6 只动物
        animals.add(new Animal("Lion", R.drawable.lion));
        animals.add(new Animal("Tiger", R.drawable.tiger));
        animals.add(new Animal("Monkey", R.drawable.monkey));
        animals.add(new Animal("Dog", R.drawable.dog));
        animals.add(new Animal("Cat", R.drawable.cat));
        animals.add(new Animal("Elephant", R.drawable.elephant));

        // 2. Adapter：点击时 Toast 显示名称
        adapter = new AnimalAdapter(animals, animal ->
                Toast.makeText(this, animal.getName(), Toast.LENGTH_SHORT).show());

        // 3. 设置布局管理器 + 适配器（少了 LayoutManager 列表不会显示）
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // 4. 新增一条记录，列表立即更新
        btnAdd.setOnClickListener(v -> {
            animals.add(new Animal("New Animal " + (animals.size() + 1), R.drawable.lion));
            adapter.notifyItemInserted(animals.size() - 1);   // 局部刷新，不会错位
            recyclerView.scrollToPosition(animals.size() - 1);
            Toast.makeText(this, "已添加，当前共 " + animals.size() + " 项", Toast.LENGTH_SHORT).show();
        });
    }
}
