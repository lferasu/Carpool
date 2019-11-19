package edu.mum.ea2.auth_service.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Messages implements Serializable {
    private String token;
    private Integer userId;

}
