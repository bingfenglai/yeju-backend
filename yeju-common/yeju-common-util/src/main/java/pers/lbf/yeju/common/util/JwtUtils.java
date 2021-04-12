/*
 * Copyright 2020 赖柄沣 bingfengdev@aliyun.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pers.lbf.yeju.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.*;
import org.joda.time.DateTime;
import pers.lbf.yeju.common.pojo.Payload;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Json web token 工具类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/30 14:30
 */
public class JwtUtils {

    private static final String JWT_PAYLOAD_USER_KEY = "subject";

    public static String generateToken(Object userInfo, PrivateKey privateKey, int expire, TimeUnit timeUnit) throws JsonProcessingException {

        switch (timeUnit) {
            case MINUTES:
                return generateTokenExpireInMinutes(userInfo, privateKey, expire);
            case DAYS:
                return generateTokenExpireInDays(userInfo, privateKey, expire);
            case HOURS:
                return generateTokenExpireInHours(userInfo, privateKey, expire);
            case SECONDS:
            default:
                return generateTokenExpireInSeconds(userInfo, privateKey, expire);
        }


    }


    /**
     * 私钥加密token
     *
     * @param userInfo   载荷中的数据
     * @param privateKey 私钥
     * @param expire     过期时间，单位秒
     * @return JWT
     */
    public static String generateTokenExpireInSeconds(Object userInfo, PrivateKey privateKey, int expire) throws JsonProcessingException {
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY, JsonUtils.toString(userInfo))
                .setId(createJTI())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(privateKey, SignatureAlgorithm.RS512)
                .compact();
    }

    /**
     * 私钥加密token
     *
     * @param userInfo   载荷中的数据
     * @param privateKey 私钥
     * @param expire     过期时间，单位分钟
     * @return JWT
     */
    public static String generateTokenExpireInMinutes(Object userInfo, PrivateKey privateKey, int expire) throws JsonProcessingException {
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY, JsonUtils.toString(userInfo))
                .setId(createJTI())
                .setExpiration(DateTime.now().plusMinutes(expire).toDate())
                .signWith(privateKey, SignatureAlgorithm.RS512)
                .compact();
    }

    public static String generateTokenExpireInHours(Object userInfo, PrivateKey privateKey, int expire) throws JsonProcessingException {
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY, JsonUtils.toString(userInfo))
                .setId(createJTI())
                .setExpiration(DateTime.now().plusHours(expire).toDate())
                .signWith(privateKey, SignatureAlgorithm.RS512)
                .compact();
    }


    public static String generateTokenExpireInDays(Object userInfo, PrivateKey privateKey, int expire) throws JsonProcessingException {
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY, JsonUtils.toString(userInfo))
                .setId(createJTI())
                .setExpiration(DateTime.now().plusDays(expire).toDate())
                .signWith(privateKey, SignatureAlgorithm.RS512)
                .compact();

    }

    public static String generateTokenExpireInWeeks(Object userInfo, PrivateKey privateKey, int expire) throws JsonProcessingException {
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY, JsonUtils.toString(userInfo))
                .setId(createJTI())
                .setExpiration(DateTime.now().plusWeeks(expire).toDate())
                .signWith(privateKey, SignatureAlgorithm.RS512)
                .compact();

    }

    public static String generateTokenExpireInMouths(Object userInfo, PrivateKey privateKey, int expire) throws JsonProcessingException {
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY, JsonUtils.toString(userInfo))
                .setId(createJTI())
                .setExpiration(DateTime.now().plusMonths(expire).toDate())
                .signWith(privateKey, SignatureAlgorithm.RS512)
                .compact();

    }

    public static String generateTokenExpireInYears(Object userInfo, PrivateKey privateKey, int expire) throws JsonProcessingException {
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY, JsonUtils.toString(userInfo))
                .setId(createJTI())
                .setExpiration(DateTime.now().plusYears(expire).toDate())
                .signWith(privateKey, SignatureAlgorithm.RS512)
                .compact();

    }


    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥
     * @return Jws<Claims>
     */
    private static Jws<Claims> parserToken(String token, PublicKey publicKey) throws ExpiredJwtException {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    private static String createJTI() {
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @param userType
     * @return 用户信息
     */
    public static <T> Payload<T> getInfoFromToken(String token, PublicKey publicKey, Class<T> userType) throws ExpiredJwtException, JsonProcessingException {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        claims.setId(body.getId());
        claims.setUserInfo(JsonUtils.toBean(body.get(JWT_PAYLOAD_USER_KEY).toString(), userType));
        claims.setExpiration(body.getExpiration());
        return claims;
    }

    /**
     * 获取token中的载荷信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     */
    public static <T> Payload<T> getInfoFromToken(String token, PublicKey publicKey) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        claims.setId(body.getId());
        claims.setExpiration(body.getExpiration());
        return claims;
    }

    /**
     * 私有构造器
     */
    private JwtUtils() {
    }

}
