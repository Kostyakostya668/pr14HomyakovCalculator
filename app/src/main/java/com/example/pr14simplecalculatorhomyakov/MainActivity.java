package com.example.pr14simplecalculatorhomyakov;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNum1, etNum2;
    Button btnAdd, btnSub, btnMult, btnDiv;
    TextView tvResult;
    String oper = "";

    final int MENU_RESET_ID = 1;
    final int MENU_QUIT_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMult = findViewById(R.id.btnMult);
        btnDiv = findViewById(R.id.btnDiv);
        tvResult = findViewById(R.id.tvResult);

        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        float num1, num2, result = 0;

        if (TextUtils.isEmpty(etNum1.getText().toString()) ||
                TextUtils.isEmpty(etNum2.getText().toString())) {
            tvResult.setText("Введите оба числа!");
            return;
        }

        num1 = Float.parseFloat(etNum1.getText().toString());
        num2 = Float.parseFloat(etNum2.getText().toString());

        if (v.getId() == R.id.btnAdd) {
            oper = "+";
            result = num1 + num2;
        } else if (v.getId() == R.id.btnSub) {
            oper = "-";
            result = num1 - num2;
        } else if (v.getId() == R.id.btnMult) {
            oper = "*";
            result = num1 * num2;
        } else if (v.getId() == R.id.btnDiv) {
            oper = "/";
            if (num2 == 0) {
                tvResult.setText("Ошибка: деление на 0");
                return;
            }
            result = num1 / num2;
        }

        tvResult.setText(num1 + " " + oper + " " + num2 + " = " + result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_RESET_ID, 0, "Очистить");
        menu.add(0, MENU_QUIT_ID, 0, "Выход");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == MENU_RESET_ID) {
            etNum1.setText("");
            etNum2.setText("");
            tvResult.setText("Результат:");
            return true;
        } else if (id == MENU_QUIT_ID) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
