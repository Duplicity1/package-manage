package com.fxdigital.dao.impl;

import com.fxdigital.bean.PackageInfo;
import com.fxdigital.bean.ProductType;
import com.fxdigital.dao.UpgradePackageInfoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hx
 * @date 2019/4/22
 * <p>
 * 升级包信息dao实现
 */
@Repository
public class UpgradePackageInfoDaoImpl implements UpgradePackageInfoDao{
    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<ProductType> getProductionTypes() {
        String sql = "select typeCode,typeName from VIMS.DICT_PRODUCTTYPE_TAB";
        log.info("查询所有的设备类型sql="+sql);
        List<ProductType> list = null;
        try {
            list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProductType.class));
        } catch (Exception e) {
            log.error("获取所有类型异常", e);
        }
        return list;
    }

    @Override
    public List<PackageInfo> getAllPackageInfos() {
        String sql = "select a.typeName as productType,b.versionNum,b.packageSize,b.packageName,b.uploadPath," +
                "b.uploadTime,b.downloadTimes,b.packageDesc,b.versionDesc " +
                "from VIMS.UPGRADE_PACKAGE_INFO as b,VIMS.DICT_PRODUCTTYPE_TAB as a " +
                "where a.typeCode=b.productType";
        log.info("查询所有的升级包信息sql="+sql);
        List<PackageInfo> list = null;
        try {
            list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PackageInfo.class));
        } catch (Exception e) {
            log.error("查询所有的升级包信息异常", e);
        }
        return list;
    }

    @Override
    public List<PackageInfo> getAllPackageInfosForPage(int pageSize, int pageIndex) {
        String sql = "select a.typeName as productType,b.versionNum,b.packageSize,b.packageName,b.uploadPath," +
                "b.uploadTime,b.downloadTimes,b.packageDesc,b.versionDesc " +
                "from VIMS.UPGRADE_PACKAGE_INFO as b,VIMS.DICT_PRODUCTTYPE_TAB as a " +
                "where a.typeCode=b.productType limit " + (pageIndex - 1) + "," + pageSize;
        log.info("查询分页升级包信息sql="+sql);
        List<PackageInfo> list = null;
        try {
            list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PackageInfo.class));
        } catch (Exception e) {
            log.error("查询分页升级包信息异常", e);
        }
        return list;
    }

    @Override
    public List<PackageInfo> getPackageInfosByPackageType(String productType) {
        String sql = "select a.typeName as productType,b.versionNum,b.packageSize,b.packageName,b.uploadPath," +
                "b.uploadTime,b.downloadTimes,b.packageDesc,b.versionDesc " +
                "from VIMS.UPGRADE_PACKAGE_INFO as b,VIMS.DICT_PRODUCTTYPE_TAB as a " +
                "where a.typeCode=b.productType and b.productType=?";
        log.info("查询某个设备类型所有升级包信息sql="+sql);
        List<PackageInfo> list = null;
        try {
            list = jdbcTemplate.query(sql,new Object[]{productType}, new BeanPropertyRowMapper<>(PackageInfo.class));
        } catch (Exception e) {
            log.error("查询某个设备类型所有升级包信息异常", e);
        }
        return list;
    }

    @Override
    public List<PackageInfo> getPackageInfosByTypeForPage(String productType, int pageSize, int pageIndex) {
        String sql = "select a.typeName as productType,b.versionNum,b.packageSize,b.packageName,b.uploadPath," +
                "b.uploadTime,b.downloadTimes,b.packageDesc,b.versionDesc " +
                "from VIMS.UPGRADE_PACKAGE_INFO as b,VIMS.DICT_PRODUCTTYPE_TAB as a " +
                "where a.typeCode=b.productType and b.productType=?" + " limit " + (pageIndex - 1) + "," + pageSize;
        log.info("查询某个设备类型分页升级包信息sql="+sql);
        List<PackageInfo> list = null;
        try {
            list = jdbcTemplate.query(sql,new Object[]{productType}, new BeanPropertyRowMapper<>(PackageInfo.class));
        } catch (Exception e) {
            log.error("查询某个设备类型分页升级包信息异常", e);
        }
        return list;
    }

    @Override
    public List<PackageInfo> deletePackageInfo(String productType, String versionNum, int pageSize, int pageIndex) {
        String deleteSql = "delete from VIMS.UPGRADE_PACKAGE_INFO" +
                " where productType=? and  versionNum=?";
        log.info("删除升级包信息sql="+deleteSql);
        int i = jdbcTemplate.update(deleteSql,new Object[]{productType,versionNum});
        List<PackageInfo> list = null;
        try {
            if (i < 0){
                log.error("删除升级包信息失败");
            }else {
                list = getAllPackageInfosForPage(pageSize,pageIndex);
            }
        } catch (Exception e) {
            log.error("删除升级包信息异常", e);
            return null;
        }
        return list;
    }

    @Override
    public String getPackageNameByProductTypeAndVersionNum(String productType, String versionNum) {
        String sql = "select packageName from VIMS.UPGRADE_PACKAGE_INFO where productType=? and versionNum=?";
        log.info("获取升级包的名称sql="+sql);
        String packageName = null;
        try {
            packageName = jdbcTemplate.queryForObject(sql,new Object[]{productType,versionNum}, String.class);
        } catch (Exception e) {
            log.error("获取升级包的名称异常", e);
        }
        return packageName;
    }


}
