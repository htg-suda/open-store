package com.htg.common.result;

import java.util.Collection;

public class RespList<T> {
    private Collection<T> list;


    public RespList(Collection<T> list) {
        this.list = list;
    }

    public Collection<T> getList() {
        return list;
    }

    public void setList(Collection<T> list) {
        this.list = list;
    }
}
