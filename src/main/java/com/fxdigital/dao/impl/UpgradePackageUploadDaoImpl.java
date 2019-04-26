package com.fxdigital.dao.impl;

import com.fxdigital.bean.PackageInfo;
import com.fxdigital.dao.UpgradePackageUploadDao;
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
 * 升级包上传dao实现
 */
@Repository
public class UpgradePackageUploadDaoImpl implements UpgradePackageUploadDao{
    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean selectUpGradePackagePackageName(String upFileValue) {
        String sql = "select count(*) from VIMS.UPGRADE_PACKAGE_INFO where packageName = ?";
        log.info("查询是否存在升级包名称sql="+sql);
        try {
            int i = jdbcTemplate.queryForObject(sql,new Object[]{upFileValue},int.class);
            log.info("查询是否存在升级包名称结果="+i);
            if (i > 0){
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            log.error("查询是否存在升级包名称异常", e);
            return false;
        }
    }

    @Override
    public int selectUpGradePackageVersionNum(String productType) {
        String sql = "select versionNum from VIMS.UPGRADE_PACKAGE_INFO where productType = ? order by versionNum desc";
        log.info("查询当前类型的版本号sql="+sql);
        try {
            List<Integer> versionNums = jdbcTemplate.queryForList(sql,new Object[]{productType},Integer.class);
            log.info("查询当前类型的版本号结果="+versionNums);
            if (versionNums.size() > 0){
                return versionNums.get(0);
            }else {
                return 0;
            }
        } catch (Exception e) {
            log.error("查询当前类型的版本号异常", e);
            return -1;
        }
    }

    @Override
    public int insertUpGradePackageInfo(PackageInfo packageInfo) {
        String sql = "insert into VIMS.UPGRADE_PACKAGE_INFO values(?,?,?,?,?,?,?,?,?)";
        log.info("插入升级包信息sql="+sql);
        try {
            int i = jdbcTemplate.update(sql,new Object[]{packageInfo.getProductType(),packageInfo.getVersionNum()
            ,packageInfo.getPackageSize(),packageInfo.getPackageName(),packageInfo.getUploadPath(),packageInfo.getUploadTime()
            ,packageInfo.getDownloadTimes(),packageInfo.getPackageDesc(),packageInfo.getVersionDesc()});

            log.info("插入升级包信息结果="+i);
            return i;
        } catch (Exception e) {
            log.error("插入升级包信息异常", e);
            return -1;
        }
    }
}
