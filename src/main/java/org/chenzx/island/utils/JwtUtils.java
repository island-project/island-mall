package org.chenzx.island.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.core.util.IdUtil;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
        headers.put("nbf", now);
        headers.put("iat", now);
        headers.put("exp", expirationTime);
        headers.put("jti", IdUtil.randomUUID());
        Map<String, Object> payload = Maps.newHashMap();
        payload.put(KEY, obj);
        String token = JWTUtil.createToken(headers, payload, signer);
        log.debug("token ---> {}", token);
        return token;
    }

    /**
     * 检查并解析token
     *
     * @param token jwt token
     * @return 解析后的对象
     */
    public Map<String, Object> parseToken(String token) {
        if (!checkToken(token)) {
            throw new RuntimeException("token无效");
        }
        Map<String, Object> data = BeanUtil.beanToMap(JWTUtil.parseToken(token).getPayload(KEY));
        if (data == null) {
            throw new RuntimeException("token无效");
        }
        return data;
    }

    /**
     * 检查jwt token时间、签名
     *
     * @param token jwt
     * @throws ValidateException 当jwt过期、jwt签名不合法时,抛出该异常
     */
    public void checkTokenException(String token) throws ValidateException {
        JWTValidator.of(token).validateDate(DateUtil.date()).validateAlgorithm(signer);
    }

    /**
     * 检查jwt token时间、签名
     *
     * @param token jwt
     * @return token合法返回true, 否则false
     */
    public Boolean checkToken(String token) throws ValidateException {
        try {
            JWTValidator.of(token).validateDate(DateUtil.date()).validateAlgorithm(signer);
        } catch (ValidateException e) {
            return false;
        }
        return true;
    }

}
