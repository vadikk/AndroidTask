package com.example.vadym.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    public static EditText loginText;
    private EditText passwordText;
    private CheckBox check;
    private Button buttonSignUp;
    private int cursorPosition =0;

    public static final String AppPrefs = "AppPrefs";
    public static final String Login = "LoginKey";
    public static final String Password = "PasswordKey";
    public static final String IS_Sign_In = "UserLoginIn";

    private SharedPreferences sharedPreferences;
    public static boolean autoSignIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkinformation);

        loginText = (EditText) findViewById(R.id.editLoginText);
        passwordText = (EditText) findViewById(R.id.editPasswordText);
        buttonSignUp = (Button) findViewById(R.id.button);
        check = (CheckBox) findViewById(R.id.checkBox);

        sharedPreferences = getSharedPreferences(AppPrefs, Context.MODE_PRIVATE);

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    passwordText.setTransformationMethod(null);

                } else {
                    passwordText.setTransformationMethod(new PasswordTransformationMethod());

                }
                //Устанавливает курсор в конце слова
                //passwordText.setSelection(passwordText.getText().length());
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitInformation();
            }
        });

        passwordText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cursorPosition = passwordText.getSelectionStart();
                CharSequence enterText = passwordText.getText().toString();
                CharSequence cursorEnd = enterText.subSequence(cursorPosition,enterText.length());
                return false;
            }
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

        boolean Sign_In = sharedPreferences.getBoolean(IS_Sign_In,false);
        if(Sign_In){
            Intent intent = new Intent(this,OpenNewWindow.class);
            startActivity(intent);
        }
    }

    public void submitInformation() {

        if (loginText.getText().length() == 0 && passwordText.getText().length() == 0) {

            loginText.setError(getString(R.string.login_error));
            passwordText.setError(getString(R.string.password_error));
        } else if (loginText.getText().length() == 0) {
//
            loginText.setError(getString(R.string.login_error));
        } else if (passwordText.getText().length() == 0) {

            passwordText.setError(getString(R.string.password_error));
        } else {

            GoNextActivity();

            /*new AlertDialog.Builder(this)
                    .setTitle(R.string.final_title)
                    .setMessage(R.string.final_message)
                    .show();*/
        }
    }

    private void GoNextActivity(){

        String log = loginText.getText().toString();
        String pas = passwordText.getText().toString();

        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(Login,log);
        edit.putString(Password,pas);
        edit.putBoolean(IS_Sign_In,true);
        edit.apply();

        Intent intent = new Intent(this,OpenNewWindow.class);
        startActivity(intent);
    }

}

