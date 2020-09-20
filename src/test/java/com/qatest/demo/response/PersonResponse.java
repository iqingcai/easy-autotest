package com.qatest.demo.response;

import com.qatest.demo.model.Person;
import lombok.Data;

@Data
public class PersonResponse extends BasicResponse {

    private Person person;

}
