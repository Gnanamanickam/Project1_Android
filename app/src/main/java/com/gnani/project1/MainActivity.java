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

    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        Intent intent = new Intent(this, NameCheckActivity.class);
        button.setOnClickListener(v -> startActivityForResult(intent,1));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1) {
            button2 = findViewById(R.id.button2);
            Log.i("OnActivityResult", "success");
            if (resultCode == Activity.RESULT_OK) {
                assert data != null;
                String name = data.getStringExtra("name");
                button2.setOnClickListener(view -> Toast.makeText(MainActivity.this, "Incorrect name entered", Toast.LENGTH_LONG).show());
                button2.setOnClickListener(view -> {
                        String ContactName = data.getStringExtra("returnName");
                        Intent openContacts = new Intent(ContactsContract.Intents.Insert.ACTION).setType(ContactsContract.RawContacts.CONTENT_TYPE);
                        openContacts.putExtra(ContactsContract.Intents.Insert.NAME, ContactName);
                        startActivity(openContacts);

                });
            } else if (resultCode == Activity.RESULT_CANCELED && data == null) {
                button2.setOnClickListener(view -> Toast.makeText(MainActivity.this, "Incorrect name entered", Toast.LENGTH_LONG).show());
            }
        }

        }
    }
