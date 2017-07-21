package com.example.vadym.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.vadym.myapplication.R.id.textView2;

public class MainActivity extends AppCompatActivity {

    private EditText loginText;
    private EditText passwordText;
    private TextView viewText;
    private CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);

        loginText = (EditText)findViewById(R.id.editText2);
        passwordText = (EditText)findViewById(R.id.editText3);
        viewText = (TextView) findViewById(textView2);
        check = (CheckBox) findViewById(R.id.checkBox);
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    passwordText.setTransformationMethod(null);

                }else{
                    passwordText.setTransformationMethod(new PasswordTransformationMethod());
                }
                passwordText.setSelection(passwordText.getText().length());
            }
        });
    }

    public void submitInformation(View view) {

        if (loginText.getText().length() == 0 && passwordText.getText().length() == 0) {
            viewText.setVisibility(view.VISIBLE);
            viewText.setText("Please write login and password");
        }else if(loginText.getText().length() == 0){
            viewText.setVisibility(view.VISIBLE);
            viewText.setText("Please write login");
        }else if(passwordText.getText().length() == 0){
            viewText.setVisibility(view.VISIBLE);
            viewText.setText("Please write password");
        }else {
            viewText.setVisibility(view.VISIBLE);
            viewText.setText("Vova give me next task");
        }
    }
}
