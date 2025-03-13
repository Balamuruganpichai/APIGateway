package com.citpl.validation.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;


@Component
public class JwtUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 12L * 60L * 60L * 1000L;
//    public static final long JWT_TOKEN_VALIDITY = 5L * 60L * 1000L;

    public static final long JWT_REFRESH_TOKEN_VALIDITY = 13L * 60L * 60L * 1000L;

    @Value("${jwt.secret}")
    public String secretKeyString;

    private SecretKey secretKey;

    @PostConstruct
    public void init() throws NoSuchAlgorithmException {
        if (secretKeyString == null || secretKeyString.isEmpty()) {
            throw new IllegalArgumentException("Secret key is not defined in application.properties.");
        }
        byte[] keyBytes = secretKeyString.getBytes();
        if (keyBytes.length < 32) {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            keyBytes = sha256.digest(keyBytes);
        }
        secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

//    @Autowired
//    private Authservice accessTokenService;

    // retrieve username from jwt token
//    public String getUsernameFromToken(String token) {
//        return getClaimFromToken(token, Claims::getSubject);
//    }

//    public String getId(String token) {
//        return extractValueFromTokenForUserId(token, Constants.USERID,secret);
//    }
//    private String extractValueFromTokenForUserId(String token, String id, String secret) {
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get(id).toString();
//    }

//    private String extractValueFromToken(String token, String mobile, String secret) {
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get(mobile).toString();
//    }

//    public Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }

//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }

//    public Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }

    // validate token
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = getEmail(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }


//    public Boolean validateToken(String token, UserDetails userDetails) {
//        if (accessTokenService.isTokenBlacklisted(token)) {
//            return false;
//        }
//
//        final String username = getEmail(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }

//    public String getAccessToken(Map<String, Object> claims, String userName) {
//        try {
//            return Jwts.builder()
//                    .setClaims(claims)
//                    .setSubject(userName)
//                    .setIssuedAt(new Date(System.currentTimeMillis()))
//                    .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
//
//                    .signWith(SignatureAlgorithm.HS256, secret).compact();
//        } catch (Exception e) {
//            throw new CustomException(e.getMessage());
//        }
//    }


//    public UsernamePasswordAuthenticationToken getAuthentication(final UserDetails userDetails) {
//
//        final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
//    }

//    public String generateToken(User user, HttpSession session) {
//        Map<String, Object> claims = new HashMap<>();
//        try {
//            claims.put("email", AESUtils.encrypt(user.getUserEmailID()));
//            claims.put("mobile", AESUtils.encrypt(user.getMobile()));
//            claims.put("id", user.getId());
//            claims.put("roll", user.getUserType());
//        } catch (Exception e) {
//            throw new CustomException("Error encrypting claims: " + e.getMessage());
//        }
//
//        session.setAttribute("getId", user.getId());
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(user.getUserEmailID())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//    }

//    public String refreshToken(String token, User user) {
//        final Claims claims = extractAllClaims(token);
//        claims.setIssuedAt(new Date(System.currentTimeMillis()));
//        claims.setExpiration(new Date(System.currentTimeMillis() + JWT_REFRESH_TOKEN_VALIDITY));
//        try {
//            claims.put("email", AESUtils.encrypt(user.getUserEmailID()));
//            claims.put("mobile", AESUtils.encrypt(user.getMobile()));
//            claims.put("id", user.getId());
//        } catch (Exception e) {
//            throw new CustomException("Error encrypting claims: " + e.getMessage());
//        }
//        return Jwts.builder()
//                .setClaims(claims)
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//    }

//    public String getEmail(String token) {
//        try {
//            return AESUtils.decrypt(extractValueFromToken(token, "email"));
//        } catch (Exception e) {
//            throw new CustomException("Error decrypting email: " + e.getMessage());
//        }
//    }

//    public String getMobileNo(String token) {
//        try {
//            return AESUtils.decrypt(extractValueFromToken(token, "mobile"));
//        } catch (Exception e) {
//            throw new CustomException("Error decrypting mobile number: " + e.getMessage());
//        }
//    }

//    private String extractAllClaims(String token) {
//        System.out.println("token: "+token);
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("roles").toString();
//    }

//    public String extractValueFromToken(String token) {
//        System.out.println("token: "+token);
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("roles").toString();
//        //final Claims claims = extractAllClaims(token);
//        //return claims.get(key).toString();
//    }
//    public Claims parseJwt(String token) {
//        try {
//            return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get(key, String.class);
//
//        } catch (Exception e) {
//            throw new RuntimeException("Invalid JWT Token: " + e.getMessage());
//        }
//    }
//
//    public List<String> getRolesFromToken(String token) {
//        Claims claims = parseJwt(token);
//        return claims.get("roles", List.class);
//    }
    public List<String> extractValueFromToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey) // Ensure you're using the correct secret key
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("roles", List.class); // Extract roles as List
    }


//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
//    }
public String generateTestJwt() {
    Map<String, Object> claims = new HashMap<>();
    claims.put("roles", Arrays.asList("ADMIN", "USER"));
   // claims.put("roles", Arrays.asList("ADMIN"));

    return Jwts.builder()
            .setClaims(claims)
            .setSubject("testUser")
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiry
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
}

}
