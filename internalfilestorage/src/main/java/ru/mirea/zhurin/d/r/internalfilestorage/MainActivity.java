package ru.mirea.zhurin.d.r.internalfilestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private static final String SHARED_PREFS_FILE = "Russ";
    private static final String DATE_KEY = "date";

    private EditText dateEditText;
    private EditText descriptionEditText;
    private TextView lastDateTextView;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateEditText = findViewById(R.id.date_edit_text);
        descriptionEditText = findViewById(R.id.description_edit_text);
        lastDateTextView = findViewById(R.id.last_date_text_view);

        sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);

        String lastDate = sharedPreferences.getString(DATE_KEY, "");
        lastDateTextView.setText(lastDate);
    }

    public void onSaveButtonClick(View view) {
        String date = dateEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        writeToFile(date, description);

        dateEditText.setText("");
        descriptionEditText.setText("");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DATE_KEY, date);
        editor.apply();
    }

    private void writeToFile(String date, String description) {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.date_info);

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append('\n');
            }
            String fileContent = builder.toString();
            String newEntry = date + " - " + description + "\n";
            fileContent = newEntry + fileContent;
            int fileResourceId = R.raw.date_info;
            writeToFile(getResources().openRawResource(fileResourceId), fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeToFile(InputStream inputStream, String fileContent) {
        try {
            int fileResourceId = R.raw.date_info;
            String fileName = getResources().getResourceEntryName(fileResourceId);
            openFileOutput(fileName, Context.MODE_PRIVATE).close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                writeLineToFile(fileName, line);
            }
            writeLineToFile(fileName, fileContent);

            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeLineToFile(String fileName, String line) {
        try {
            openFileOutput(fileName, Context.MODE_APPEND).close();

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(fileName, Context.MODE_APPEND));
            outputStreamWriter.write(line + "\n");
            outputStreamWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}