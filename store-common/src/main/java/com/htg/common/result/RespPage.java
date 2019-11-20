package com.htg.common.result;

import java.util.Collection;

public class RespPage<T> {
    private Collection<T> list;
    private Long total;


    public RespPage(Collection<T> list, Long total) {
        this.list = list;
        this.total = total;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Collection<T> getList() {
        return list;
    }

    public void setList(Collection<T> list) {
        this.list = list;
    }
}
