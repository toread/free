package com.toread.core.domain.base;


import com.toread.core.domain.base.PrimaryKeyEntity;
import com.toread.core.eume.EnumState;

import javax.persistence.*;

/**
 * @author 探路者
 * @version 1.0
 * @created 17-八月-2014 11:47:54
 */

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class StateEntity extends PrimaryKeyEntity {

    @Enumerated(EnumType.STRING)
    private EnumState state;

    public EnumState getState() {
        return state;
    }

    public void setState(EnumState state) {
        this.state = state;
    }
}