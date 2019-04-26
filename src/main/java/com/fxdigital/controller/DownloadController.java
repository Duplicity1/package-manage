package com.fxdigital.controller;

import com.fxdigital.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hx
 * @date 2019/4/22
 * <p>
 * 下载控制器
 */
@RestController
@RequestMapping("/fxrest/v5/upgradePackage")
public class DownloadController {
    private Logger log = LoggerFactory.getLogger(DownloadController.class);

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/downloadPackage/{packageName}/{productType}", method = RequestMethod.GET)
    @ResponseBody
    public void getAllPackageInfosForPage(@PathVariable String productType, @PathVariable String packageName,
                                          HttpServletRequest request, HttpServletResponse response) {
        log.info("【下载控制器】==接收数据:productType="+productType+"=packageName="+packageName);
        loginService.downloadPackage(productType,packageName,request,response);

    }
}
