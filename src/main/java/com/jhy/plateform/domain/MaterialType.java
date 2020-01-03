package com.jhy.plateform.domain;

import com.jhy.plateform.domain.base.BaseDomain;

import javax.validation.constraints.NotBlank;

public class MaterialType extends BaseDomain {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
