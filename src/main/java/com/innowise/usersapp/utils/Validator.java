package com.innowise.usersapp.utils;

import com.innowise.usersapp.entity.Roles;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean emailValidation(String email) {
        String regex = "^(.+)[@](.+)[.](.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean phoneValidation(String phone) {
        String regex = "[3][7][5][0-9]{9}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean phoneListValidation(List<String> phones) {
        if (phones.size() > 3 || phones.size() < 1) {
            return false;
        }

        for (String phone : phones) {
            if (!phoneValidation(phone)) {
                return false;
            }
        }
        return true;
    }

    public static boolean phonesStringValidation(String phonesStr) {
        String regex1 = "[3][7][5][0-9]{9}";
        String regex2 = "[3][7][5][0-9]{9}[,][ ][3][7][5][0-9]{9}";
        String regex3 = "[3][7][5][0-9]{9}[,][ ][3][7][5][0-9]{9}[,][ ][3][7][5][0-9]{9}";
        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);
        Pattern pattern3 = Pattern.compile(regex3);
        Matcher matcher1 = pattern1.matcher(phonesStr);
        Matcher matcher2 = pattern2.matcher(phonesStr);
        Matcher matcher3 = pattern3.matcher(phonesStr);
        return matcher1.matches() || matcher2.matches() || matcher3.matches();
    }

    public static boolean rolesListValidation(List<Roles> roles) {
        if (roles.size() > 2 || roles.size() < 1) {
            return false;
        } else if (roles.size() > 1 && roles.contains(Roles.SUPER_ADMIN)) {
            return false;
        } else if (roles.size() != 2 || roles.get(0).getLevel() != roles.get(1).getLevel()) {
            return true;
        }
        return false;
    }

    public static boolean roleIndexValidation(String roleStr) {
        String regex = "[1-5]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(roleStr);
        return matcher.matches();
    }

    public static boolean rolesStringValidation(String roleStr) {
        String regex1 = "[1-5]";
        String regex2 = "[1-5][,][ ][1-5]";
        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher1 = pattern1.matcher(roleStr);
        Matcher matcher2 = pattern2.matcher(roleStr);
        return matcher1.matches() || matcher2.matches();
    }

    public static boolean idValidation(String idStr) {
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(idStr);
        return matcher.matches();
    }
}
