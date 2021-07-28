package com.townscript.goodreadsapi.Repository;

import com.townscript.goodreadsapi.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    Object deleteByToken(String token);
}