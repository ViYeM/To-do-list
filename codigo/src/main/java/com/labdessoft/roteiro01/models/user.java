package com.labdessoft.roteiro01.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "user")
public class user {
    public static final String TABLE_NAME = "user";

    @Id
    private Long id;
    
}
