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
import android.widget.TextView;

import static com.example.vadym.myapplication.R.id.textView2;

public class MainActivity extends AppCompatActivity {

    private EditText loginText;
    private EditText passwordText;
    private TextView viewText;
    private CheckBox check;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: 21.07.17  Переназви лейаут, з його назви має буть зрозуміло, для якої цілі він був створений.
        setContentView(R.layout.layout2);

        loginText = (EditText) findViewById(R.id.editText2);
        passwordText = (EditText) findViewById(R.id.editText3);
        viewText = (TextView) findViewById(textView2);
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
                passwordText.setSelection(passwordText.getText().length());
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitInformation(view);
            }
        });
    }


    //TODO: 21.07.17  Це спочатку можна заюзати сетап кліків через шаблон, але на ма  бутнє - краще юзати це через код.
    //TODO: 21.07.17  Типу як зверху я зробив.

    public void submitInformation(View view) {

        //TODO: 21.07.17  Те, що ти зробив обпрацювання опмилок - вже добре.
        //TODO: 21.07.17  Проблема тепер в іншому - ти зробив так, що в тебе помилка показується у вьюсі, яка десь знизу.
        //TODO: 21.07.17  Я її спочатку і не побачив, але потім згадав, що ти одне поле текстове якесь маєш.
        //TODO: 21.07.17  До речі, яке обзивається viewText і нічого з цього не ясно.
        //TODO: 21.07.17  Дам підказку - у textView є метод setError.
        //TODO: 21.07.17  Він показує помилку до TextView конкретного. І користувач відразу бачить, де помилка є.
        //TODO: 21.07.17  В твоєму випадку клавіатура закрила підказку і я не бачив ніякої помилки, поки не закрив клаву.
        if (loginText.getText().length() == 0 && passwordText.getText().length() == 0) {
            //TODO: 21.07.17  Тобі студія показує, що ти робиш помилку.
            //TODO: 21.07.17  Коли стаєш на підсвічене місце і натискаєш лівий Alt і Enter, тобі дає підказку, яка виправить цю помилку
            //TODO: 21.07.17  Попередження - Access static ..bla-bla-bla
            //TODO: 21.07.17  Треба замість view.VISIBLE зробити View.VISIBLE, далі не поправив це. Сам зробиш.
            viewText.setVisibility(View.VISIBLE);
            viewText.setText("Please write login and password");
        } else if (loginText.getText().length() == 0) {
//            viewText.setVisibility(view.VISIBLE);
            //// TODO: 21.07.17 подивися цей метод і розберися з ним
            loginText.setError("Please write login");
//            viewText.setText("Please write login");
        } else if (passwordText.getText().length() == 0) {
            viewText.setVisibility(view.VISIBLE);
            viewText.setText("Please write password");
        } else {
            viewText.setVisibility(view.VISIBLE);
            viewText.setText("Vova give me next task");

            //TODO: 21.07.17  Юзай не прямі записи тексту, а в стрінгових файлах.
            //TODO: 21.07.17  Пізніше ти прочитаєш ще і про переклади, як їх робити правильно.
            new AlertDialog.Builder(this)
                    .setTitle(R.string.final_title)
                    .setMessage(R.string.final_message)
                    .show();
        }
    }
}
