package com.fxdigital.dao.impl;

import com.fxdigital.bean.PackageInfo;
import com.fxdigital.dao.UpgradePackageDownloadDao;
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
 * 升级包下载dao实现
 */
@Repository
public class UpgradePackageDownloadDaoImpl implements UpgradePackageDownloadDao{
    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public PackageInfo getFilePath(String productType, String packageName) {
        String sql = "select * from VIMS.UPGRADE_PACKAGE_INFO where productType=? and packageName=?";
        log.info("查询下载升级包信息sql="+sql);
        try {
            List<PackageInfo> packageInfos = jdbcTemplate.query(sql,new Object[]{productType,packageName},new BeanPropertyRowMapper<>(PackageInfo.class));
            log.info("查询下载升级包信息结果={}"+packageInfos);
            if (packageInfos.size() > 0){
                return packageInfos.get(0);
            }else {
                log.error("查询不到此升级包");
                return null;
            }
        } catch (Exception e) {
            log.error("查询当前类型的版本号异常", e);
            return null;
        }
    }

    @Override
    public int updataDowntimes(int downloadTimes, String productType, String packageName) {
        String sql = "update VIMS.UPGRADE_PACKAGE_INFO set downloadTimes=? where productType=? and packageName=?";
        log.info("更新升级包下载次数sql="+sql);
        try {
            int i = jdbcTemplate.update(sql,new Object[]{downloadTimes,productType,packageName});
            return i;
        } catch (Exception e) {
            log.error("查询当前类型的版本号异常", e);
            return -1;
        }
    }
}
