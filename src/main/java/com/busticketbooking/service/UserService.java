package com.busticketbooking.service;

import com.busticketbooking.DTO.UserDTO;
import org.springframework.data.domain.Page;

public interface UserService {
    UserDTO getUserById(int id);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(int id, UserDTO updatedUserDTO);
    void deleteUser(int id);
    void deleteAllUsers();
    Page<UserDTO> getAllUsers(int page, int size);
}