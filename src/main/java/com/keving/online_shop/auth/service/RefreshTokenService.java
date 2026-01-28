package com.keving.online_shop.auth.service;

import com.keving.online_shop.auth.model.RefreshToken;
import com.keving.online_shop.auth.repository.RefreshTokenRepository;
import com.keving.online_shop.exception.BusinessException;
import com.keving.online_shop.user.model.User;
import com.keving.online_shop.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService {
    @Value("${security.jwt.refresh-expiration-time}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshToken createRefreshToken(User user){
        refreshTokenRepository.deleteByUser(user);

        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        token.setToken(UUID.randomUUID().toString());

        return refreshTokenRepository.save(token);
    }

    public void verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().isBefore(Instant.now())){
            refreshTokenRepository.delete(token);
            throw new BusinessException("Refresh token expired. Please login again", HttpStatus.UNAUTHORIZED.value());
        }
    }

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }


}
