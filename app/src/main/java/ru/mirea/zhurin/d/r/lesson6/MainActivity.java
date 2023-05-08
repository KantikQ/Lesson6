package ru.mirea.zhurin.d.r.lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText groupNumberEditText;
    private EditText listNumberEditText;
    private EditText favoriteMovieEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupNumberEditText = findViewById(R.id.group_number_edit_text);
        listNumberEditText = findViewById(R.id.list_number_edit_text);
        favoriteMovieEditText = findViewById(R.id.favorite_movie_edit_text);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("group_number", groupNumberEditText.getText().toString());
                editor.putString("list_number", listNumberEditText.getText().toString());
                editor.putString("favorite_movie", favoriteMovieEditText.getText().toString());
                editor.apply();
            }
        });

        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String groupNumber = preferences.getString("group_number", "");
        String listNumber = preferences.getString("list_number", "");
        String favoriteMovie = preferences.getString("favorite_movie", "");

        groupNumberEditText.setText(groupNumber);
        listNumberEditText.setText(listNumber);
        favoriteMovieEditText.setText(favoriteMovie);
    }
}