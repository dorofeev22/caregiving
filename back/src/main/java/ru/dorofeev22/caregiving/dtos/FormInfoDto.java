package ru.dorofeev22.caregiving.dtos;

import com.fasterxml.jackson.databind.JsonNode;

public class FormInfoDto {
    private Long id;
    private JsonNode info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInfo(JsonNode info) {
        this.info = info;
    }

    public JsonNode getInfo() {
        return info;
    }
}
