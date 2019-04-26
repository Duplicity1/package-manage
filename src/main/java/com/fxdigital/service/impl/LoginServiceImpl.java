package com.fxdigital.service.impl;

import com.alibaba.fastjson.JSON;
import com.fxdigital.bean.PackageInfo;
import com.fxdigital.dao.UpgradePackageDownloadDao;
import com.fxdigital.service.LoginService;
import com.fxdigital.utils.UserAgentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author hx
 * @date 2019/4/22
 * <p>
 * 登录服务实现类
 */
@Service
public class LoginServiceImpl implements LoginService {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UpgradePackageDownloadDao downloadDao;

    @Override
    public void downloadPackage(String productType, String packageName, HttpServletRequest request, HttpServletResponse response) {
        PackageInfo packageInfo = downloadDao.getFilePath(productType, packageName);
        log.info("【下载服务】==下载包信息：{}" + JSON.toJSONString(packageInfo));
        int downloadTimes = packageInfo.getDownloadTimes() + 1;
        String packagePath = packageInfo.getUploadPath();
        File file = new File(packagePath);
        if (file.exists()) {
            response.setContentType("application/octet-stream;charset=utf8");// 设置强制下载不打开
            response.setHeader("Content-disposition",
                    "attachment;" + UserAgentUtil.encodeFileName(request, packageName));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                ServletOutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                os.flush();
                os.close();
                log.info("【下载服务】==下载完成");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //更新数据库下载信息
            int i = downloadDao.updataDowntimes(downloadTimes, productType, packageName);
            if (i < 0){
                log.info("【下载服务】==更新数据失败");
            }
        } else {
            log.error("【下载服务】==文件不存在：{}" + packageName);
        }
    }
}
