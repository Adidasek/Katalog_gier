package pl.sklep.kataloggier.controller;

import pl.sklep.kataloggier.model.User;
import pl.sklep.kataloggier.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password
    ) {

        User user = new User();

        user.setUsername(username);
        user.setPassword(
                passwordEncoder.encode(password)
        );

        user.setRole(User.Role.USER);

        userRepository.save(user);

        return "redirect:/login";
    }
}

