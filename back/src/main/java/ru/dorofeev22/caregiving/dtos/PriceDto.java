package ru.dorofeev22.caregiving.dtos;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class PriceDto {

    @NotBlank
    private String productNumber;
    @NotBlank
    private BigDecimal price;

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getPriceAsDouble() {
        return price.doubleValue();
    }
}
