package com.learning.tripexpensemanagementsystem.mapper;

import com.learning.tripexpensemanagementsystem.dto.UserDto;
import com.learning.tripexpensemanagementsystem.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDto userToUserDto(User user);
}
