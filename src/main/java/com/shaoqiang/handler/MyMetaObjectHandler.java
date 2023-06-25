package com.shaoqiang.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: renshaoqiang
 * @date: 2023/6/18
 * @description: 元对象处理器
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * @author: renshaoqiang
     * @description: 在插入时属性为createTime和updateTime时对其进行自动数据填充
     * @date: 2023/6/18 16:57
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    /**
     * @author: renshaoqiang
     * @description: 在更新时属性为updateTime时对其进行自动数据填充
     * @date: 2023/6/18 16:58
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);

    }
}
