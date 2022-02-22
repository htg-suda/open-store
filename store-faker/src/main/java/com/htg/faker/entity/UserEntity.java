package com.htg.faker.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "demo_test")
public class UserEntity {
    @Id
    private String uid;
    private Long sid;
    private String username;
    private String nickname;
    private String prv;
    private String commpany;
    private String phone_type;
    private String birth;

}
