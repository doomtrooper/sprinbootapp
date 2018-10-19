package com.morganstanley.anand.validation;

import java.util.ArrayList;
import java.util.List;

public class BuySellValidation implements Validation {
    private final static String pattern= "^[BS]*$";
    private final static int[] index = {7};

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
