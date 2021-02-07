package com.gnani.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class NameCheckActivity extends AppCompatActivity {

    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_check);
        name = (EditText) findViewById(R.id.enterName);

        Intent intent = new Intent(this, MainActivity.class);
        name.setOnClickListener(v -> {
            String enteredName = name.getText().toString();
            intent.putExtra("name", enteredName);
            if (enteredName.isEmpty()) {
                setResult(RESULT_CANCELED, intent);
                System.out.println("if part");
            } else if (enteredName.split(" ").length > 1 && validator(enteredName)) {
                setResult(RESULT_OK, intent);
                System.out.println("else if part");
            } else {
                setResult(RESULT_CANCELED, intent);
                System.out.println("else part");
            }
            finish();
        });
    }

    public static boolean validator(String name) {
        boolean value = true;
        System.out.println(name);
        String split_name[] = name.split(" ");
        for (int i = 0; i < split_name.length; i++) {
            value = split_name[i].matches("([a-zA-z]{0,23})");
            if (value == false)
                return value;
        }
        return value;
    }


}