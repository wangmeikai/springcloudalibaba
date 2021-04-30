package com.wmk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/27
 * @TIME: 22:15
 **/
@Component("stu")
public class Stu {


    private Person person;

    @Autowired
    public void setPerson(Person person) {
        this.person = person;
    }
}
