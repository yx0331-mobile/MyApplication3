package com.example.myapplication3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Task3Activity extends AppCompatActivity {

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task3);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvContent = findViewById(R.id.tvContent);
    }

    // 加载 XML 菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task3_menu, menu);
        return true;
    }

    // 处理菜单点击
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_font_small) {
            tvContent.setTextSize(10);     // 小 = 10号字
            return true;
        } else if (id == R.id.menu_font_medium) {
            tvContent.setTextSize(16);     // 中 = 16号字
            return true;
        } else if (id == R.id.menu_font_large) {
            tvContent.setTextSize(20);     // 大 = 20号字
            return true;
        } else if (id == R.id.menu_color_red) {
            tvContent.setTextColor(Color.RED);
            return true;
        } else if (id == R.id.menu_color_black) {
            tvContent.setTextColor(Color.BLACK);
            return true;
        } else if (id == R.id.menu_normal) {
            Toast.makeText(this, "你点击了普通菜单项", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
