package com.example.vadym.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

//import static com.example.vadym.myapplication.Constants.sharedPreferences;

public class OpenNewWindow extends AppCompatActivity {

    private TextView welcomeText;
    private Button btnExit;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_window);

        welcomeText = (TextView) findViewById(R.id.welcomeText);
        btnExit = (Button) findViewById(R.id.btnExit);

        sharedPreferences = getSharedPreferences(Constants.APP_PREFS, Context.MODE_PRIVATE);
        String user = sharedPreferences.getString(Constants.LOGIN,"");
        welcomeText.setText("Hello " + user);

        btnExit.setOnClickListener(v -> logout());

    }

    private void logout(){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
        edit.apply();

        Intent i = new Intent(this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }


}
