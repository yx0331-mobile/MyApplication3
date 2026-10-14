package com.example.myapplication3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Task2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);

        Button btnShow = findViewById(R.id.btnShowDialog);
        btnShow.setOnClickListener(v -> showLoginDialog());
    }

    private void showLoginDialog() {
        // 1. 把自定义布局解析成 View 对象
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_login, null);

        // 2. AlertDialog.Builder 调用 setView() 把布局装进对话框
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();

        // 3. 注意：控件从 dialog 的 view 里找，不是 findViewById（那是 Activity 的）
        EditText etUser = view.findViewById(R.id.etUsername);
        EditText etPass = view.findViewById(R.id.etPassword);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnSign = view.findViewById(R.id.btnSign);

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnSign.setOnClickListener(v -> {
            String user = etUser.getText().toString().trim();
            String pass = etPass.getText().toString().trim();
            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "登录成功，欢迎 " + user, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
