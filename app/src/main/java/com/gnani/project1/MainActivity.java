package com.gnani.project1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;

    String name;
    private int requestCode;
    private int resultCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        Intent intent = new Intent(this, NameCheckActivity.class);
        button1.setOnClickListener(v -> startActivityForResult(intent, 1));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        System.out.println(requestCode + " " + resultCode + " " + data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            button2 = findViewById(R.id.button2);
            Log.i("OnActivityResult", "success");
            if (resultCode == Activity.RESULT_OK) {
//                assert data != null;
                name = data.getStringExtra("name");
                System.out.println("name in activity" + name);
                button2.setOnClickListener(view -> {
                    Intent openContacts = new Intent(ContactsContract.Intents.Insert.ACTION).setType(ContactsContract.RawContacts.CONTENT_TYPE);
                    openContacts.putExtra(ContactsContract.Intents.Insert.NAME, name);
                    startActivity(openContacts);
                    name = " ";

                });
            } else {
                button2.setOnClickListener(view -> Toast.makeText(MainActivity.this, "Incorrect name entered", Toast.LENGTH_LONG).show());
            }
            requestCode = 0;
            data = null;
            resultCode = RESULT_CANCELED;

        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("requestCode", requestCode);
        savedInstanceState.putInt("resultCode",resultCode);
        savedInstanceState.putString("name", name);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        requestCode = savedInstanceState.getInt("requestCode");
        resultCode = savedInstanceState.getInt("resultCode");
        name = savedInstanceState.getString("name");
    }
}
