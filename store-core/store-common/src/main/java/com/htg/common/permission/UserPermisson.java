package com.htg.common.permission;

import lombok.Data;

import java.util.List;

@Data
public class UserPermisson {
    private String username;
    private List<String> authorities;
}
