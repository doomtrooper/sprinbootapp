package com.morganstanley.anand.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ValidatorService {

    List<Validation> getValidations();

    default Optional<List<String>> validate(String[] attrs){
        List<String> error = new ArrayList<>();
        for (Validation validation : getValidations()) {
            error.addAll(validation.validate(attrs));
        }
        return error.size()>0?Optional.of(error):Optional.empty();
    }
}
