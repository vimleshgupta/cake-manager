package com.waracle.cakemanagerservice.client.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CakeResponse implements Serializable {

    private String title;
    private String desc;
    private String image;

}
