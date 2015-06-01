package com.toread.common.page;

/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-05-08
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
public enum PageResultType{
    GRID(PageResultType.GRID_STRING),FORM(PageResultType.FORM_STRING);

    public static final String GRID_STRING = "grid";
    public static final String FORM_STRING = "form";

    private String type;
    PageResultType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static final String  getType(PageResultType pageResultType){
        return pageResultType.getType();
    }
}
