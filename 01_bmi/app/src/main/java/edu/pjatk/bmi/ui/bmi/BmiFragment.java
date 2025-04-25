package edu.pjatk.bmi.ui.bmi;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;

import edu.pjatk.bmi.R;
import edu.pjatk.bmi.util.BmiCalculator;

public class BmiFragment extends Fragment {

    private static final DecimalFormat df = new DecimalFormat("#.00");

    private double weight = 0;
    private double height = 1.75; // Default height in meters
    private double bmi = 0;
    private TextView bmiTextView;
    private TextView bmiDescriptionTextView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bmi_calculator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bmiTextView = view.findViewById(R.id.textViewBMI);
        bmiDescriptionTextView = view.findViewById(R.id.textViewDescription);

        EditText heightEditText = view.findViewById(R.id.editTextNumberDecimalHeight);
        heightEditText.addTextChangedListener(heightEditTextWatcher);

        EditText weightEditText = view.findViewById(R.id.editTextNumberDecimalWeight);
        weightEditText.addTextChangedListener(weightEditTextWatcher);
    }

    private void updateBmiDisplay() {
        bmi = BmiCalculator.calculateBmi(weight, height);
        bmiTextView.setText(df.format(bmi));
        bmiDescriptionTextView.setText(BmiCalculator.getBmiCategory(bmi));
    }

    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                weight = Double.parseDouble(s.toString());
            } catch (NumberFormatException e) {
                weight = 0;
            }
            updateBmiDisplay();
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
            updateBmiDisplay();
        }

        @Override
        public void afterTextChanged(Editable s) {}

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}