package com.goit.finalproject.user;

import com.goit.finalproject.role.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final String TEST_USERNAME = "test@gmail.com";

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void loadUserByUsername() {
    //GIVEN
    User user = createTestAccount();
    UserDto expectedAccount = createTestAccountDto(user);
    //WHEN
            Mockito.when(userRepository.findUserByUsername(TEST_USERNAME)).thenReturn(user);
            Mockito.when(userMapper.mapEntityToDto(user)).thenReturn(expectedAccount);
    //THEN
    UserDto actualUserDto = userService.loadUserByUsername(TEST_USERNAME);
    Assertions.assertEquals(expectedAccount.getUsername(), actualUserDto.getUsername());
}

    private UserDto createTestAccountDto(User accountEntity) {
        UserDto dto = new UserDto();
        dto.setUsername(accountEntity.getUsername());
        return dto;
    }

    private User createTestAccount() {
        User accountEntity = new User();
        accountEntity.setId(1L);
        accountEntity.setUsername(TEST_USERNAME);
        accountEntity.setPassword("testPassword");
        Role userRole = new Role(1L, "USER", new ArrayList<>());
        accountEntity.setRoles(new HashSet<Role>(Collections.singletonList(userRole)));
        return accountEntity;
    }
}
