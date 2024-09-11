package com.aurionpro.mappings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.aurionpro.mappings.entity.Captcha;

@Repository
public interface CaptchaRepository extends JpaRepository<Captcha, Long> {
    Captcha findByCaptchaId(String captchaId);
}
