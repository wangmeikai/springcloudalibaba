package com.wmk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/27
 * @TIME: 22:14
 **/
@Component
public class Person {

    private Stu stu;

    @Autowired
    public void setStu(Stu stu) {
        this.stu = stu;
    }
}
