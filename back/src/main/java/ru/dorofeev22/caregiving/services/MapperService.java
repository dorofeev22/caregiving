package ru.dorofeev22.caregiving.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

    /**
     * Transform DTO to Entity
     * @param dto object
     * @param entityClass entity class object
     * @param <E> Entity class
     * @param <D> DTO class
     * @return entity object
     */
    public <E, D> E fromDto(D dto, Class<E> entityClass) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, entityClass);
    }

    /**
     * Transform Entity to DTO
     * @param entity object
     * @param dtoClass dto class object
     * @param <E> Entity class
     * @param <D> DTO class
     * @return DTO object
     */
    public <E, D> D toDto(E entity, Class<D> dtoClass) {
        ModelMapper modelMapper = new ModelMapper();
        return entity != null ? modelMapper.map(entity, dtoClass) : null;
    }

}
