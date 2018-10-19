package com.morganstanley.anand.validation;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class AlphaNumbericValidation implements Validation{

    private final static String pattern= "^[a-zA-Z0-9 ]*$";
    private final static int[] index = {0, 1, 2};

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
