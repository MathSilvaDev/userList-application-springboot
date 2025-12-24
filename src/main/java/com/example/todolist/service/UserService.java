package com.example.todolist.service;

import com.example.todolist.exception.EmailAlreadyExistsException;
import com.example.todolist.exception.IdNotFoundException;
import com.example.todolist.dto.request.UserRequestDTO;
import com.example.todolist.dto.response.UserResponseDTO;
import com.example.todolist.entities.User;
import com.example.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //GET ALL
    public List<UserResponseDTO> findAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    //GET BY ID
    public UserResponseDTO findUserById(UUID id){
        User user = getUserById(id);
        return toResponseDTO(user);
    }

    //POST
    public UserResponseDTO createUser(UserRequestDTO dto){

        Optional<User> userOpt = userRepository.findUserByEmail(dto.getEmail());
        userOpt.ifPresent((_) -> {
            throw new EmailAlreadyExistsException();
        });

        User user = new User(
                dto.getEmail(),
                dto.getUserName(),
                dto.getPassword()
        );

        userRepository.save(user);
        return toResponseDTO(user);
    }

    //PUT
    @Transactional
    public UserResponseDTO updateUserById(UUID id, UserRequestDTO dto){
        User user = getUserById(id);
        user.update(dto);

        return toResponseDTO(user);
    }

    //DELETE
    public void deleteUserById(UUID id){
        getUserById(id);
        userRepository.deleteById(id);
    }

    //METHODS
    private UserResponseDTO toResponseDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getUserName(),
                user.getPassword(),
                user.getCreatedAt()
        );
    }

    private User getUserById(UUID id){
        return userRepository
                .findById(id)
                .orElseThrow(IdNotFoundException::new);
    }
}
