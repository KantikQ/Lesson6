package ru.mirea.zhurin.d.r.securesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("Poet", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("poet_name", "Леонардо Да Винчи");
        editor.putInt("poet_photo_res_id", R.drawable.leo);
        editor.apply();

        String poetName = sharedPreferences.getString("poet_name", "");
        int poetPhotoResId = sharedPreferences.getInt("poet_photo_res_id", R.drawable.leo);

        ImageView poetPhotoImageView = findViewById(R.id.poet_photo);
        Bitmap poetPhotoBitmap = BitmapFactory.decodeResource(getResources(), poetPhotoResId);
        poetPhotoImageView.setImageBitmap(poetPhotoBitmap);

        TextView poetNameTextView = findViewById(R.id.poet_name_textview);
        poetNameTextView.setText(poetName);


    }
}