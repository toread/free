/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-05-06
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.service.auth.rbac;

import com.toread.common.tree.easyui.annotation.EasyUiTreeId;
import com.toread.core.domain.base.PrimaryKeyEntity;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @ClassName: SimpleTest
 * @Description:
 * @author lizhibing
 * @date 2015-05-06 0:07 
 */
public class SimpleTest {
    @Test
    public void asy() throws NoSuchFieldException {
        Class<?> ct = PrimaryKeyEntity.class.getClass().getSuperclass();
        Class<?> xx = Object.class;
        System.out.println(xx.equals(ct));

        Field field =PrimaryKeyEntity.class.getDeclaredField("uuid");
        System.out.println(field.isAnnotationPresent(EasyUiTreeId.class));
    }
}
