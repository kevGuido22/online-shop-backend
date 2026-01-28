package com.keving.online_shop.auth.repository;

import com.keving.online_shop.auth.model.RefreshToken;
import com.keving.online_shop.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(User user);
}
