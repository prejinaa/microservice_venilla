package com.example.UserSecurity.service;


import com.example.UserSecurity.dto.UserRequest;
import com.example.UserSecurity.dto.UserResponse;
import com.example.UserSecurity.model.User;
import com.example.UserSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final WebClient.Builder webClientBuilder;
    private final ModelMapper modelMapper;

    public UserResponse createUser(UserRequest userRequest) {
        User createUser = modelMapper.map(userRequest, User.class);
        createUser.setPassword(encryptPassword(userRequest.getPassword()));
        User savedUser = userRepository.save(createUser);
        createAccountForUser(savedUser);
        UserResponse userResponse = modelMapper.map(savedUser, UserResponse.class);
        userResponse.setMessage("User created successfully");
        return userResponse;
    }

    private String encryptPassword(String password) {
        // Encrypt the password here (e.g., using BCryptPasswordEncoder)
        return password; // Replace this with actual encryption logic
    }

    private void createAccountForUser(User user) {
        AccountCreationRequest accountRequest = new AccountCreationRequest(user.getId(), user.getUsername());

        webClientBuilder.build()
                .post()
                .uri("http://localhost:8082/api/accounts") // AccountService URL
                .bodyValue(accountRequest)
                .retrieve()
                .bodyToMono(Void.class)
                .block(); // Blocking call for simplicity
    }
}
