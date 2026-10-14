package com.example.myapplication3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTask1 = findViewById(R.id.btnTask1);
        btnTask1.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, Task1Activity.class)));

        Button btnTask2 = findViewById(R.id.btnTask2);
        btnTask2.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, Task2Activity.class)));

        Button btnTask3 = findViewById(R.id.btnTask3);
        btnTask3.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, Task3Activity.class)));

        Button btnTask4 = findViewById(R.id.btnTask4);
        btnTask4.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, Task4Activity.class)));

        Button btnTask5 = findViewById(R.id.btnTask5);
        btnTask5.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, Task5Activity.class)));
    }
}
