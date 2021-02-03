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
        name.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId==KeyEvent.KEYCODE_ENTER || actionId== EditorInfo.IME_ACTION_DONE) {
                    if(!name.getText().toString().isEmpty()){

                        Intent validName = new Intent();
                        validName.putExtra("name",name.getText().toString());
                        setResult(Activity.RESULT_OK,validName);
                        finish();
                        return true;
                    }
                    else {

                        Intent InvalidName = new Intent();
                        InvalidName.putExtra("name",name.getText().toString());
                        setResult(Activity.RESULT_CANCELED, InvalidName);
                        finish();
                        return true;
                    }
                }
                return false;
            }
        });
    }


}