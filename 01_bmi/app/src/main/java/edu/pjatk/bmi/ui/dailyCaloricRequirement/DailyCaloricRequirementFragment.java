package edu.pjatk.bmi.ui.dailyCaloricRequirement;



import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;

import edu.pjatk.bmi.R;

public class DailyCaloricRequirementFragment extends Fragment {

    private int age = 0;
    private double weight = 0;
    private double height = 1.75;
    private boolean isWoman = false;
    private double calories = 0;

    private double activityLevel = 0;
    private TextView caloriesTextView;
    private TextView bmiDescriptionTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_caloric_requirement, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        caloriesTextView = view.findViewById(R.id.textViewCalories);
        bmiDescriptionTextView = view.findViewById(R.id.textViewDescription);

        EditText heightEditText = view.findViewById(R.id.editTextNumberDecimalHeight);
        heightEditText.addTextChangedListener(heightEditTextWatcher);

        EditText weightEditText = view.findViewById(R.id.editTextNumberDecimalWeight);
        weightEditText.addTextChangedListener(weightEditTextWatcher);

        EditText ageEditText = view.findViewById(R.id.editTextNumberDecimalAge);
        ageEditText.addTextChangedListener(ageEditTextWatcher);

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch sexEditSwitch = view.findViewById(R.id.sex);
        sexEditSwitch.setOnCheckedChangeListener(sexSwitchListener);

        Spinner spinner = view.findViewById(R.id.spinnerOptionsActivityLevel);
        spinner.setOnItemSelectedListener(activityLevelSelectedListener);
    }

    @SuppressLint("SetTextI18n")
    private void calculateBMR() {
        if (isWoman) {
            calories = (655.1 + (9.567 * weight) + (1.85 * height) - (4.68 * age)) * activityLevel;

        } else {
            calories = (66.47 + (13.7 * weight) + (5.0 * height) - (6.76 * age)) * activityLevel;
        }
        caloriesTextView.setText(Double.toString(calories));
        setDishDescription();
    }

    private void setDishDescription() {
        if (calories > 500 && calories < 1000) {
            bmiDescriptionTextView.setText(R.string.dish_1);
        } else if (calories < 1500) {
            bmiDescriptionTextView.setText(R.string.dish_2);
        } else if (calories < 2000) {
            bmiDescriptionTextView.setText(R.string.dish_3);
        } else {
            bmiDescriptionTextView.setText(R.string.dish_4);
        }
    }

    private final TextWatcher ageEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                age = Integer.parseInt(s.toString());
            } catch (NumberFormatException e) {
                age = 0;
            }
            calculateBMR();
        }

        @Override
        public void afterTextChanged(Editable s) {}

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    };

    private final CompoundButton.OnCheckedChangeListener sexSwitchListener = (buttonView, isChecked) -> {
        isWoman = isChecked;
        calculateBMR();
    };

    private final AdapterView.OnItemSelectedListener activityLevelSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // Map spinner position to physical activity multiplier.
            // Adjust these values as needed for your application.
            switch (position) {
                case 0:
                    activityLevel = 1.2;  // Sedentary
                    break;
                case 1:
                    activityLevel = 1.375; // Light activity
                    break;
                case 2:
                    activityLevel = 1.55;  // Moderate activity
                    break;
                case 3:
                    activityLevel = 1.725; // Very active
                    break;
                case 4:
                    activityLevel = 1.9;   // Extra active
                    break;
                default:
                    activityLevel = 1.0;   // Default fallback
            }
            calculateBMR();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // Optional: Handle the case when nothing is selected if needed.
        }
    };

    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                weight = Double.parseDouble(s.toString());
            } catch (NumberFormatException e) {
                weight = 0;
            }
            calculateBMR();
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
            calculateBMR();
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