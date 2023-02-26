package com.name.blog.web;

import com.name.blog.core.security.Auth;
import com.name.blog.core.security.Role;
import com.name.blog.provider.service.AuthService;
import com.name.blog.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/v1/sign-in")
    public Map<String, Object> signIn(@RequestBody SignInRequestDTO signInRequestDTO) {
        return authService.signIn(signInRequestDTO);
    }

    @PostMapping("/api/v1/sign-up")
    public void signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        authService.signUp(signUpRequestDTO);

        return;
    }

    @PostMapping("/api/v1/reissue-tokens")
    public Map<String, Object> reissueAuthTokens(@RequestBody ReissueAuthTokensRequestDTO reissueAuthTokensRequestDTO) {
        return authService.reissueAuthTokens(reissueAuthTokensRequestDTO);
    }

    @DeleteMapping("/api/v1/user/{username}/withdrawal")
    @Auth(roles = {Role.USER})
    public void withdrawal(@PathVariable("username") String username) {
        authService.withdrawal(username);
    }

    @PostMapping("/api/v1/update-password")
    @Auth(roles = {Role.USER})
    public void updatePassword(@RequestBody UpdatePasswordRequestDTO updatePasswordRequestDTO) {
        authService.updatePassword(updatePasswordRequestDTO);
    }

    @PostMapping("/api/v1/reset-password")
    public void resetPassword(@RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO) {
        authService.resetPassword(resetPasswordRequestDTO);
    }

    @PostMapping("/api/v1/send-reset-password-mail")
    public void sendResetPasswordMail(@RequestBody EmailRequestDTO emailRequestDTO) {
        authService.sendResetPasswordMail(emailRequestDTO);
    }
}
