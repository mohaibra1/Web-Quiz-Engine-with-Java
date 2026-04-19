package engine.controller;

import engine.model.AppUser;
import engine.repository.AppUserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "/api/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request){
        if(appUserRepository.findAppUserByUsername(request.email).isPresent()){
            return ResponseEntity.badRequest().build();
        }
        var user =  new AppUser();
        user.setUsername(request.email);
        user.setPassword(passwordEncoder.encode(request.password));
        user.setAuthority("ROLE_USER");

        appUserRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/test")
    public String test() {
        return "Access to '/test' granted";
    }

    record RegistrationRequest(@NotBlank @Pattern(regexp = ".+@.+\\..+") String email,
                               @NotBlank @Size(min = 5) String password,
                               String authority) { }
}
