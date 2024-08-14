package com.example.experiment2;


import static android.widget.AdapterView.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    String[] operators = {"add", "subtract", "multiply", "divide", "mod"};
    EditText editTextNumber1, editTextNumber2;
    Spinner operatorSpinner;
    Button calculateButton;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextNumber1 = findViewById(R.id.num1);
        editTextNumber2 = findViewById(R.id.num2);
        operatorSpinner = findViewById(R.id.operatorspinner);
        calculateButton = findViewById(R.id.button);
        resultTextView = findViewById(R.id.resultView);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                operators
        );

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        operatorSpinner.setAdapter(adapter);

        // Set OnItemSelectedListener to the spinner
        operatorSpinner.setOnItemSelectedListener(this);

        // Set OnClickListener to the button
        calculateButton.setOnClickListener(view -> calculateResult());
    }

    // Method to calculate result based on selected operator and display it
    private void calculateResult() {
        // Get numbers from EditText fields
        String number1Str = editTextNumber1.getText().toString();
        String number2Str = editTextNumber2.getText().toString();

        if (number1Str.isEmpty() || number2Str.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        // Parse numbers
        double number1 = Double.parseDouble(number1Str);
        double number2 = Double.parseDouble(number2Str);

        // Get selected operator
        String selectedOperator = operators[operatorSpinner.getSelectedItemPosition()];

        // Calculate result based on operator
        double result = 0;
        switch (selectedOperator) {
            case "add":
                result = number1 + number2;
                break;
            case "subtract":
                result = number1 - number2;
                break;
            case "multiply":
                result = number1 * number2;
                break;
            case "divide":
                if (number2 != 0) {
                    result = number1 / number2;
                } else {
                    Toast.makeText(MainActivity.this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case "mod":
                result = number1 % number2;
                break;
        }

        // Display result in TextView
        resultTextView.setText("Result: " + result);
    }

    // Performing action when an item is selected from the spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        // Optionally, you can add code here to handle spinner item selection
    }

    // Handling case where nothing is selected in the spinner
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Optionally, you can add code here for what happens when nothing is selected
    }
}