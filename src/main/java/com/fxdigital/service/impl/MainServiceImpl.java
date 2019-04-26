package com.fxdigital.service.impl;

import com.alibaba.fastjson.JSON;
import com.fxdigital.bean.PackageInfo;
import com.fxdigital.bean.ProductType;
import com.fxdigital.bean.TableData;
import com.fxdigital.dao.UpgradePackageInfoDao;
import com.fxdigital.dao.UpgradePackageUploadDao;
import com.fxdigital.dao.impl.UpgradePackageUploadDaoImpl;
import com.fxdigital.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author hx
 * @date 2019/4/22
 * <p>
 * 上传包，包管理服务实现类
 */
@Service
public class MainServiceImpl implements MainService {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UpgradePackageInfoDao packageInfoDao;
    @Autowired
    UpgradePackageUploadDao uploadDao;

    @Override
    public List<ProductType> getProductionTypes() {
        log.info("【主服务】==进入获取所有设备类型的服务");
        List<ProductType> productTypes = packageInfoDao.getProductionTypes();
        log.info("【主服务】==进入获取所有设备类型的服务：{}", productTypes);
        return productTypes;
    }

    @Override
    public String getAllPackageInfosForPage(int pageSize, int pageIndex) {
        log.info("【主服务】==进入获取分页数据的服务");
        List<PackageInfo> allPackageInfos = packageInfoDao.getAllPackageInfos();
        List<PackageInfo> pagePackageInfos = packageInfoDao.getAllPackageInfosForPage(pageSize, pageIndex);
        TableData tableData = new TableData();
        tableData.setTotal(allPackageInfos.size());
        tableData.setRows(pagePackageInfos);
        String s = JSON.toJSONString(tableData);
        log.info("【主服务】==分页数据={}", s);
        return s;
    }

    @Override
    public String getPackageInfosByProductType(String productType, int pageSize, int pageIndex) {
        log.info("【主服务】==进入根据设备类型获取分页数据的服务");
        List<PackageInfo> allPackageInfos = packageInfoDao.getPackageInfosByPackageType(productType);
        List<PackageInfo> pagePackageInfos = packageInfoDao.getPackageInfosByTypeForPage(productType, pageSize, pageIndex);
        TableData tableData = new TableData();
        tableData.setTotal(allPackageInfos.size());
        tableData.setRows(pagePackageInfos);
        String s = JSON.toJSONString(tableData);
        log.info("【主服务】==根据设备类型获取分页数据={}", s);
        return s;
    }

    @Override
    public String deletePackageInfo(String productType, String versionNum, int pageSize, int pageIndex) {
        log.info("【主服务】==删除升级包的服务");
        String uploadDir;
        String os = System.getProperty("os.name");
        if (os != null && os.toLowerCase().indexOf("linux") > -1) {
            uploadDir = "/etc/sysconfig/workspace";
        } else {
            //测试路径
            uploadDir = "E:/workspace";
        }
        //根据版本类型得到升级包的名称
        String packageName = packageInfoDao.getPackageNameByProductTypeAndVersionNum(productType, versionNum);
        String fileName = uploadDir + "/" + packageName;
        log.info("删除文件路径=" + fileName);
        //删除路径下的升级包文件
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                log.info("删除单个文件" + fileName + "成功！");
            } else {
                log.error("删除单个文件" + fileName + "失败！");
            }
        } else {
            log.error("删除单个文件失败：" + fileName + "不存在！");
        }

        //删除数据库里记录信息
        List<PackageInfo> allPackageInfos = packageInfoDao.getAllPackageInfos();
        List<PackageInfo> pagePackageInfos = packageInfoDao.deletePackageInfo(productType, versionNum, pageSize, pageIndex);
        //返回全部的升级包信息
        TableData tableData = new TableData();
        tableData.setTotal(allPackageInfos.size());
        tableData.setRows(pagePackageInfos);
        String s = JSON.toJSONString(tableData);
        log.info("【主服务】==根据设备类型获取分页数据={}", s);
        return s;
    }

    @Override
    public String UpgradePackageUpload(MultipartFile file, String productType, String fileDesc, String versionDesc, String upFileValue) {
        log.info("【主服务】==上传升级包的服务");
        //获取文件保存目录
        String uploadDir;
        String os = System.getProperty("os.name");
        if (os != null && os.toLowerCase().indexOf("linux") > -1) {
            uploadDir = "/etc/sysconfig/workspace";
        } else {
            //测试路径
            uploadDir = "E:/workspace";
        }
        //查询文件名是否存在
        boolean b = uploadDao.selectUpGradePackagePackageName(upFileValue);
        int versionNum = 1;
        if (!b){
            int oldVersionNum = uploadDao.selectUpGradePackageVersionNum(productType);
            if (oldVersionNum > 0){
                //有保存过的版本
                versionNum = oldVersionNum+1;
            }else if (oldVersionNum == -1){
                return "{\"result\" : 0}";
            }
        }else{
            log.error("升级包已上传,文件已存在");
            return "{\"result\" : 3}";
        }

        //保存文件到目录
        try {
            if (file.isEmpty()) {
                return "{\"result\" : 0}";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
            File dest = new File(uploadDir,fileName);
            log.info("文件路径：" + dest.getAbsolutePath());
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                log.info("【主服务】==上传升级包的服务--目录不存在，创建");
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            // 文件写入
            file.transferTo(dest);
            log.info("【主服务】==写入文件完成");

            //数据库写入升级包信息
            PackageInfo packageInfo = new PackageInfo();
            packageInfo.setProductType(productType);
            packageInfo.setVersionNum(versionNum);
            packageInfo.setPackageSize((int) (file.getSize()/1024));
            packageInfo.setPackageName(fileName);
            packageInfo.setUploadPath(dest.getAbsolutePath());
            packageInfo.setUploadTime(new Timestamp(new Date().getTime()));
            packageInfo.setDownloadTimes(0);
            packageInfo.setPackageDesc(fileDesc);
            packageInfo.setVersionDesc(versionDesc);

            int i = uploadDao.insertUpGradePackageInfo(packageInfo);
            if (i <= 0){
                return "{\"result\" : 0}";
            }
            return "{\"result\" : 1}";
        } catch (IOException e) {
            log.error("保存文件失败"+e);
            return "{\"result\" : 0}";
        }


    }


}
