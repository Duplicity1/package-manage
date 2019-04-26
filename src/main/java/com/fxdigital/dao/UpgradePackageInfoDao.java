package com.fxdigital.dao;

import com.fxdigital.bean.PackageInfo;
import com.fxdigital.bean.ProductType;

import java.util.List;

/**
 * @author hx
 * @date 2019/4/22
 * <p>
 * 升级包信息Dao
 */
public interface UpgradePackageInfoDao {

    /**
     * 获取所有的设备类型
     * @return
     */
    List<ProductType> getProductionTypes();

    /**
     * 获取所有升级包的信息
     * @return
     */
    List<PackageInfo> getAllPackageInfos();

    /**
     * 获取分页的升级包信息
     * @param pageSize
     * @param pageIndex
     * @return
     */
    List<PackageInfo> getAllPackageInfosForPage(int pageSize, int pageIndex);

    /**
     * 查询单个设备类型的所有升级包
     * @param productType
     * @return
     */
    List<PackageInfo> getPackageInfosByPackageType(String productType);

    /**
     * 获取某个设备类型的升级包分页信息
     * @param productType
     * @param pageSize
     * @param pageIndex
     * @return
     */
    List<PackageInfo> getPackageInfosByTypeForPage(String productType, int pageSize, int pageIndex);

    /**
     * 删除升级包信息，并查询分页信息
     * @param productType
     * @param versionNum
     * @param pageSize
     * @param pageIndex
     * @return
     */
    List<PackageInfo> deletePackageInfo(String productType, String versionNum, int pageSize, int pageIndex);

    /**
     * 根据版本和类型，得到审计报告的名称
     * @param productType
     * @param versionNum
     * @return
     */
    String getPackageNameByProductTypeAndVersionNum(String productType, String versionNum);
}
