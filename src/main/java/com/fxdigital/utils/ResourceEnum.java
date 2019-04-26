package com.fxdigital.utils;

/**
 * 业务枚举接口类
 * 定义所有业务相关的枚举类型参数。
 *
 * @author liqq
 */
public interface ResourceEnum {

    /**
     * 用户类型
     */
     enum USER_TYPE implements ResourceEnum {
	    //普通用户
        COMMON(1),
	    //管理员
        MANAGER(2),
	    //维护人员
        MAINTAINER(3);

        private Integer value;

	    /**
	     * @value 类型
	     */
        USER_TYPE(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }


    enum INTERFACE_URL implements ResourceEnum {
        //获取服务信息url路径
        SERVER_CONFIG_URL("http://192.168.100.92:12281/v5/storeserver/config"),
        SET_DEVICE_URL("http://192.168.100.92:12281/v5/storeserver/device"),
        SERVER_INFO_URL("http://192.168.100.92:12281/v5/storeserver/storeinfo");


        private String value;

        INTERFACE_URL(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }
}