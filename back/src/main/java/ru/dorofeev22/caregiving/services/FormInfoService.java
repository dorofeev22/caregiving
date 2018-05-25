package ru.dorofeev22.caregiving.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dorofeev22.caregiving.dtos.FormInfoDto;
import ru.dorofeev22.caregiving.entities.FormInfo;
import ru.dorofeev22.caregiving.repository.FormInfoRepository;

import java.io.IOException;

@Service
public class FormInfoService extends BaseService {

    @Autowired
    private FormInfoRepository formInfoRepository;

    @Autowired
    private MapperService mapperService;

    @Transactional
    public FormInfoDto getById(Long id) {
        return toDto(findById(formInfoRepository, id));
    }

    public FormInfoDto toDto(FormInfo formInfo) {
        FormInfoDto dto = mapperService.toDto(formInfo, FormInfoDto.class);
        try {
            ObjectMapper mapper = new ObjectMapper();
            dto.setInfo(mapper.readTree(formInfo.getInfo()));
        } catch (IOException e) {

        }
        return dto;
    }

}
