package com.example.vadym.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity  {

    private  EditText loginText;
    private EditText passwordText;
    private CheckBox check;
    private Button buttonSignUp;

    private boolean mAutoSignIn;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkinformation);

        sharedPreferences = getSharedPreferences(Constants.AppPrefs, Context.MODE_PRIVATE);

        loginText = (EditText) findViewById(R.id.editLoginText);
        passwordText = (EditText) findViewById(R.id.editPasswordText);
        buttonSignUp = (Button) findViewById(R.id.button);
        check = (CheckBox) findViewById(R.id.checkBox);

        mAutoSignIn = sharedPreferences.getBoolean(Constants.IS_Sign_In,false);

        check.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                passwordText.setTransformationMethod(null);

            } else {
                passwordText.setTransformationMethod(new PasswordTransformationMethod());

            }

            //Устанавливает курсор в конце слова
            //passwordText.setSelection(passwordText.getText().length());
        });

        buttonSignUp.setOnClickListener(v -> submitInformation());

        passwordText.setOnTouchListener((v, event) -> {

            // TODO: не працює запам’ятовування позиції курсора, коли знімаємо/ставимо галочку, весь час курсор падає на початок слова.
            //int cursorPositionStart = passwordText.getSelectionStart();
            int cursorPositionEnd = passwordText.getSelectionEnd();

            //CharSequence enterText = passwordText.getText().toString();
            //CharSequence cursorEnd = enterText.subSequence(cursorPosition,enterText.length());

            return false;
        });



        passwordText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction()==KeyEvent.ACTION_DOWN){
                    switch (keyCode){
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            submitInformation();
                            return  true;
                        default:
                            return false;
                    }
                }
                return  false;
            }
        });


        if(mAutoSignIn){
            Intent intent = new Intent(this,OpenNewWindow.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

    }

    public void submitInformation() {
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

            goNextActivity();

            /*new AlertDialog.Builder(this)
                    .setTitle(R.string.final_title)
                    .setMessage(R.string.final_message)
                    .show();*/
        }
    }

    private void setSettingsToSharedPreferencesEditor(){

        String log = loginText.getText().toString();
        String pas = passwordText.getText().toString();

        SharedPreferences.Editor edit = sharedPreferences.edit();

        edit.putString(Constants.Login, log);
        edit.putString(Constants.Password,pas);
        edit.putBoolean(Constants.IS_Sign_In,true);
        edit.apply();

    }

    private void goNextActivity(){
        setSettingsToSharedPreferencesEditor();

        Intent intent = new Intent(this,OpenNewWindow.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }

}


