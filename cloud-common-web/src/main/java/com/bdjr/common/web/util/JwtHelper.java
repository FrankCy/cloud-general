package com.bdjr.common.web.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.Key;
import java.util.Date;


public class JwtHelper {
    private static final Logger logger = LoggerFactory.getLogger(JwtHelper.class);
    public static Claims parseJWT(String jsonWebToken, String base64Security){
        try
        {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        }
        catch(Exception ex)
        {
            logger.error("===>read jwt from request failure", ex);
            return null;
        }
    }

    public static String createJWT(String name, String userId, String role,
                                   String audience, String issuer, long TTLMillis, String base64Security)
    {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("role", role)
                .claim("unique_name", name)
                .claim("userid", userId)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        //生成JWT
        return builder.compact();
    }

    /**
     * 向http响应头中写入JWT信息
     * @param res
     * @param subject
     * @throws IOException
     * @throws ServletException
     */
    public static void wirteResponseHeader(
                                            HttpServletResponse res,

                                            String subject) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + 30L * 60L * 60L * 24L * 1000L)) // 30天
                .signWith(SignatureAlgorithm.HS512, "123456abc") //采用什么算法是可以自己选择的，不一定非要采用HS512
                .compact();
        res.addHeader("Authorization", "Bearer " + token);
    }

    /**
     * 从http请求头中读取JWT信息
     * @param request
     * @return
     */
    public static String readReqeustHreader(HttpServletRequest request) {
        try{
            String token = request.getHeader("Authorization");
            if (token != null) {
                // parse the token.
                String user = Jwts.parser()
                        .setSigningKey("123456abc")
                        .parseClaimsJws(StringUtils.substringAfter(token, "Bearer")) // StringUtils.substringAfter(token, "Bearer")
                        .getBody()
                        .getSubject();

                if (user == null) {
                    throw new RuntimeException("reqeust header no key authorization");
                }
                return user;
            }
        }catch (Exception e){
            logger.error("===>read jwt from request failure", e);
        }
        return null;
    }
}
