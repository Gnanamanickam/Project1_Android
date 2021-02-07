package com.gnani.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

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
            } else if (enteredName.split(" ").length > 1 && checkName(enteredName)) {
                setResult(RESULT_OK, intent);
            } else {
                setResult(RESULT_CANCELED, intent);
            }
            finish();
        });
    }

    public static boolean checkName(String name) {
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