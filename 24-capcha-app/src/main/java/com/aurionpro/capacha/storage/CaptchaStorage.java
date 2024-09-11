package com.aurionpro.capacha.storage;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CaptchaStorage {

    private final Map<String, String> captchaStore = new HashMap<>();

    public void storeCaptcha(String captchaId, String captcha) {
        captchaStore.put(captchaId, captcha);
    }

    public String getCaptcha(String captchaId) {
        return captchaStore.get(captchaId);
    }

    public void removeCaptcha(String captchaId) {
        captchaStore.remove(captchaId);
    }
}