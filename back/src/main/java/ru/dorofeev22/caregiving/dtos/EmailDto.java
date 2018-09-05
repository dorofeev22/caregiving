package ru.dorofeev22.caregiving.dtos;

import javax.validation.constraints.NotBlank;

public class EmailDto {

    @NotBlank
    private String to;

    @NotBlank
    private String subject;

    @NotBlank
    private String text;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
