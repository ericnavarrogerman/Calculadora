package com.example.calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView display;
    private TextView operation;
    private CalculatorLogic calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new CalculatorLogic();
        display = findViewById(R.id.display);
        operation = findViewById(R.id.operation);
        setupButtons();
    }

    private void setupButtons() {
        setupNumberButtons();
        setupOperatorButtons();
        setupSpecialButtons();
    }

    private void setupNumberButtons() {
        int[] numberIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        };

        for (int id : numberIds) {
            findViewById(id).setOnClickListener(v -> {
                calculator.appendNumber(((Button) v).getText().toString());
                updateDisplay();
            });
        }
    }



    private void setupOperatorButtons() {
        int[] operatorIds = {
                R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide
        };

        for (int id : operatorIds) {
            findViewById(id).setOnClickListener(v -> {
                calculator.setOperator(((Button) v).getText().toString());
                updateDisplay();
            });
        }

        findViewById(R.id.btnEquals).setOnClickListener(v -> {
            calculator.calculateResult();
            updateDisplay();
        });

    }

    private void setupSpecialButtons() {
        findViewById(R.id.btnClear).setOnClickListener(v -> {
            calculator.clear();
            updateDisplay();
        });

        findViewById(R.id.btnDot).setOnClickListener(v -> {
            calculator.addDecimalPoint();
            updateDisplay();
        });

        findViewById(R.id.btnPercent).setOnClickListener(v -> {
            calculator.calculatePercent();
            updateDisplay();
        });

        findViewById(R.id.btnSqrt).setOnClickListener(v -> {
            calculator.calculateSquareRoot();
            updateDisplay();
        });

        findViewById(R.id.btnBackspace).setOnClickListener(v -> {
            calculator.backspace();
            updateDisplay();
        });
    }

    private void updateDisplay() {
        display.setText(calculator.getCurrentNumber());
        operation.setText(calculator.getOperationDisplay());
    }
}