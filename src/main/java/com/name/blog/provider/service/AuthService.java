package com.name.blog.provider.service;

import com.name.blog.constants.Retentions;
import com.name.blog.core.entity.MailProcess;
import com.name.blog.core.entity.Profile;
import com.name.blog.core.entity.User;
import com.name.blog.core.repository.MailProcessRepository;
import com.name.blog.core.repository.ProfileRepository;
import com.name.blog.core.repository.UserRepository;
import com.name.blog.core.security.Role;
import com.name.blog.exception.*;
import com.name.blog.provider.dto.UserDTO;
import com.name.blog.provider.security.*;
import com.name.blog.provider.useCase.AuthUseCase;
import com.name.blog.util.DateUtil;
import com.name.blog.util.EmailSender;
import com.name.blog.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {
    private static final String USER_INFO_KEY = "userInfo";
    private static final String AUTH_TOKENS_KEY = "tokens";
    private static final String ACCESS_TOKEN_KEY = "accessToken";
    private static final String ACCESS_TOKEN_EXPIRES_AT_KEY = "accessTokenExpiresAt";
    private static final String REFRESH_TOKEN_KEY = "refreshToken";

    private final UserRepository userRepository;
    private final MailProcessRepository mailProcessRepository;
    private final ProfileRepository profileRepository;

    private final AccessTokenProvider accessTokenProvider;
    private final RefreshTokenProvider refreshTokenProvider;
    private final ProcessTokenProvider processTokenProvider;

    private final DateUtil dateUtil = new DateUtil();
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final EmailSender emailSender;

    @Override
    @Transactional
    public Map<String, Object> signIn(SignInRequestDTO signInRequestDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<User> optionalUser = userRepository.findByUsername(signInRequestDTO.getUsername());

            if(!(optionalUser.isPresent())) {
                throw new Exception("USER_DOES_NOT_EXIST.");
            }

            User user = optionalUser.get();

            if(!(encoder.matches(signInRequestDTO.getPassword(), user.getPassword()))) {
                throw new Exception("USERNAME_AND_PASSWORD_DO_NOT_MATCH.");
            }

            AccessToken accessToken = accessTokenProvider.createToken(user.getUsername(), Role.of(user.getRole()));
            RefreshToken refreshToken = refreshTokenProvider.createToken();

            Date accessTokenExpiredDate = accessToken.getExpiredDate();
            Long accessTokenExpiresAt = dateUtil.convertToEpochSecond(accessTokenExpiredDate);
            Date refreshTokenExpiredDate = dateUtil.createUTCDatePlus(Retentions.REFRESH_TOKEN_DAYS.getValue(), ChronoUnit.DAYS);
            Long refreshTokenExpiresAt = dateUtil.convertToEpochSecond(refreshTokenExpiredDate);

            user.updateRefreshToken(refreshToken.getToken(), refreshTokenExpiresAt);

            UserDTO userDTO = UserDTO.of(userRepository.save(user));

            Map<String, Object> authTokens = new HashMap<>();
            authTokens.put(ACCESS_TOKEN_KEY, accessToken.getToken());
            authTokens.put(ACCESS_TOKEN_EXPIRES_AT_KEY, String.valueOf(accessTokenExpiresAt));
            authTokens.put(REFRESH_TOKEN_KEY, user.getRefreshToken());

            response.put(USER_INFO_KEY, userDTO);
            response.put(AUTH_TOKENS_KEY, authTokens);

            return response;
        } catch (Exception error) {
            error.printStackTrace();

            throw new SignInFailedException();
        }
    }

    @Override
    @Transactional
    public void signUp(SignUpRequestDTO signUpRequestDTO) {
        try {
            signUpRequestDTO.setPassword(encoder.encode(signUpRequestDTO.getPassword()));

            userRepository.save(signUpRequestDTO.toEntity());
            profileRepository.save(
                    Profile.builder()
                    .username(signUpRequestDTO.getUsername())
                    .build()
            );

            return;
        } catch (Exception error) {
            error.printStackTrace();

            throw new SignUpFailedException();
        }
    }

    @Override
    @Transactional
    public Map<String, Object> reissueAuthTokens(ReissueAuthTokensRequestDTO reissueAuthTokensRequestDTO) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<User> optionalUser = userRepository.findByRefreshToken(reissueAuthTokensRequestDTO.getRefreshToken());
            if(!(optionalUser.isPresent())) {
                throw new Exception("REFRESH_TOKEN_DOES_NOT_EXIST.");
            }
            User user = optionalUser.get();

            if(!(dateUtil.isValid(user.getRefreshTokenExpiresAt()))) {
                throw new Exception("REFRESH_TOKEN_HAS_EXPIRED.");
            }

            AccessToken newAccessToken = accessTokenProvider.createToken(user.getUsername(), Role.of(user.getRole()));
            RefreshToken newRefreshToken = refreshTokenProvider.createToken();

            Date refreshTokenExpiredDate = dateUtil.createUTCDatePlus(Retentions.REFRESH_TOKEN_DAYS.getValue(),ChronoUnit.DAYS);
            Long refreshTokenExpiresAt = dateUtil.convertToEpochSecond(refreshTokenExpiredDate);

            user.updateRefreshToken(newRefreshToken.getToken(), refreshTokenExpiresAt);

            userRepository.save(user);

            response.put(ACCESS_TOKEN_KEY, newAccessToken.getToken());
            response.put(ACCESS_TOKEN_EXPIRES_AT_KEY, String.valueOf(dateUtil.convertToEpochSecond(newAccessToken.getExpiredDate())));
            response.put(REFRESH_TOKEN_KEY, user.getRefreshToken());

            return response;
        } catch (Exception error) {
            error.printStackTrace();

            throw new ReissueAuthTokensFailedException();
        }
    }

    @Override
    @Transactional
    public void withdrawal(String username) {
        try {
            Optional<User> optionalUser = userRepository.findByUsername(username);

            if(!(optionalUser.isPresent())) {
                throw new Exception("USER_DOES_NOT_EXIST.");
            }

            User user = optionalUser.get();
            userRepository.updateDeletingById(user.getId());
        } catch(Exception error) {
            error.printStackTrace();

            throw new WithdrawalFailedException();
        }
    }

    @Override
    @Transactional
    public void updatePassword(UpdatePasswordRequestDTO updatePasswordRequestDTO) {
        try {
            Optional<User> optionalUser = userRepository.findByUsername(updatePasswordRequestDTO.getUsername());

            if(!(optionalUser.isPresent())) {
                throw new Exception("USER_DOES_NOT_EXIST.");
            }

            User user = optionalUser.get();

            if(!(encoder.matches(updatePasswordRequestDTO.getPassword(), user.getPassword()))) {
                throw new Exception("USERNAME_AND_PASSWORD_DO_NOT_MATCH.");
            }

            user.updatePassword(encoder.encode(updatePasswordRequestDTO.getNewPassword()));
            userRepository.save(user);

        }catch (Exception error) {
            error.printStackTrace();

            throw new UserUpdateFailedException();
        }
    }

    @Override
    @Transactional
    public void resetPassword(ResetPasswordRequestDTO resetPasswordRequestDTO) {
        try {
            ProcessToken processToken = processTokenProvider.convertToToken(resetPasswordRequestDTO.getProcessToken());

            Optional<MailProcess> optionalProcess = mailProcessRepository.findByProcessToken(processToken.getToken());

            if(!(optionalProcess.isPresent())) {
                throw new Exception("TOKEN_DOES_NOT_EXIST.");
            }

            MailProcess mailProcess = optionalProcess.get();

            if(mailProcess.getProcessYN().equals("Y")) {
                throw new Exception("TOKEN_ALREADY_USED.");
            }

            if(!dateUtil.isValid(mailProcess.getExpiresAt())) {
                throw new Exception("TOKEN_HAS_EXPIRED.");
            }

            Optional<User> optionalUser = userRepository.findByEmail(mailProcess.getEmail());

            if(!(optionalUser.isPresent())){
                throw new Exception("USER_DOES_NOT_EXIST.");
            }

            User user = optionalUser.get();
            user.updatePassword(encoder.encode(resetPasswordRequestDTO.getNewPassword()));
            userRepository.save(user);

            mailProcessRepository.updateProcessingById(mailProcess.getId());
        } catch (Exception error) {
            error.printStackTrace();

            throw new ResetPasswordFailedException();
        }
    }

    @Override
    @Transactional
    public void sendResetPasswordMail(EmailRequestDTO emailRequestDTO) {
        try {
            ProcessToken processToken = processTokenProvider.convertToToken(emailRequestDTO.getProcessToken());

            mailProcessRepository.updateProcessingByEmail(emailRequestDTO.getTo());

            MailProcess mailProcess = MailProcess.builder()
                    .email(emailRequestDTO.getTo())
                    .processToken(processToken.getToken())
                    .build();

            mailProcessRepository.save(mailProcess);

            emailSender.send(mailProcess.getEmail(), emailRequestDTO.getSubject(), emailRequestDTO.getMessage());
        }catch (Exception error) {
            error.printStackTrace();

            throw new MailSendFailedException();
        }
    }
}
