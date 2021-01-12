package com.htg.common.exception;

public class DaoExcp extends RuntimeException{
    public static DaoExcp createExp(){
        return new DaoExcp();
    }
}
