package com.fxdigital.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hx
 * @date 2019/4/22
 * <p>
 * 升级包的信息
 */
public class PackageInfo {
    private String productType;
    private int versionNum;
    private int packageSize;
    private String packageName;
    private String uploadPath;
    private Timestamp uploadTime;
    private String uploadTimeDate;
    private int downloadTimes;
    private String packageDesc;
    private String versionDesc;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }

    public int getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(int packageSize) {
        this.packageSize = packageSize;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
        setUploadTimeDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(uploadTime));
    }

    public String getUploadTimeDate() {
        return uploadTimeDate;
    }

    public void setUploadTimeDate(String uploadTimeDate) {
        this.uploadTimeDate = uploadTimeDate;
    }
    public int getDownloadTimes() {
        return downloadTimes;
    }

    public void setDownloadTimes(int downloadTimes) {
        this.downloadTimes = downloadTimes;
    }

    public String getPackageDesc() {
        return packageDesc;
    }

    public void setPackageDesc(String packageDesc) {
        this.packageDesc = packageDesc;
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }
}
