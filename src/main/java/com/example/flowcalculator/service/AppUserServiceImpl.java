package com.example.flowcalculator.service;

import com.example.flowcalculator.data.dto.AppDto;
import com.example.flowcalculator.data.dto.CreateRequest;
import com.example.flowcalculator.data.dto.TokenResponseDto;
import com.example.flowcalculator.data.model.AppUser;
import com.example.flowcalculator.data.repository.AppUserRepository;
import com.example.flowcalculator.exceptiom.InvalidLoginDetailsException;
import com.example.flowcalculator.exceptiom.UnauthorizedRequestException;
import com.example.flowcalculator.exceptiom.UsernameAlreadyExistsException;
import com.example.flowcalculator.security.AuthenticationUser;
import com.example.flowcalculator.security.JwtGenerator;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.flowcalculator.data.dto.Message.CREATED;
import static com.example.flowcalculator.data.dto.Message.LOGIN_SUCCESSFULLY;


@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService{

    private final AppUserRepository repository;
    private final JwtGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final ModelMapper mapper;
    @Override
    public TokenResponseDto creatUser(CreateRequest request) {
        if (repository.existsByUsername(request.getUsername())){
            throw new UsernameAlreadyExistsException(request.getUsername()+" is not available");
        }
        AppUser user = AppUser.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .age(getAge(request.getBirthDate()))
                .createdAt(LocalDate.now())
                .build();
        AppUser savedUser = repository.save(user);
        String token = jwtGenerator.generateToken(savedUser);
        return TokenResponseDto.builder().token("Bearer "+ token).build();
    }
    private int getAge(String dob){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dob, formatter);
//        DayOfWeek dayOfWeek = date.getDayOfWeek();
        LocalDate currentDate = LocalDate.now();

        return Period.between(date,currentDate).getYears();

    }

    private AppDto getCreationResponse(AppUser savedUser) {
        return AppDto.builder()
                .id(savedUser.getId())
                .message(CREATED)
                .success(true)
                .code(HttpStatus.CREATED.value())
                .build();
    }

    @Override
    public TokenResponseDto login(@NotBlank(message = "Username is required") String username,
                                  @NotBlank(message = "password is required") String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtGenerator.generateToken(authentication);
            return TokenResponseDto.builder().token("Bearer "+ token).build();
        }catch (Exception e) {
            throw new InvalidLoginDetailsException("invalid login details");
        }
    }
    @Override
    public AppDto logout() {
        return loginResponse();
    }

    @Override
    public AppUser getAuthenticatedUser() {
        try {
            AuthenticationUser authUser =
                    (AuthenticationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (authUser == null) {
                throw new UnauthorizedRequestException("");
            }
            return (AppUser) repository.findAppUserByUsername(authUser.getUsername()).orElseThrow();
        } catch (Exception e) {
            throw new UnauthorizedRequestException("Unauthorized request");
        }
    }
    @Override
    public List<AppUser> getAllUsers() {
        return repository.findAll();
    }

    private AppDto loginResponse() {
        return AppDto.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message(LOGIN_SUCCESSFULLY).build();
    }

    @Override
    public AppUser findByUsername(String username) {
        return repository.findUserByUsername(username);
    }
}
