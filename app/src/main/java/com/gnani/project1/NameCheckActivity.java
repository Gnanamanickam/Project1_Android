package com.gnani.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NameCheckActivity extends AppCompatActivity {

    private static String ReadText = "ReadText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_check);

//        Intent intent = getIntent();
//        String name = intent.getStringExtra(NameCheckActivity.ReadText);
//        TextView textView = findViewById(R.id.textView);
//        textView.setText(name);
    }

    public void sendName(View view) {
        Intent intent = new Intent(this, NameCheckActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String name = editText.getText().toString();
        intent.putExtra(ReadText, name);
        startActivity(intent);
    }

}