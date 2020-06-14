package com.company;

public class IntervalValueExeption extends Exception {
    @Override
    public String toString(){
        return "Invalid interval. minVal >  maxVal!";
    }
}
