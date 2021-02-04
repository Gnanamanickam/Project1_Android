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
        String enteredName = name.getText().toString();
        Intent returnName = new Intent(this, MainActivity.class);
        returnName.putExtra("name",name.getText().toString());
        name.setOnClickListener(v -> {
            if(enteredName.isEmpty()) {
                setResult(Activity.RESULT_CANCELED, returnName);
            }
            if(enteredName.split(" ").length > 1 && validator(enteredName)) {
                    setResult(Activity.RESULT_OK, returnName);
                }
            else {
                setResult(Activity.RESULT_CANCELED, returnName);
            }
            finish();
        });
    }

    public static boolean validator(String name) {
        boolean value = true;
        String split_name[] = name.split(" ");
        for(int i=0;i < name.length(); i++) {
            value = split_name[i].matches("([a-zA-z]{0,23})");
            if(value == false)
                return value;
        }
        return value;
    }


}