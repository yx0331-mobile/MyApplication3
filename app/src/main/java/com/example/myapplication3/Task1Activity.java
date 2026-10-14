package com.example.myapplication3;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class Task1Activity extends AppCompatActivity {

    private static final String CHANNEL_ID = "task1_channel";
    private static final int REQ_NOTIFY = 100;

    // 列表数据：名称 + 对应的图片资源
    private final String[] names = {"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
    private final int[] images = {
            R.drawable.lion, R.drawable.tiger, R.drawable.monkey,
            R.drawable.dog, R.drawable.cat, R.drawable.elephant
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);

        createChannel();
        requestNotifyPermission();

        // 1. 组装数据：每行是一个 Map，key 要和下面 from 数组里的字符串一致
        ArrayList<HashMap<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            HashMap<String, Object> item = new HashMap<>();
            item.put("name", names[i]);
            item.put("image", images[i]);
            data.add(item);
        }

        // 2. SimpleAdapter：from = Map 的 key，to = list_item.xml 里的控件 id
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                R.layout.list_item,
                new String[]{"name", "image"},
                new int[]{R.id.itemName, R.id.itemImage}
        );

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // 3. 点击列表项：Toast 显示名称 + 发送通知
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String name = names[position];
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            sendNotification(name);
        });
    }

    // Android 8.0+ 必须先建通知渠道，否则通知发不出去
    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, "列表点击通知", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager nm = getSystemService(NotificationManager.class);
            if (nm != null) {
                nm.createNotificationChannel(channel);
            }
        }
    }

    // Android 13+ 发通知需要运行时权限
    private void requestNotifyPermission() {
        if (Build.VERSION.SDK_INT >= 33) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQ_NOTIFY);
            }
        }
    }

    private void sendNotification(String name) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)          // 图标 = 应用图标
                .setContentTitle(name)                       // 标题 = 列表项内容
                .setContentText("你点击了列表项：" + name)      // 内容自拟
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (nm != null) {
            nm.notify(1, builder.build());
        }
    }
}
