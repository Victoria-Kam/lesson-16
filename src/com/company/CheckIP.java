package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Пусть ip адрес задается с консоли.
 * Программа должна проверять валидность введенного ip адреса с
 * помощью регулярного выражения и выводить результат проверки на
 * экран.
 */
public class CheckIP {

    public boolean isValid(String enterIP){

        Pattern pattern = Pattern.compile("([0-1]?[0-9]+|2[0-4][0-9]|2[0-5]+)\\." +
                "([0-1]?[0-9]+|2[0-4][0-9]|2[0-5]+)\\." +
                "([0-1]?[0-9]+|2[0-4][0-9]|2[0-5]+)\\." +
                "([0-1]?[0-9]+|2[0-4][0-9]|2[0-5]+)$");

        Matcher matcher = pattern.matcher(enterIP);

        return matcher.find();
    }
}
