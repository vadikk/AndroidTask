package com.example.vadym.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OpenNewWindow extends AppCompatActivity {

    //public MainActivity main = new MainActivity();

    private TextView welcomeText;
    private Button btnExit;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_window);

        welcomeText = (TextView) findViewById(R.id.welcomeText);
        btnExit = (Button) findViewById(R.id.btnExit);

        // TODO: Якщо юзаєш якісь речі повторно - винеси їх, особливо, якщо це змінні.
        sharedPreferences = getSharedPreferences(MainActivity.AppPrefs, Context.MODE_PRIVATE);
        String user = sharedPreferences.getString(MainActivity.Login,"");
        welcomeText.setText("Hello " + user);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        // TODO: Коли зайшов в додаток вже типу наступного разу, то натискаю назад і знову сторінка логінки.
    }

    private void logout(){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
        edit.apply();

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        // TODO: Коли найтиснув на вихід і настинув назад - показує знову цей екран.
    }


}