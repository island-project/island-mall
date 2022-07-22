package org.chenzx.island.common.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.chenzx.island.action.security.exception.InvalidTokenException;
import org.chenzx.island.action.security.exception.TokenExpiredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Map;

/**
 * @author 陈泽宣
 * @version 1.0
 * @description JWT 工具类
 * @date 2022/7/9 16:05
 */
@Slf4j
@Component
public class JwtUtils {

    private final static String KEY = "data";
    @Value("${jwt.signature-key}")
    private String signatureKey;
    private JWTSigner signer;

    @PostConstruct
    public void init() {
        this.signer = JWTSignerUtil.hs512(signatureKey.getBytes());
    }

    /**
     * 生成jwt token
     *
     * @param obj  jwt中携带的数据
     * @param hour 过期时间(小时)
     * @return jwt token
     */
    public String getJwtToken(Object obj, Integer hour) {
        Date now = new Date();
        Date expirationTime = DateUtil.offset(now, DateField.HOUR, hour);
        Map<String, Object> headers = Maps.newConcurrentMap();
        headers.put("iss", "izhangxr.cn");
        headers.put("sub", "login token");
        headers.put("jti", IdUtil.randomUUID());
        Map<String, Object> payload = Maps.newHashMap();
        payload.put("nbf", now);
        payload.put("iat", now);
        payload.put("exp", expirationTime);
        payload.put(KEY, obj);
        String token = JWTUtil.createToken(headers, payload, signer);
        log.debug("token ---> {}", token);
        return token;
    }

    /**
     * 解析token
     * 该方法不提供校验token功能如需要校验请使用checkToken方法
     *
     * @param token jwt token
     * @return 解析后的对象
     */
    public Map<String, Object> parseToken(String token) {
        Map<String, Object> data = BeanUtil.beanToMap(JWTUtil.parseToken(token).getPayload(KEY));
        if (data == null) {
            throw new ValidateException("token无效");
        }
        return data;
    }

    /**
     * 检查jwt token时间、签名
     *
     * @param token jwt
     * @return token合法返回true, 否则false
     */
    public Boolean checkToken(String token) {
        if (StrUtil.isEmpty(token)) {
            return false;
        }
        try {
            JWTValidator.of(token).validateDate(DateUtil.date()).validateAlgorithm(signer);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * 校验token,该方法只能用在Spring Security校验用户权限时,禁止其他用法
     *
     * @param token jwt token
     * @throws AccessDeniedException 用户token无效时,例如签名不正确,token为空
     * @throws TokenExpiredException token过期时
     */
    public void securityCheckToken(String token) throws AccessDeniedException, TokenExpiredException {
        if (StrUtil.isBlank(token)) {
            throw new InvalidTokenException(null);
        }
        JWTValidator jwtValidator;
        try {
            jwtValidator = JWTValidator.of(token);
        } catch (Exception e) {
            throw new InvalidTokenException(null);
        }
        try {
            // 校验token签名
            jwtValidator.validateAlgorithm(this.signer);
        } catch (Exception e) {
            throw new InvalidTokenException(null);
        }
        try {
            // 校验token时间
            jwtValidator.validateDate(DateUtil.date());
        } catch (Exception e) {
            throw new TokenExpiredException(null);
        }
    }

}
