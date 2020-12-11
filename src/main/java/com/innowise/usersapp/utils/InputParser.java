package com.innowise.usersapp.utils;

import com.innowise.usersapp.entity.Roles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputParser {

    public static List<Roles> parseRoles(String input) {
        List<Roles> roles = new ArrayList<>();
        String[] inputArray = input.replaceAll("\\s+", "").split(",").clone();
        for (String str : inputArray) {
            if (Validator.roleIndexValidation(str)) {
                roles.add(Roles.getRole(Integer.parseInt(str)));
            }
        }
        return roles;
    }

    public static List<String> parsePhones(String input) {
        String[] inputArray = input.replaceAll("\\s+", "").split(",").clone();
        return new ArrayList<>(Arrays.asList(inputArray));
    }
}
