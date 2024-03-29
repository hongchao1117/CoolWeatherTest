package com.hongchao.coolweathertest.db;

import org.litepal.crud.LitePalSupport;

//LitePal中每一个实体类都应该继承LitePalSupport
public class Province extends LitePalSupport {
    private int id;//实体类具有的id
    private String provinceName;//省份的名字
    private int provinceCode;//省的代号

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
