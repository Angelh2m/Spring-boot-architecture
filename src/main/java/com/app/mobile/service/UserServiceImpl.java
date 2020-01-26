

package com.app.mobile.service;

import com.app.mobile.dto.UserDto;
import com.app.mobile.entity.UserEntity;

import com.app.mobile.interfaces.UserService;
import com.app.mobile.repository.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) {

        UserEntity storedUserDetails = userRepository.findByEmail(user.getEmail());
        if (storedUserDetails != null) {
            throw new RuntimeException("Record already exist");
        }
        
        UserEntity userEntity = new UserEntity();

        BeanUtils.copyProperties(user, userEntity);

        userEntity.setEncryptedPassword("test");
        userEntity.setUserId("123");
        
        UserEntity storedUserDetatils = userRepository.save(userEntity);
        
        UserDto returnValue = new UserDto();
        
        BeanUtils.copyProperties(storedUserDetatils, returnValue);


        return returnValue;
    }

    
}