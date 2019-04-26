package com.fxdigital.service;

import com.fxdigital.bean.ProductType;
import com.fxdigital.utils.RestResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author hx
 * @date 2019/4/22
 * <p>
 * 主入口，升级包上传管理部分
 */
public interface MainService {

    /**
     * 返回所有的设备类型
     * @return
     */
    List<ProductType> getProductionTypes();

    /**
     * 获取所有的升级包信息
     * @param pageSize
     * @param pageIndex
     * @return
     */
    String getAllPackageInfosForPage(int pageSize, int pageIndex);

    /**
     * 根据设备类型查询升级包的信息
     * @param productType
     * @param pageSize
     * @param pageIndex
     * @return
     */
    String getPackageInfosByProductType(String productType, int pageSize, int pageIndex);

    /**
     * 删除某个升级包
     * @param productType1
     * @param versionNum1
     * @param pageSize
     * @param pageIndex
     * @return
     */
    String deletePackageInfo(String productType1, String versionNum1, int pageSize, int pageIndex);

    /**
     * 上传文件
     * @param productType
     * @param fileDesc
     * @param versionDesc
     * @param upFileValue
     * @return
     */
    String UpgradePackageUpload(MultipartFile file,String productType, String fileDesc, String versionDesc, String upFileValue);
}
