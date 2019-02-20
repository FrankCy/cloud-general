package com.bdjr.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.netty.buffer.ByteBufAllocator;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.gateway.filter、
 * @email: cy880708@163.com
 * @date: 2019/2/20 上午11:29
 * @mofified By:
 */
@Component
public class InjectParameterGatewayFilterFactory extends AbstractGatewayFilterFactory<InjectParameterGatewayFilterFactory.Config> {

    private static final Logger logger = LoggerFactory.getLogger(InjectParameterGatewayFilterFactory.class);

    public InjectParameterGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            return handleParameter(exchange,chain);
        };
    }

    public static class Config {
        //Put the configuration properties for your filter here
    }

    /**
     * 处理请求(添加全局类参数)
     * @param exchange
     * @param chain
     * @return
     */
    private Mono<Void> handleParameter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String method = serverHttpRequest.getMethodValue();
        String contentType = serverHttpRequest.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);
        //post请求时，如果是文件上传之类的请求，不修改请求消息体
        if (HttpMethod.POST.toString().equals(method) &&
                (MediaType.APPLICATION_FORM_URLENCODED_VALUE.equalsIgnoreCase(contentType)
                || MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(contentType))) {
            // TODO: 2018/12/21 参考api文档中GatewapFilter中“修改请求消息体拦截器”：ModifyRequestBodyGatewayFilterFactory.java
            //下面的将请求体再次封装写回到request里，传到下一级，否则，由于请求体已被消费，后续的服务将取不到值
            URI uri = serverHttpRequest.getURI();
            URI newUri = UriComponentsBuilder.fromUri(uri).build(true).toUri();
            ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri).build();
            MultiValueMap<String, String> queryParams = initParameters(request.getQueryParams());

            // 定义新的消息头
            HttpHeaders headers = new HttpHeaders();
            headers.putAll(exchange.getRequest().getHeaders());

            // 添加消息头
            headers.set("header-info","HEADER_INFO");

            // 设置CONTENT_TYPE
            if (StringUtils.isNotBlank(contentType)) {
                headers.set(HttpHeaders.CONTENT_TYPE, contentType);
            }

            // 由于post的body只能订阅一次，由于上面代码中已经订阅过一次body。所以要再次封装请求到request才行，不然会报错请求已经订阅过
            request = new ServerHttpRequestDecorator(request) {
                @Override
                public MultiValueMap<String, String> getQueryParams() {
                    return queryParams;
                }
            };

            return chain.filter(exchange.mutate().request(request).build());

        } else if (HttpMethod.GET.toString().equals(method)) {
            // 获取原参数
            URI uri = serverHttpRequest.getURI();
            StringBuilder query = new StringBuilder();
            String originalQuery = uri.getRawQuery();
            if (org.springframework.util.StringUtils.hasText(originalQuery)) {
                query.append(originalQuery);
                if (originalQuery.charAt(originalQuery.length() - 1) != '&') {
                    query.append('&');
                }
            }
            // 添加查询参数
            query.append("FIND-PARAMETER");

            // 替换查询参数
            URI newUri = UriComponentsBuilder.fromUri(uri)
                    .replaceQuery(query.toString())
                    .build(true)
                    .toUri();

            ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri).build();
            return chain.filter(exchange.mutate().request(request).build());
        }

        return chain.filter(exchange);
    }

    /**
     * 从Flux<DataBuffer>中获取字符串的方法
     * @return 请求体
     */
    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        //获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();

        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        //获取request body
        return bodyRef.get();
    }

    /**
     * 字符串转DataBuffer
     * @param value
     * @return
     */
    private DataBuffer stringBuffer(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }

    /**
     * @description：解密信息
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/20 下午1:30
     * @mofified By:
     */
    private MultiValueMap<String, String> initParameters(MultiValueMap multiValueMap) {
        String values = (String) multiValueMap.getFirst("value");
        logger.info("values : " + values);
        //解密

        //格式化数据为MultiValueMap并返回
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cName", "Frank-New-N");
        jsonObject.put("cCode", "95271");
        jsonObject.put("cDes", "描述信息信息");
        map.add("value", jsonObject.toJSONString());

        return map;
    }

    public static void main(String[] args) {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<String, String>();
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        multiValueMap.put("list",list);
        System.out.println(multiValueMap.get("list").toString());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cName", "Frank-New");
        jsonObject.put("cCode", "9527");
        jsonObject.put("cDes", "描述信息");
        System.out.println(jsonObject.toJSONString());
    }
}
