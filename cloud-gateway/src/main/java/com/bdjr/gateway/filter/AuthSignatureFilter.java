package com.bdjr.gateway.filter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.gateway.filter、
 * @email: cy880708@163.com
 * @date: 2019/2/14 上午10:42
 * @mofified By:
 */
@Component
public class AuthSignatureFilter implements GlobalFilter, Ordered {

    private static final Log logger = LogFactory.getLog(AuthSignatureFilter.class);

    private static String POST_RES = "POST";

    private static String GET_RES = "GET";

    /**
     * 全局过滤器：拦截请求，获取authToken，并校验，设置
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        logger.info("请求信息 ： {" + JSONArray.toJSONString( exchange.getRequest()) + "}" );

        //判断请求来源
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String resType = serverHttpRequest.getMethodValue();

        InetAddress inetAddress = serverHttpRequest.getRemoteAddress().getAddress();
        String hostName = serverHttpRequest.getRemoteAddress().getHostName();
        String hostString = serverHttpRequest.getRemoteAddress().getHostString();
        int port = serverHttpRequest.getRemoteAddress().getPort();

        logger.info("inetAddress :" + inetAddress);
        logger.info("hostName :" + hostName);
        logger.info("hostString :" + hostString);
        logger.info("port :" + port);

        if (POST_RES.equals(resType)) {
            MultiValueMap<String, String> params = exchange.getRequest().getQueryParams();
            //token
            String token = params.getFirst("authToken");

            //设备ID
            String deviceId = params.getFirst("deviceId");

            //请求ID
            String requestId = params.getFirst("requestId");

            //请求方法（具体的Controller）
            String method = params.getFirst("method");

            //请求参数
            String values = params.getFirst("value");

            //签名
            String sign = params.getFirst("sign");

            // 校验 Token
            if (!checkToken(token)) {
                JSONObject message = new JSONObject();
                message.put("status", HttpStatus.UNAUTHORIZED);
                message.put("data", "鉴权失败");
                byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bits);
                //指定编码，否则在浏览器中会中文乱码
                exchange.getResponse().getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
                //当请求不携带Token或者token为空时，直接设置请求状态码为401，返回
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().writeWith(Mono.just(buffer));
            }

            // 解密请求参数

            // 重定向到对应方法

            // 向headers中放信息，记得build
            ServerHttpRequest host = exchange.getRequest().mutate().header("memberIp", hostName).build();
            logger.info("exchange.getRequest().mutate().toString() : " + exchange.getRequest().mutate().toString());
            ServerWebExchange build = exchange.mutate().request(host).build();

            return chain.filter(build);
        } else
        if(GET_RES.equals(resType)) {
            Map requestQueryParams = serverHttpRequest.getQueryParams();
            //TODO 得到Get请求的请求参数后，做你想做的事

            return chain.filter(exchange);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -200;
    }

    /**
     * @description：校验token
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/19 下午2:32
     * @mofified By:
     */
    public boolean checkToken(String token) {
        if(token == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @description：解密信息
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/19 下午3:29
     * @mofified By:
     */
    private Map<String, Object> decodeBody(String body) {
        return Arrays.stream(body.split("&"))
                .map(s -> s.split("="))
                .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
    }

    /**
     * @description：加密信息
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/19 下午6:29
     * @mofified By:
     */
    private String encodeBody(Map<String, Object> map) {
        return map.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining("&"));
    }


}
