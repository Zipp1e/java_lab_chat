package com.company;

public class Rapper implements Person {
    @Override
    public String answer(String word){
        return("Imma Lil " + word + ", yo!");
    }

    @Override
    public String name() {
        return "rapper";
    }
}
