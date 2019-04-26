package com.fxdigital.dao;

import com.fxdigital.bean.PackageInfo;

/**
 * @author hx
 * @date 2019/4/22
 * <p>
 * 升级包下载
 */
public interface UpgradePackageDownloadDao {

    /**
     * 获取升级文件包的信息，主要是文件路径
     * @param productType
     * @param packageName
     * @return
     */
    PackageInfo getFilePath(String productType, String packageName);

    /**
     * 更新数据库下载次数
     * @param downloadTimes
     * @param productType
     * @param packageName
     * @return
     */
    int updataDowntimes(int downloadTimes, String productType, String packageName);
}
