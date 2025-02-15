package com.aurionpro.capacha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.capacha.entity.Captcha;

@Repository
public interface CaptchaRepository extends JpaRepository<Captcha, Long> {
    Captcha findByCaptchaId(String captchaId);
}
