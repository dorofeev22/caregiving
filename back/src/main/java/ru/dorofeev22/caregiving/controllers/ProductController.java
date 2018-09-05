package ru.dorofeev22.caregiving.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dorofeev22.caregiving.dtos.PriceDto;
import ru.dorofeev22.caregiving.entities.Product;
import ru.dorofeev22.caregiving.repository.ProductRepositoryEm;
import ru.dorofeev22.caregiving.services.ProductService;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepositoryEm productRepositoryEm;

    @PostMapping("/prices")
    public Map<String, DoubleSummaryStatistics> info(@RequestBody List<PriceDto> priceDtos) {
        return productService.getInfoByProductNumber(priceDtos);
    }

    @PostMapping
    public void create(@RequestBody List<Product> products) {
        productRepositoryEm.saveCollection(products);
    }

}
