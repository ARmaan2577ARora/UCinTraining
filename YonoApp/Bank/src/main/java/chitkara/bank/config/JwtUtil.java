package chitkara.bank.config;

import java.util.Base64;

public class JwtUtil {

    public static String generateToken(String username) {
        return Base64.getEncoder().encodeToString(username.getBytes());
    }

    public static String extractUsername(String token) {
        return new String(Base64.getDecoder().decode(token));
    }
}