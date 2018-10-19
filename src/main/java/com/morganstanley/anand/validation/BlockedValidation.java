package com.morganstanley.anand.validation;

import com.morganstanley.anand.repository.BlockStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlockedValidation implements Validation {


    @Autowired
    BlockStockRepository blockStockRepository;
    private final static int[] index = {1};

    @Override
    public List<String> validate(String[] attrs) {
        List<String> errors = new ArrayList<>();
        for (int anIndex : index) {
            if (anIndex < attrs.length) {
                if (blockStockRepository.findById(attrs[anIndex]).isPresent()) {
                    errors.add("Blocked Trade: " + attrs[anIndex] + " NOT valid");
                }
            }
        }
        return errors;
    }
}
