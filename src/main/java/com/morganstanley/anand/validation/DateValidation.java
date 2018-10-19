package com.morganstanley.anand.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateValidation implements Validation {

    private final static int[] index = {5, 6};
    private final static SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");

    @Override
    public List<String> validate(String[] attrs) {
        List<String> errors = new ArrayList<>();
        sdf.setLenient(false);
        for (int anIndex : index) {
            if (anIndex < attrs.length) {
                try {
                    //if not valid, it will throw ParseException
                    Date date = sdf.parse(attrs[anIndex]);
                    System.out.println(date);
                } catch (ParseException e) {
                    errors.add("Index " + anIndex + " NOT valid");
                }
            }
        }
        if (errors.isEmpty()){
            Date settlementDate = null;
            Date tradeDate = null;
            try {
                settlementDate = sdf.parse(attrs[5]);
                tradeDate = sdf.parse(attrs[6]);
                if (settlementDate.before(tradeDate)) errors.add("Settlement Date should always be greater than trade Date.");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return errors;
    }
}
