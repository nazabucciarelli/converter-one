package com.nbucciarelli.converter.client;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DollarEuroAPI {

    public static StringBuilder getResponse(){
        StringBuilder infoString = null;
        try{
            URL url = new URL("https://api.bluelytics.com.ar/v2/latest");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200){
                throw new RuntimeException("An error has ocurred in DollarAPI: Code " + responseCode);
            }else{
                infoString = new StringBuilder();
                Scanner sc = new Scanner(url.openStream());
                while(sc.hasNext()){
                    infoString.append(sc.next());
                }
                sc.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return infoString;
    }

    public static double getAvgValueOf(CurrencyEnum currency) {
        String currencyFormat = "";
        if(currency == currency.EURO){
            currencyFormat = "blue_euro";
        } else if (currency == currency.DOLLAR){
            currencyFormat = "blue";
        } else{
            throw new RuntimeException("Currency selected is not correct: Use 'euro' or 'dollar'");
        }
        double avgValue = 0;
        String stringToFind = "\""+ currencyFormat +"\":{\"value_avg\":";
        int indexOfAvgValue = getResponse().indexOf(stringToFind);
        String substringAvgValue = getResponse().substring(indexOfAvgValue + stringToFind.length());
        avgValue = Double.valueOf(substringAvgValue.substring(0,substringAvgValue.indexOf(',')));
        return avgValue;
    }

}
