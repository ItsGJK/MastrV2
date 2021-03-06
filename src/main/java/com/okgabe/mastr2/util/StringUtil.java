/*
 * Copyright 2020 Gabriel Keller
 * This work is licensed under a Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * A copy of this license can be found at
 * https://creativecommons.org/licenses/by-nc-sa/4.0/legalcode.
 */

package com.okgabe.mastr2.util;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    public static String join(Object[] args){
        return join(args, " ", 0);
    }

    public static String join(Object[] args, String combiner){
        return join(args, combiner, 0);
    }

    public static String join(Object[] args, int start){
        return join(args, " ", start);
    }

    public static String join(Object[] args, String combiner, int start){
        StringBuilder sb = new StringBuilder();
        for(int i = start; i < args.length; i++){
            if(args[i] == null) continue;
            String s = args[i].toString();
            if(s.length()==0) continue;
            sb.append(s);

            if(i != args.length-1){
                sb.append(combiner);
            }
        }

        return sb.substring(0, sb.length());
    }

    // From commons-lang StringUtils
    public static boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static int positionInAlphabet(char c){
        c = Character.toLowerCase(c);
        if(c > 122) return -1;
        if(c < 97) return -1;
        return c - 97;
    }

    public static char numberToAlphabet(int n){
        return (char)(97+(n%26));
    }

    // For single-digit numbers - may rewrite to support any number of digits if I need it
    public static String numberToString(int i){
        switch(i){
            case 1:
                return  "one";
            case 2:
                return  "two";
            case 3:
                return  "three";
            case 4:
                return  "four";
            case 5:
                return  "five";
            case 6:
                return  "six";
            case 7:
                return  "seven";
            case 8:
                return  "eight";
            case 9:
                return "nine";
            default:
                return  "zero";
        }
    }

    public static String nameAndTag(Member m){
        return m.getEffectiveName() + "#" + m.getUser().getDiscriminator();
    }

    public static String nameAndTag(User u){
        return u.getAsTag();
    }

    /**
     * Splits a string by spaces
     *
     * @param str String to be split
     * @param respectQuotes If true, 'strings' within the string will be respected, meaning spaces may be included in a single argument if it is wrapped with quotes
     * @return List of split arguments
     */
    public static List<String> splitBySpaces(@NotNull String str, boolean respectQuotes){
        ArrayList<String> split = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        boolean inQuote = false;
        boolean escaped = false;

        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(respectQuotes && !escaped && (c == '"')){ // If quote is present
                inQuote = !inQuote;
            }
            else if(c == ' '){ // If space is present
                if(inQuote) { // If in a quote, append the space
                    builder.append(' ');
                }
                else{ // Else, add the string to the list and delete the string
                    if(builder.length()>0){ // (ignore if there's a double space or trailing/leading space)
                        split.add(builder.toString());
                        builder.delete(0, builder.length());
                    }

                }
            }
            else if(!escaped && c == '\\'){ // If backslash is present
                escaped = true;
                continue;
            }
            else{ // Else append character
                builder.append(c);
            }

            if(escaped) escaped = false;
        }

        if(builder.length() > 0) split.add(builder.toString()); // Append last string to list

        return split;
    }

    public static String pad(String s, boolean left, boolean right){
            String formatted = s;

            if(!formatted.startsWith(" ")){
                formatted = " " + formatted;
            }
            if(!formatted.endsWith(" ")){
                formatted += " ";
            }

            return formatted;
    }
}
