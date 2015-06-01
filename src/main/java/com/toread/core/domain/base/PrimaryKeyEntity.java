package com.toread.core.domain.base;

import com.toread.common.tree.easyui.annotation.EasyUiTreeId;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.String;

/**
 * @author 探路者
 * @version 1.0
 * @created 17-八月-2014 11:47:53
 */

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public  class PrimaryKeyEntity implements Serializable{

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @EasyUiTreeId
    private String uuid;

    @Override
    public boolean equals(Object obj) {
        Boolean  equals = false;
        if(obj!=null&&obj instanceof PrimaryKeyEntity) {
            String objId = ((PrimaryKeyEntity) obj).getUuid();
            if(uuid==null&&objId == null){
                equals  = true;
            }else{
                equals = uuid.equals(objId);
            }
        }
        return  equals;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}