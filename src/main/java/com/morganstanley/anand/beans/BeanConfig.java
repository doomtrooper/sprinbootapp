package com.morganstanley.anand.beans;

import com.morganstanley.anand.validation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class BeanConfig {

    @Bean
    public ValidatorService validatorService(){
        return new ValidatorService() {
            @Override
            public List<Validation> getValidations() {
                return Arrays.asList(new AlphaNumbericValidation(), new IntegerValidation(), new DateValidation(), new BuySellValidation());
            }
        };
    }

}
