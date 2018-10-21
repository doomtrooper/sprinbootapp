package com.morganstanley.anand.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class GenericException {
    private String reason;
    @JsonProperty(value = "http_code")
    private int httpCode;
}
