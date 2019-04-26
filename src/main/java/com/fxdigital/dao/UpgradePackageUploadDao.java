package com.fxdigital.dao;

import com.fxdigital.bean.PackageInfo; /**
 * @author hx
 * @date 2019/4/22
 * <p>
 * 升级包上传
 */
public interface UpgradePackageUploadDao {

    /**
     * 获取文件是否存在于数据库
     * @return
     * @param upFileValue
     */
    boolean selectUpGradePackagePackageName(String upFileValue);

    /**
     * 获取当前设备类型的版本号码
     * @param productType
     * @return
     */
    int selectUpGradePackageVersionNum(String productType);

    /**
     * 插入升级包信息到数据库
     * @param packageInfo
     * @return
     */
    int insertUpGradePackageInfo(PackageInfo packageInfo);
}
