package com.fxdigital.bean;

import java.util.List;

/**
 * @author hx
 * @date 2019/4/22
 * <p>
 * 前段页面创建表格需要的数据
 */
public class TableData {
    private int total;
    private List<PackageInfo> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PackageInfo> getRows() {
        return rows;
    }

    public void setRows(List<PackageInfo> rows) {
        this.rows = rows;
    }
}
