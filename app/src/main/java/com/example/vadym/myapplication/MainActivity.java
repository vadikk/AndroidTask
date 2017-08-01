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

        sharedPreferences = getSharedPreferences(Constants.APP_PREFS, Context.MODE_PRIVATE);

        loginText = (EditText) findViewById(R.id.editLoginText);
        passwordText = (EditText) findViewById(R.id.editPasswordText);
        buttonSignUp = (Button) findViewById(R.id.button);
        check = (CheckBox) findViewById(R.id.checkBox);

        mAutoSignIn = sharedPreferences.getBoolean(Constants.IS_SIGN_IN, false);

        check.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // TODO: Я подумав, що сеттінг трансформМетода може зтирати позицію опточного курсора
            // TODO: Тому, я її запам’ятовую тут...
            int cursorPositionStart = passwordText.getSelectionStart();

            if (isChecked) {
                passwordText.setTransformationMethod(null);

            } else {
                passwordText.setTransformationMethod(new PasswordTransformationMethod());

            }
            // TODO: ...і ставлю назад тут. Вуаля.
            //Устанавливает курсор в конце слова
            passwordText.setSelection(cursorPositionStart);
        });

        buttonSignUp.setOnClickListener(v -> submitInformation());


        // TODO: Це можна зтерти, воно не потрібне.
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

        // TODO: Тут гарно зробив, але в нас проблема є в тому, що є функціонал на цьому екрані, який є ідентичним.
        // TODO: Функція #goToNextActivity() має той самий код як знизу, тільки там ще викликається saveLoginAndPassword.
        // TODO: Спробуй трохи інакше написати цей код.
        if (mAutoSignIn) {
            Intent intent = new Intent(this, OpenNewWindow.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }












        init();
        initOnClickListeners();
        initViewsListeners();
    }



    private void init(){
        // TODO: Сиди напиши findViewById всі і перевірку на прапор IS_AUTO_SING_IN.
        // TODO: Дивись, ми цим вбиваємо двох зайців(щодо другого).
        // TODO: Якщо цей прапор в тру, то нам треба кинути на другу актівіті, інші дії з елементами ж не тре робити.
        // TODO: Якщо ні, то в ініт інших елементів воно саме зайде далі, оскільки викликаються всі функції.
    }

    private void initOnClickListeners(){
        // TODO: Тут всі setOnClickListener`и напиши.
    }
    private void initViewsListeners(){
        // TODO: А тут всі інші listeners.
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

    // TODO: Краще назвати функцію ясніше, типу так ->
//    private void setSettingsToSharedPreferencesEditor(){
    private void saveLoginAndPassword() {
        String log = loginText.getText().toString();
        String pas = passwordText.getText().toString();

        SharedPreferences.Editor edit = sharedPreferences.edit();

        edit.putString(Constants.LOGIN, log);
        edit.putString(Constants.PASSWORD, pas);
        edit.putBoolean(Constants.IS_SIGN_IN, true);
        edit.apply();
    }

    private void goToNextActivity() {
        saveLoginAndPassword();

        Intent intent = new Intent(this, OpenNewWindow.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}


