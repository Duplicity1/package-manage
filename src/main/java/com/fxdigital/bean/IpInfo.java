/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ipInfoTab
 * Author:   fx
 * Date:     2018/6/11 15:03
 * Description: 网关消息表单
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.fxdigital.bean;

/**
 * 〈网关消息表单〉
 * @author fx
 * @since 1.0.0
 */
public class IpInfo {
    private String addr;//本地应用服务ip
    private String mask;//子网掩码
    private String gateway;//网关ip
    private String oldIp;


    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getOldIp() {
        return oldIp;
    }

    public void setOldIp(String oldIp) {
        this.oldIp = oldIp;
    }
}
