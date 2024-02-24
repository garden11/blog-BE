package com.name.blog.provider.useCase;

import com.name.blog.web.dto.*;

import jakarta.transaction.Transactional;
import java.util.Map;

public interface AuthUseCase {
    @Transactional
    Map<String, Object> signIn(SignInRequestDTO signInRequestDTO);

    @Transactional
    void signUp(SignUpRequestDTO signUpRequestDTO);

    @Transactional
    Map<String, Object> reissueAuthTokens(ReissueAuthTokensRequestDTO reissueAuthTokensRequestDTO);

    @Transactional
    void withdrawal(String username);

    @Transactional
    void updatePassword(UpdatePasswordRequestDTO updatePasswordRequestDTO);

    @Transactional
    void resetPassword(ResetPasswordRequestDTO resetPasswordRequestDTO);

    @Transactional
    void sendResetPasswordMail(EmailRequestDTO emailRequestDTO);
}
