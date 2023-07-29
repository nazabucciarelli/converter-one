package com.nbucciarelli.converter.main;

import javax.swing.*;
import java.text.DecimalFormat;

public class Main {
    static String[] options = {"Convert currency to another one", "Convert distance measure to another one"};

    public static void main(String[] args) {
        String selected = (String) JOptionPane.showInputDialog(null, null,
                "Select", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (selected != null) {
            openInput(selected);
        } else {
            System.out.println("User's closed the main menu");
        }
    }

    private static void openInput(String option) {
        String value = JOptionPane.showInputDialog(null, "Insert a value to convert:", "Input"
                , JOptionPane.PLAIN_MESSAGE);
        if (value == null) {
            System.out.println("User's closed the input");
            System.exit(0);
        }
        double doubleValue = 0;
        try {
            doubleValue = Double.valueOf(value);
            System.out.println("Succesfully converted to double");
        } catch (NumberFormatException e) {
            System.err.println("Error converting to int");
            JOptionPane.showMessageDialog(null, "You must insert a number", "Warning",
                    JOptionPane.ERROR_MESSAGE);
            openInput(option);
        }
        chooseTypeOfConvertion(option, doubleValue);
    }

    private static void chooseTypeOfConvertion(String option, double value) {
        String[] optionsCurrency = {"Pesos Argentinos to Dollars", "Pesos Argentinos to Euros",
                "Pesos Argentinos to Corona Danesa", "Dollars to Pesos Argentinos", "Euros to Pesos Argentinos",
                "Corona Danesa to Pesos Argentinos"};
        String[] optionsDistance = {"Meters to Centimeters", "Kilometers to Miles",
                "Miles to Kilometers", "Centimeters to Meters"};
        String chosenConvertType;
        DecimalFormat df = new DecimalFormat("#.##");
        if (option.equals(options[0])) {
            chosenConvertType = (String) JOptionPane.showInputDialog(null, null,
                    "Choose currency convertion", JOptionPane.PLAIN_MESSAGE, null, optionsCurrency, optionsCurrency[0]);
        } else {
            chosenConvertType = (String) JOptionPane.showInputDialog(null, null,
                    "Choose distance measure convertion", JOptionPane.PLAIN_MESSAGE, null, optionsDistance,
                    optionsDistance[0]);
        }
        double result = 0;
        if (chosenConvertType == null) {
            System.out.println("User's closed the menu of choose");
            System.exit(0);
        } else if (chosenConvertType.equals(optionsCurrency[0])) { // ARS to USD
            result = value * 0.0037;
        } else if (chosenConvertType.equals(optionsCurrency[1])) { // ARS to EUR
            result = value * 0.0033;
        } else if (chosenConvertType.equals(optionsCurrency[2])) { // ARS to COR
            result = value * 0.0247;
        } else if (chosenConvertType.equals(optionsCurrency[3])) { // USD to ARS
            result = value * 273.6507;
        } else if (chosenConvertType.equals(optionsCurrency[4])) { // EUR to ARS
            result = value * 301.8750;
        } else if (chosenConvertType.equals(optionsCurrency[5])) { // COR to ARS
            result = value * 40.38;
        } else if (chosenConvertType.equals(optionsDistance[0])) { // Meters to Centimeters
            result = value * 100;
        } else if (chosenConvertType.equals(optionsDistance[1])) { // KM to Miles
            result = value * 0.621371;
        } else if (chosenConvertType.equals(optionsDistance[2])) { // Miles to KM
            result = value * 1.60934;
        } else if (chosenConvertType.equals(optionsDistance[3])) { // CM to Meters
            result = value * 0.01;
        }
        JOptionPane.showMessageDialog(null, value + " " + chosenConvertType + " is equal to " + df.format(result));
        chooseTypeOfConvertion(option, value);
    }
}
