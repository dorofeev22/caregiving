package ru.dorofeev22.caregiving.services;

import org.springframework.stereotype.Service;
import ru.dorofeev22.caregiving.dtos.PriceDto;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summarizingDouble;

@Service
public class ProductService {

    /**
     * Create prices analysis result as a list of pair product number and price info (count, sum, min, max, avg)
     * @param priceDtos list of products prices
     */
    public Map<String, DoubleSummaryStatistics> getInfoByProductNumber(List<PriceDto> priceDtos) {
        return priceDtos.stream()
                        .collect(groupingBy(PriceDto::getProductNumber, summarizingDouble(PriceDto::getPriceAsDouble)));
    }
}
