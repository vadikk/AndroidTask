package com.example.vadym.myapplication;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText loginText;
    private EditText passwordText;
    private CheckBox check;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkinformation);

        loginText = (EditText) findViewById(R.id.editLoginText);
        passwordText = (EditText) findViewById(R.id.editPasswordText);
        buttonSignUp = (Button) findViewById(R.id.button);

        check = (CheckBox) findViewById(R.id.checkBox);
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    passwordText.setTransformationMethod(null);

                } else {
                    passwordText.setTransformationMethod(new PasswordTransformationMethod());
                }
                // TODO: Дуже розумно з селекшеном. А можеш запам’ятовувати позицію,
                // TODO: на якій користувач був, як ставив курсор на текст?
                passwordText.setSelection(passwordText.getText().length());
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitInformation(view);
            }
        });

        // TODO: Давай ще цікавого додамо.
        // TODO: Додай підтримку клавіатури трохи.
        // TODO: Коли чувак ввів пароль і на клаві натискає галочку, то його має автоматом логінити.

        // TODO: І давай вже новий екран!
        // TODO: Якщо успішно входить чувак, то він переходить на новий екран, на якому написано привітання його по логіну.
        // TODO: Також там має буть кнопка "вийти".
        // TODO: Якщо чувак її не клікає, то заходить автоматом на другий екран.
    }

    public void submitInformation(View view) {
        // TODO: Дивись, в тебе на вхід мають йти аргументи, які мають використовуватися.
        // TODO: Коли ти клікнеш на вьюху, то вона в тебе нікуди не поведе, тобто, її можна видаляти з функції, так і зроби.

        if (loginText.getText().length() == 0 && passwordText.getText().length() == 0) {
            //viewText.setVisibility(View.VISIBLE);
            loginText.setError(getString(R.string.login_error));
            passwordText.setError(getString(R.string.password_error));
        } else if (loginText.getText().length() == 0) {
//            viewText.setVisibility(view.VISIBLE);
            loginText.setError(getString(R.string.login_error));
        } else if (passwordText.getText().length() == 0) {
            //viewText.setVisibility(View.VISIBLE);
            passwordText.setError(getString(R.string.password_error));
        } else {
            //viewText.setVisibility(View.VISIBLE);
            new AlertDialog.Builder(this)
                    .setTitle(R.string.final_title)
                    .setMessage(R.string.final_message)
                    .show();
        }
    }
}
