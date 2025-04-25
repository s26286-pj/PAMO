package edu.pjatk.bmi.util;

import edu.pjatk.bmi.R;

public class BmiCalculator {

    public static double calculateBmi(double weight, double heightInMeters) {
        if (heightInMeters <= 0 || weight <= 0) return 0;
        return weight / (heightInMeters * heightInMeters);
    }

    public static int getBmiCategory(double bmi) {
        if (bmi < 18.5) {
            return R.string.bmi_underweight;
        } else if (bmi < 25) {
            return R.string.bmi_optimal;
        } else if (bmi < 30) {
            return R.string.bmi_overweight;
        } else {
            return R.string.bmi_obesity;
        }
    }
}