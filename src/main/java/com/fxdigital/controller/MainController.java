package com.fxdigital.controller;

import com.fxdigital.bean.ProductType;
import com.fxdigital.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author hx
 * @date 2019/4/22
 * <p>
 * 主界面控制器
 */
@RestController
@RequestMapping("/fxrest/v5/upgradePackage")
public class MainController {
    private Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    MainService mainService;

    @RequestMapping(value = "/packages", method = RequestMethod.POST)
    @ResponseBody
    public String getAllPackageInfosForPage(@RequestBody Map<String, Object> param) {
        int pageSize = (int) param.get("pageSize");
        int pageIndex = (int) param.get("pageIndex");
        log.info("请求获取表格显示需要内容==pageSize"+pageSize+";pageIndex="+pageIndex);
        String s = mainService.getAllPackageInfosForPage(pageSize, pageIndex);
        return s;
    }

    @RequestMapping(value = "/getProductionTypes", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductType> getProductionTypes() {
        log.info("请求获取所有产品类型信息");
        List<ProductType> productTypes = mainService.getProductionTypes();
        return productTypes;
    }

    @RequestMapping(value = "/packages/{productType}", method = RequestMethod.POST)
    @ResponseBody
    public String getPackageInfosByProductType(@RequestBody Map<String, Object> param, @PathVariable String productType) {
        String productType1 = productType;
        int pageSize = (int) param.get("pageSize");
        int pageIndex = (int) param.get("pageIndex");
        log.info("请求按产品类型查询升级包信息,productType:" + productType1 + ",pageSize:" + pageSize + ",pageIndex:" + pageIndex);
        String s = mainService.getPackageInfosByProductType(productType1, pageSize, pageIndex);
        return s;
    }

    @RequestMapping(value = "/packages/{productType}/{versionNum}", method = RequestMethod.POST)
    @ResponseBody
    public String deletePackageInfo(@RequestBody Map<String, Object> param, @PathVariable String productType,@PathVariable String versionNum) {
        String productType1 = productType;
        String versionNum1 = versionNum;
        int pageSize = (int) param.get("pageSize");
        int pageIndex = (int) param.get("pageIndex");
        log.info("请求按条件删除升级包信息,productType:" + productType1 + ",versionNum：" + versionNum1 + ",pageSize:" + pageSize + ",pageIndex:" + pageIndex);
        String s =  mainService.deletePackageInfo(productType1, versionNum1, pageSize, pageIndex);
        return s;
    }

    @RequestMapping(value = "/UpgradePackageUpload/{obj}", method = RequestMethod.POST)
    @ResponseBody
    public String UpgradePackageUpload(@RequestParam(value = "file") MultipartFile file, @PathVariable String obj) {
        log.info("收到升级包的信息==obj="+obj);
        String[] param = obj.split(":");
        String productType = param[0];
        String fileDesc = param[1];
        String versionDesc = param[2];
        String upFileValue = param[3];


        log.info("收到升级包的信息==productType="+productType+";fileDesc="+fileDesc+";versionDesc="+versionDesc+
                ";upFileValue="+upFileValue);
        String s = mainService.UpgradePackageUpload(file,productType, fileDesc, versionDesc, upFileValue);
        return s;
    }

}
