package com.dmdev.http.service;

import com.dmdev.http.dao.UserDao;
import com.dmdev.http.dto.CreateUserDto;
import com.dmdev.http.dto.UserDto;
import com.dmdev.http.exception.ValidationException;
import com.dmdev.http.mapper.CreateUserMapper;
import com.dmdev.http.mapper.UserMapper;
import com.dmdev.http.validator.CreateUserValidator;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password)
                .map(userMapper::mapFrom);
    }

    @SneakyThrows
    public Integer create(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        imageService.upload(userEntity.getImage(), userDto.getImage().getInputStream());
        userDao.save(userEntity);

        return userEntity.getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
