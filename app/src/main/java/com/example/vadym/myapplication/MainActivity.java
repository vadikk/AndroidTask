package com.example.vadym.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText loginText;
    private EditText passwordText;
    private CheckBox check;
    private Button buttonSignUp;

    private boolean mAutoSignIn;
    private SharedPreferences sharedPreferences;


    // TODO: Розкинь по функціях свій функціонал, який я написав.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkinformation);

        init();
        initOnClickListeners();
        initViewsListeners();
    }


    private void init(){

        sharedPreferences = getSharedPreferences(Constants.APP_PREFS, Context.MODE_PRIVATE);
        mAutoSignIn = sharedPreferences.getBoolean(Constants.IS_SIGN_IN, false);

        if (mAutoSignIn) {
            setInitSettings();
        }

        loginText = (EditText) findViewById(R.id.editLoginText);
        passwordText = (EditText) findViewById(R.id.editPasswordText);
        buttonSignUp = (Button) findViewById(R.id.button);
        check = (CheckBox) findViewById(R.id.checkBox);

    }

    private void initOnClickListeners(){
        buttonSignUp.setOnClickListener(v -> submitInformation());
    }
    private void initViewsListeners(){
        check.setOnCheckedChangeListener((buttonView, isChecked) -> {

            int cursorPositionStart = passwordText.getSelectionStart();

            if (isChecked) {
                passwordText.setTransformationMethod(null);

            } else {
                passwordText.setTransformationMethod(new PasswordTransformationMethod());

            }

            passwordText.setSelection(cursorPositionStart);
        });

        passwordText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            submitInformation();
                            return true;
                        default:
                            return false;
                    }
                }
                return false;
            }
        });
    }

    public void submitInformation() {
        // TODO: Поки це не чіпай, але хай буде.
        // TODO: Коли стою на логіні, є введений якийсь текст і пустий пароль - то повідомлення помилки не показується.
        // TODO: Треба ховати помилки, якщо продовжуємо писати текст, тому що овни не зникаєть, поки не натиснеш ентер.
        if (loginText.getText().length() == 0 && passwordText.getText().length() == 0) {

            loginText.setError(getString(R.string.login_error));
            passwordText.setError(getString(R.string.password_error));


        } else if (loginText.getText().toString().trim().length() <= 0) {
            loginText.requestFocus();
            loginText.setError(getString(R.string.login_error));
        } else if (passwordText.getText().length() == 0) {
            passwordText.requestFocus();
            passwordText.setError(getString(R.string.password_error));
        } else {

            goToNextActivity();

            /*new AlertDialog.Builder(this)
                    .setTitle(R.string.final_title)
                    .setMessage(R.string.final_message)
                    .show();*/
        }
    }

    private void setInitSettings(){
        Intent intent = new Intent(this, OpenNewWindow.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void setSettingsToSharedPreferencesEditor() {
        String log = loginText.getText().toString();
        String pas = passwordText.getText().toString();

        SharedPreferences.Editor edit = sharedPreferences.edit();

        edit.putString(Constants.LOGIN, log);
        edit.putString(Constants.PASSWORD, pas);
        edit.putBoolean(Constants.IS_SIGN_IN, true);
        edit.apply();
    }

    private void goToNextActivity() {
        setSettingsToSharedPreferencesEditor();
        setInitSettings();
    }

}


