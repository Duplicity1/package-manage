package com.fxdigital.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hx
 * @date 2019/4/22
 * <p>
 * 登录服务
 */
public interface LoginService {
    /**
     * 下载升级包
     * @param productType
     * @param packageName
     * @param request
     * @param response
     */
    void downloadPackage(String productType, String packageName, HttpServletRequest request, HttpServletResponse response);
}
