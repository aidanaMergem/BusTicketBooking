package com.busticketbooking.service.impl;

import com.busticketbooking.DTO.UserDTO;
import com.busticketbooking.exception.ResourceNotFoundException;
import com.busticketbooking.model.User;
import com.busticketbooking.repository.UserRepository;
import com.busticketbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", (long) id));
        return convertToUserDTO(user);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToUser(userDTO);
        user = userRepository.save(user);
        return convertToUserDTO(user);
    }

    @Override
    public UserDTO updateUser(int id, UserDTO updatedUserDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", (long) id));
        existingUser.setEmail(updatedUserDTO.getEmail());
        existingUser.setPassword(updatedUserDTO.getPassword());
        existingUser.setPhoneNumber(updatedUserDTO.getPhoneNumber());
        existingUser.setModifiedAt(LocalDateTime.now());
        existingUser = userRepository.save(existingUser);

        return convertToUserDTO(existingUser);
    }

    @Override
    public void deleteUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", (long) id));
        userRepository.delete(user);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public Page<UserDTO> getAllUsers(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<User> users = userRepository.findAll(pageable);
        return users.map(this::convertToUserDTO);
    }

    // Conversion methods
    private  UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        return userDTO;
    }

    private static User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }
}
