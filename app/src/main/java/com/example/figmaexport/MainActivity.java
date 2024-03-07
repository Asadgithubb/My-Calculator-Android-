package com.example.figmaexport;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText resultEditText;
    Button clearButton, themeButton;
    Button[] numberButtons = new Button[10];
    Button addButton, subtractButton, multiplyButton, divideButton;
    Button equalsButton, dotButton;

    StringBuilder currentNumber = new StringBuilder();
    double num1 = Double.NaN;
    double num2;
    char operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultEditText = findViewById(R.id.result);
        clearButton = findViewById(R.id.clear_button);
        themeButton = findViewById(R.id.theme_button);

        clearButton.setOnClickListener(this);
        themeButton.setOnClickListener(this);

        // Initialize number buttons
        for (int i = 0; i < 10; i++) {
            String buttonID = "button_" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            numberButtons[i] = findViewById(resID);
            numberButtons[i].setOnClickListener(this);
        }

        // Initialize operator buttons
        addButton = findViewById(R.id.button_add);
        subtractButton = findViewById(R.id.button_subtract);
        multiplyButton = findViewById(R.id.button_multiply);
        divideButton = findViewById(R.id.button_divide);
        equalsButton = findViewById(R.id.button_equals);
        dotButton = findViewById(R.id.button_dot);

        addButton.setOnClickListener(this);
        subtractButton.setOnClickListener(this);
        multiplyButton.setOnClickListener(this);
        divideButton.setOnClickListener(this);
        equalsButton.setOnClickListener(this);
        dotButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear_button:
                clear();
                break;
            case R.id.theme_button:
                toggleTheme();
                break;
            case R.id.button_equals:
                compute();
                break;
            case R.id.button_dot:
                if (!currentNumber.toString().contains(".")) {
                    currentNumber.append(".");
                }
                break;
            case R.id.button_add:
                setOperator('+');
                break;
            case R.id.button_subtract:
                setOperator('-');
                break;
            case R.id.button_multiply:
                setOperator('*');
                break;
            case R.id.button_divide:
                setOperator('/');
                break;
            default:
                for (int i = 0; i < 10; i++) {
                    if (v.getId() == numberButtons[i].getId()) {
                        currentNumber.append(i);
                        break;
                    }
                }
                break;
        }
        updateResult();
    }

    private void compute() {
        if (!Double.isNaN(num1)) {
            num2 = Double.parseDouble(currentNumber.toString());

            switch (operator) {
                case '+':
                    num1 += num2;
                    break;
                case '-':
                    num1 -= num2;
                    break;
                case '*':
                    num1 *= num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        num1 /= num2;
                    } else {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
            currentNumber.setLength(0);
            currentNumber.append(num1);
            num1 = Double.NaN;
        }
    }

    private void updateResult() {
        resultEditText.setText(currentNumber.toString());
    }

    private void clear() {
        currentNumber.setLength(0);
        resultEditText.setText("");
        num1 = Double.NaN;
    }

    private void toggleTheme() {
        // Your theme toggle logic here
        Toast.makeText(this, "Toggle Theme button clicked", Toast.LENGTH_SHORT).show();
    }

    private void setOperator(char op) {
        if (!Double.isNaN(num1)) {
            if (currentNumber.length() > 0) {
                num2 = Double.parseDouble(currentNumber.toString());
                switch (operator) {
                    case '+':
                        num1 += num2;
                        break;
                    case '-':
                        num1 -= num2;
                        break;
                    case '*':
                        num1 *= num2;
                        break;
                    case '/':
                        if (num2 != 0) {
                            num1 /= num2;
                        } else {
                            Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                currentNumber.setLength(0);
                currentNumber.append(num1);
                num1 = Double.NaN;
            }
            operator = op;
        } else {
            if (currentNumber.length() > 0) {
                num1 = Double.parseDouble(currentNumber.toString());
                operator = op;
                currentNumber.setLength(0);
            }
        }
    }
}
