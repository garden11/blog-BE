package com.name.blog.provider.useCase;

import com.name.blog.web.dto.*;

import jakarta.transaction.Transactional;
import java.util.Map;

public interface AuthUseCase {
    Map<String, Object> signIn(SignInRequestDTO signInRequestDTO);

    void signUp(SignUpRequestDTO signUpRequestDTO);

    Map<String, Object> reissueAuthTokens(ReissueAuthTokensRequestDTO reissueAuthTokensRequestDTO);

    void withdrawal(String username);

    void updatePassword(UpdatePasswordRequestDTO updatePasswordRequestDTO);

    void resetPassword(ResetPasswordRequestDTO resetPasswordRequestDTO);

    void sendResetPasswordMail(EmailRequestDTO emailRequestDTO);
}
