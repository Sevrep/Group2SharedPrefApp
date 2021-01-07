package com.example.group2sharedprefapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPref;

    private EditText et1, et2, et3;

    private final String NAME = "nameKey";
    private final String PASSWORD = "passKey";
    private final String EMAIL = "emailKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String MY_PREFERENCES = "MyPrefs";
        sharedPref = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);

        Button bt = findViewById(R.id.bt);
        bt.setOnClickListener(v -> {
            savePreferences(et1.getText().toString().trim(), et2.getText().toString().trim(), et3.getText().toString().trim());
            customToast(sharedPref.getString(NAME, "No name found."), sharedPref.getString(PASSWORD, "No password found."), sharedPref.getString(EMAIL, "No email found."));
            stringBuffer(sharedPref.getString(NAME, "No name found."), sharedPref.getString(PASSWORD, "No password found."), sharedPref.getString(EMAIL, "No email found."));
        });
    }

    private void savePreferences(String n, String pw, String e) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(NAME, n);
        editor.putString(PASSWORD, pw);
        editor.putString(EMAIL, e);
        editor.apply();
    }

    private void customToast(String n, String pw, String e) {
        Toast.makeText(MainActivity.this, "Name: " + n + "\n" + "Password: " + pw + "\n" + "Email: " + e, Toast.LENGTH_SHORT).show();
        clearEntries();
    }

    private void stringBuffer(String n, String pw, String e) {
        String buffer = "Name: " + n + "\n" +
                "Password: " + pw + "\n" +
                "Email: " + e + "\n\n";
        showMessage(buffer);
    }

    private void clearEntries() {
        et1.setText("");
        et2.setText("");
        et3.setText("");
    }

    private void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Shared Preferences");
        builder.setMessage(message);
        builder.show();
    }
}