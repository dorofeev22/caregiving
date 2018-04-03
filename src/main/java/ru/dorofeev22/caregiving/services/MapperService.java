package ru.dorofeev22.caregiving.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dorofeev22.caregiving.dtos.UserDto;
import ru.dorofeev22.caregiving.entities.User;

@Service
public class MapperService {

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        return modelMapper.map(entity, dtoClass);
    }

    /**
     * Transform User DTO to User entity with password encoding
     * @param ud
     * @return
     */
    public User fromUserDto(UserDto ud) {
        User u = fromDto(ud, User.class);
        u.setPassword(passwordEncoder.encode(ud.getPassword()));
        return u;
    }

    /**
     * Transform User entity to User DTO without password
     * @param u
     * @return
     */
    public UserDto toUserDto(User u) {
        UserDto ud = toDto(u, UserDto.class);
        ud.setPassword(null);
        return ud;
    }

}
