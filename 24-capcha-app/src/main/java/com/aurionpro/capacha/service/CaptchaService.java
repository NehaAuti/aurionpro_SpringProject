package com.aurionpro.capacha.service;

import org.springframework.stereotype.Service;

import com.aurionpro.capacha.entity.Captcha;
import com.aurionpro.capacha.repository.CaptchaRepository;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class CaptchaService {

    private final CaptchaRepository captchaRepository;

    public CaptchaService(CaptchaRepository captchaRepository) {
        this.captchaRepository = captchaRepository;
    }

    public void storeCaptcha(String captchaId, String inputCaptcha) {
        Captcha captcha = new Captcha();
        captcha.setCaptchaId(captchaId);
        captcha.setInputCaptcha(inputCaptcha);
        captcha.setCreatedAt(LocalDateTime.now());
        captchaRepository.save(captcha);
    }

    public Captcha getCaptcha(String captchaId) {
        return captchaRepository.findByCaptchaId(captchaId);
    }

    public void removeCaptcha(String captchaId) {
        Captcha captcha = captchaRepository.findByCaptchaId(captchaId);
        if (captcha != null) {
            captchaRepository.delete(captcha);
        }
    }
}