package ru.dorofeev22.caregiving.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dorofeev22.caregiving.dtos.FormInfoDto;
import ru.dorofeev22.caregiving.services.FormInfoService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/formInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FormInfoController {

    @Autowired
    private FormInfoService formInfoService;

    @GetMapping("/{id}")
    public FormInfoDto formInfo(@PathVariable Long id, HttpServletResponse response) {
        FormInfoDto u = formInfoService.getById(id);
        response.setStatus((u != null ? HttpStatus.OK : HttpStatus.NOT_FOUND).value());
        return u;
    }

}
