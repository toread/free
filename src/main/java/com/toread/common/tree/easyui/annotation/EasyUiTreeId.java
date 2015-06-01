package com.toread.common.tree.easyui.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-05-05
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface EasyUiTreeId {
}
