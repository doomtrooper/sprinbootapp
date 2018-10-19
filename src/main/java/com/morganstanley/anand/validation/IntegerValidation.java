package com.morganstanley.anand.validation;

import java.util.ArrayList;
import java.util.List;

public class IntegerValidation implements Validation {

    private final static String pattern= "^[0-9]*$";
    private final static int[] index = {4};

    @Override
    public List<String> validate(String[] attrs) {
        List<String> errors = new ArrayList<>();
        for (int anIndex : index) {
            if (anIndex < attrs.length) {
                if (!attrs[anIndex].matches(pattern)) {
                    errors.add("Index " + anIndex + " NOT valid");
                }
            }
        }
        return errors;
    }
}
