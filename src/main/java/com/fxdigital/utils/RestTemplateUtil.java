package com.fxdigital.utils;

import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * rest发送http,https请求
 *
 * @author ankang
 */
@Component
public class RestTemplateUtil {

    private RestTemplate restTemplate;

    private RestTemplate httpsRestTemplate;

    private int timeout = 5000;

    private String APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";

    private String APPLICATION_XML_UTF8 = "application/xml;charset=UTF-8";

    public RestTemplateUtil() {
        restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(timeout);
        requestFactory.setConnectTimeout(timeout);
        restTemplate.setRequestFactory(requestFactory);

        httpsRestTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory requestFactoryHttps = new HttpsClientRequestFactory();
        requestFactoryHttps.setReadTimeout(timeout);
        requestFactoryHttps.setConnectTimeout(timeout);
        httpsRestTemplate.setRequestFactory(requestFactoryHttps);
    }

    private <T> T post(String url, String param, String mediaType, Class<T> bodyType) {

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType(mediaType);
        headers.setContentType(type);

        return exchange(url, param, HttpMethod.POST, bodyType, headers);
    }

    public <T> T postJson(String url, String param, Class<T> bodyType) {
        return post(url, param, APPLICATION_JSON_UTF8, bodyType);
    }

    public <T> T postXml(String url, String param, Class<T> bodyType) {
        return post(url, param, APPLICATION_XML_UTF8, bodyType);
    }

    public <T> T get(String url, String param, Class<T> bodyType) {

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType(APPLICATION_JSON_UTF8);
        headers.setContentType(type);

        return exchange(url, param, HttpMethod.GET, bodyType, headers);

    }

    public <T> T put(String url, String data, Class<T> bodyType) {

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType(APPLICATION_JSON_UTF8);
        headers.setContentType(type);

        return exchange(url, data, HttpMethod.PUT, bodyType, headers);
    }

    public <T> T delete(String url, String data, Class<T> bodyType) {

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType(APPLICATION_JSON_UTF8);
        headers.setContentType(type);

        return exchange(url, data, HttpMethod.DELETE, bodyType, headers);
    }

    /**
     * 发送/获取 服务端数据(主要用于解决发送put,delete方法无返回值问题)
     *
     * @param url
     * @param content
     * @param method
     * @param bodyType
     * @param headers
     * @param <T>
     * @return
     */
    private <T> T exchange(String url, String content, HttpMethod method, Class<T> bodyType, HttpHeaders headers) {

        // 发送请求
        HttpEntity<String> entity = new HttpEntity<>(content, headers);
        ResponseEntity<T> resultEntity = restTemplate.exchange(url, method, entity, bodyType);

        return resultEntity.getBody();
    }

    public <T> T convertToBean(Class<T> clz, String msg) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            T t = (T) unmarshaller.unmarshal(new StringReader(msg));
            return t;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String convertToXml(Object bean) {
        try {
            JAXBContext context = JAXBContext.newInstance(bean.getClass());
            Marshaller marshaller = context.createMarshaller();
            StringWriter writer = new StringWriter();
            marshaller.marshal(bean, writer);
            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T httpsGet(String url, String param, Class<T> bodyType) {

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType(APPLICATION_JSON_UTF8);
        headers.setContentType(type);

        return exchangeHttps(url, param, HttpMethod.GET, bodyType, headers);

    }

    public <T> T httpsPost(String url, String param, Class<T> bodyType) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType(APPLICATION_JSON_UTF8);
        headers.setContentType(type);
        return exchangeHttps(url,param,HttpMethod.POST,bodyType,headers);
    }

    private <T> T exchangeHttps(String url, String content, HttpMethod method, Class<T> bodyType, HttpHeaders headers) {

        // 发送请求
        HttpEntity<String> entity = new HttpEntity<>(content, headers);
        ResponseEntity<T> resultEntity = httpsRestTemplate.exchange(url, method, entity, bodyType);

        return resultEntity.getBody();
    }
}
