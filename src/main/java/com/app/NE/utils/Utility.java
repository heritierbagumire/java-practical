package com.app.NE.utils;

import com.app.NE.enums.EDeductionName;
import com.app.NE.models.Employee;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;

public class Utility {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String hash(@NotEmpty(message = "The raw string is required.") String raw) {
            return passwordEncoder.encode(raw);
    }

    public static boolean compare(String hashed, String raw) {
        return passwordEncoder.matches(raw, hashed);
    }

    public static  String formatToken(String token) {
        return token.replaceAll("(.{4})(?=.)", "$1-");
    }

    public static String generateToken() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    public static String generateEmployeeCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return "EMP-" + sb.toString();
    }

    public static String generateEmployeementCode(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return "EMPL-" + sb.toString();
    }

    public static String generateDeductionCode(EDeductionName name){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return name + "-" + sb.toString();
    }
}
