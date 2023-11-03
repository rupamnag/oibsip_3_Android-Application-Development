package com.example.calcease;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView display;
    private String currentInput = "";
    private double firstOperand = 0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Set click listeners for number buttons (1-9)
        for (int i = 1; i <= 9; i++) {
            int resID = getResources().getIdentifier("button" + i, "id", getPackageName());
            Button button = findViewById(resID);
            setNumberButtonClickListener(button, String.valueOf(i));
        }

        // Set click listeners for operator buttons (+, -, *, /)
        Button plusButton = findViewById(R.id.buttonPlus);
        setOperatorButtonClickListener(plusButton, "+");

        Button minusButton = findViewById(R.id.buttonMinus);
        setOperatorButtonClickListener(minusButton, "-");

        Button multiplyButton = findViewById(R.id.buttonMultiply);
        setOperatorButtonClickListener(multiplyButton, "*");

        Button divideButton = findViewById(R.id.buttonDivide);
        setOperatorButtonClickListener(divideButton, "/");

        // Set click listener for the equals button
        Button equalsButton = findViewById(R.id.buttonEquals);
        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty()) {
                    double secondOperand = Double.parseDouble(currentInput);
                    double result = performOperation(firstOperand, secondOperand, operator);
                    currentInput = String.valueOf(result);
                    updateDisplay();
                }
            }
        });

        // Set click listener for the clear button
        Button clearButton = findViewById(R.id.buttonClear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput = "";
                firstOperand = 0;
                operator = "";
                updateDisplay();
            }
        });
    }

    private void setNumberButtonClickListener(Button button, final String number) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput += number;
                updateDisplay();
            }
        });
    }

    private void setOperatorButtonClickListener(Button button, final String op) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty()) {
                    firstOperand = Double.parseDouble(currentInput);
                    operator = op;
                    currentInput = "";
                }
            }
        });
    }

    private void updateDisplay() {
        display.setText(currentInput);
    }

    private double performOperation(double firstOperand, double secondOperand, String operator) {
        double result = 0;
        switch (operator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "/":
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    currentInput = "Error";
                    updateDisplay();
                }
                break;
        }
        return result;
    }

}
