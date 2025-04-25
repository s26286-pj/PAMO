package edu.pjatk.bmi;

import org.junit.Test;
import static org.junit.Assert.*;

import edu.pjatk.bmi.util.BmiCalculator;

public class BmiCalculatorUnitTest {

    @Test
    public void testCalculateBmi_normalValues() {
        double bmi = BmiCalculator.calculateBmi(70, 1.75);
        assertEquals(22.86, bmi, 0.01);
    }

    @Test
    public void testGetBmiCategory_overweight() {
        assertEquals(R.string.bmi_overweight, BmiCalculator.getBmiCategory(27.5));
    }
}
