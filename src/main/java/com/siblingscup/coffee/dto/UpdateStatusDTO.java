package com.siblingscup.coffee.dto;

import lombok.Data;

@Data
public class UpdateStatusDTO {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
