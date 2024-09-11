package com.aurionpro.mappings.service;

import org.springframework.stereotype.Service;


import com.aurionpro.mappings.entity.Captcha;
import com.aurionpro.mappings.repository.CaptchaRepository;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class CaptchaService {

//	private static final String CAPTCHA_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//    private static final Random RANDOM = new SecureRandom();

    private final CaptchaRepository captchaRepository;

    public CaptchaService(CaptchaRepository captchaRepository) {
        this.captchaRepository = captchaRepository;
    }

//    private static final String CAPTCHA_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//    private static final Random RANDOM = new SecureRandom();

    // Method to generate a CAPTCHA with a length between 4 to 6 characters
    public String generateCaptcha() {
        int length = 4 + new Random().nextInt(3); // Length will be either 4, 5, or 6
        String captchaChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            captcha.append(captchaChars.charAt(random.nextInt(captchaChars.length())));
        }
        return captcha.toString();
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

    public boolean validateCaptcha(String captchaId, String inputCaptcha) {
        Optional<Captcha> captchaOptional = Optional.of(captchaRepository.findByCaptchaId(captchaId));
        if (captchaOptional.isPresent()) {
            Captcha captcha = captchaOptional.get();
            return captcha.getInputCaptcha().equals(inputCaptcha);
        }
        return false;
    }

    public void removeCaptcha(String captchaId) {
        Captcha captcha = getCaptcha(captchaId);
        if (captcha != null) {
            captchaRepository.delete(captcha);
        }
    }
}