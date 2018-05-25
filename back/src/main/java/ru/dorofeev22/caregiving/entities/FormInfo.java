package ru.dorofeev22.caregiving.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "caregiving")
public class FormInfo extends BaseEntity {
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
