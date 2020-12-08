package org.example.service;


public class DemoService {

    private String sayWhat;

    private String toWho;

    public DemoService (String sayWhat, String toWho) {
        this.sayWhat = sayWhat;
        this.toWho = toWho;
    }

    public String say() {
        return this.sayWhat + "!  " + this.toWho;
    }
}
