package ru.mirea.zhurin.d.r.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText filenameEditText;
    private EditText quoteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filenameEditText = findViewById(R.id.filename_edit_text);
        quoteEditText = findViewById(R.id.quote_edit_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }

    }

    public void onSaveButtonClick(View view) {
        String filename = filenameEditText.getText().toString();
        String quote = quoteEditText.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("Rabot", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("filename", filename);
        editor.putString("quote", quote);
        editor.apply();

        filenameEditText.setText("");
        quoteEditText.setText("");

        Toast.makeText(this, "Данные успешно сохранены", Toast.LENGTH_SHORT).show();
    }

    public void onLoadButtonClick(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("Rabot", Context.MODE_PRIVATE);
        String filename = sharedPreferences.getString("filename", "");
        String quote = sharedPreferences.getString("quote", "");

        filenameEditText.setText(filename);
        quoteEditText.setText(quote);

        Toast.makeText(this, "Данные успешно загружены", Toast.LENGTH_SHORT).show();
    }
}