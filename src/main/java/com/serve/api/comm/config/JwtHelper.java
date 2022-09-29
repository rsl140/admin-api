package com.serve.api.comm.config;

import com.serve.api.comm.enums.ErrorCode;
import com.serve.api.comm.model.BusinessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Base64;
import java.util.Date;


@Slf4j
@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtHelper {
    private String name;
    private String clientId;
    private String secret;
    /**
     * 毫秒,1个月
     */
    public String expiretime;
    private static String jwtsecret = "xxxx";
    private static String jwtname = "xxxxx";
    private static String jwtclientId = "xxxx";
    /**
     * 毫秒,1个月
     */
    public static long expirestime = 2592000000L;

    @PostConstruct
    public void init() {
        jwtname = name;
        jwtclientId = clientId;
        jwtsecret = secret;
        expirestime = 2592000000L;
    }

    /**
     * 解析jwt
     */
    public static Claims parseJWT(String token) {
        try {
            if (StringUtils.isEmpty(token)) {
                throw BusinessException.instance(ErrorCode.INVALID_TOKEN);
            }
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(getBase64Secret()))
                    .parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception ex) {
            log.error("解析token出错，token:{},原因:{}", token, ex.getMessage());
            throw new BusinessException(ErrorCode.INVALID_TOKEN);
        }
    }

    /**
     * 构建jwt
     */
    public static String createJWT(String userId) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(getBase64Secret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        // 添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("userId", userId)
                .setIssuer(jwtname)
                .setAudience(jwtclientId)
                .signWith(signatureAlgorithm, signingKey);
        // 添加Token过期时间
        Long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (expirestime >= 0) {
            Long expMillis = nowMillis + expirestime;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        // 生成JWT
        return /*BEARER+*/builder.compact();
    }


    private static String getBase64Secret() {

        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(jwtsecret.getBytes());
    }

    public static int getUserId(String token) {
        Claims claims = parseJWT(token);
        Object userInfo = claims.get("userId");
        if (userInfo == null) {
            throw BusinessException.instance(ErrorCode.INVALID_TOKEN);
        }
        int userId = Integer.parseInt(userInfo.toString());
        if (claims.getExpiration().before(new Date())) {
            throw BusinessException.instance(ErrorCode.INVALID_TOKEN);
        }
        return userId;
    }


}
