package com.nbucciarelli.converter.main;

import com.nbucciarelli.converter.client.CurrencyEnum;
import com.nbucciarelli.converter.client.DollarEuroAPI;

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
                "Dollars to Pesos Argentinos", "Euros to Pesos Argentinos"};
        String[] optionsDistance = {"Meters to Centimeters", "Kilometers to Miles",
                "Miles to Kilometers", "Centimeters to Meters"};
        String chosenConvertType;
        DecimalFormat df = new DecimalFormat("#.###");
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
            result = value / DollarEuroAPI.getAvgValueOf(CurrencyEnum.DOLLAR);
            ;
        } else if (chosenConvertType.equals(optionsCurrency[1])) { // ARS to EUR
            result = value / DollarEuroAPI.getAvgValueOf(CurrencyEnum.EURO);
        } else if (chosenConvertType.equals(optionsCurrency[2])) { // USD to ARS
            result = value * DollarEuroAPI.getAvgValueOf(CurrencyEnum.DOLLAR);
        } else if (chosenConvertType.equals(optionsCurrency[3])) { // EUR to ARS
            result = value * DollarEuroAPI.getAvgValueOf(CurrencyEnum.EURO);
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
        int answer = JOptionPane.showConfirmDialog(null,"Wish you continue converting?","Question",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null);
        if(answer == JOptionPane.YES_OPTION){
            main(null);
        } else{
            System.exit(0);
        }

    }
}
