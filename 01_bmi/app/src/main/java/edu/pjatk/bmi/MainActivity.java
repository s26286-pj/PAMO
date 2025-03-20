package edu.pjatk.bmi;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final DecimalFormat df = new DecimalFormat("#.00");

    private double weight = 0;
    private double height = 1.75; // Default height in meters

    private double bmi = 0;
    private TextView heightTextView;
    private TextView weightTextView;
    private TextView bmiTextView;

    private TextView bmiDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bmiTextView = findViewById(R.id.textViewBMI);
        bmiDescriptionTextView = findViewById(R.id.textViewDescription);
        EditText heightEditText = findViewById(R.id.editTextNumberDecimalHeight);
        heightEditText.addTextChangedListener(heightEditTextWatcher);

        EditText weightEditText = findViewById(R.id.editTextNumberDecimalWeight);
        weightEditText.addTextChangedListener(weightEditTextWatcher);
    }

    private void calculate() {
        if (height > 0 && weight > 0) {
            bmi = weight / (height * height);
            bmiTextView.setText(df.format(bmi));
            setDescription();
        }
    }

    private void setDescription() {
        if (bmi < 18.5) {
            bmiDescriptionTextView.setText(R.string.bmi_underweight);
        } else if (bmi >= 18.5 && bmi < 25) {
            bmiDescriptionTextView.setText(R.string.bmi_optimal);
        } else if (bmi >= 25 && bmi < 30) {
            bmiDescriptionTextView.setText(R.string.bmi_overweight);
        } else {
            bmiDescriptionTextView.setText(R.string.bmi_obesity);
        }
    }

    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                weight = Double.parseDouble(s.toString());
            } catch (NumberFormatException e) {
                weight = 0;
            }
            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) {}

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    };

    private final TextWatcher heightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                height = Double.parseDouble(s.toString()) / 100;
            } catch (NumberFormatException e) {
                height = 0;
            }
            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) {}

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    };
}
