package com.lcbo.util;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This class was created for work with strings
 */
public class Util {

    /**
     * method accept price of product in cents anr return string in dollar format with two
     * symbols after point
     * @param number
     * @return
     */
    public static String formatToDollar(float number) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        DecimalFormat df = (DecimalFormat)nf;
        df.applyPattern("#.##");

        return df.format(number/100);
    }

    /**
     * method is formating a set of filters to string format what is usual for GET parameter
     * @param setFilter
     * @return
     */
    public static String makeFilterInString(Set<String> setFilter){
        StringBuilder filter = new StringBuilder("");
        for (String word : setFilter){
            if(!filter.toString().equals("")) {
                filter.append(",");
            }
                filter.append(word);
        }

        return filter.toString();
    }

    /**
     * method make first letter of word to upper case. It's need for possibility to equal
     * input data with API response
     * @param cityName
     * @return
     */
    public static String makeCapitalLetter(String cityName){
        List<Character> list = new ArrayList<>();
        for (Character c: cityName.toCharArray()){
            list.add(c);
        }
        list.set(0,Character.toUpperCase(list.get(0)));
        StringBuilder result = new StringBuilder();
        for(Character item: list){
            result.append(item);
        }

        return result.toString();
    }

    /**
     * method parse string filter to array list
     * @param filter
     * @return
     */
    public static ArrayList<String> parseFilter(String filter) {
        ArrayList<String> filterList = new ArrayList<>();

        StringBuilder buffer = new StringBuilder("");

        ArrayList<Character> characters = new ArrayList<>();
        for (Character c: filter.toCharArray()){
            characters.add(c);
        }

        for (int i = 0; i != characters.size(); i++) {
            if (characters.get(i).equals(',')) {
                filterList.add(buffer.toString());
                buffer = new StringBuilder("");
            } else if (i == characters.size()) {
                filterList.add(buffer.toString());
            } else {
                buffer.append(characters.get(i));
            }
        }

        return filterList;
    }

    public static ArrayList<String> getTwoPhoneNumbers(String numbersString){
        ArrayList<String> numbersList = new ArrayList<>();
        StringBuilder buffer = new StringBuilder("");
        ArrayList<Character> characters = new ArrayList<>();
        for (Character c: numbersString.toCharArray()){
            characters.add(c);
        }
        for (int i = 0; i != characters.size(); i++) {
            if (characters.get(i).equals(',')) {
                numbersList.add(buffer.toString());
                buffer = new StringBuilder("");
            } else if (i == characters.size()) {
                numbersList.add(buffer.toString());
            } else {
                buffer.append(characters.get(i));
            }
        }

        if(numbersList.size() == 2){
            return numbersList;
        }

        return null;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
