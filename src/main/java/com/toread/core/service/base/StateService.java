/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-29
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.toread.core.service.base;

import com.toread.core.domain.base.StateEntity;
import com.toread.core.eume.EnumState;
import com.toread.core.repositories.base.BaseRepository;

/**
 * @author lizhibing
 * @ClassName: StateService
 * @Description:
 * @date 2015-04-29 21:46
 */
public abstract class StateService<T extends StateEntity> extends BaseService<T> {
    public void updateState(T t,EnumState enumState){
        t.setState(enumState);
        save(t);
    }
}
