package com.jxshen.mybatis.test.controller;

import com.jxshen.mybatis.test.dao.BoyDao;
import com.jxshen.mybatis.test.entity.BoyEntity;
import com.jxshen.mybatis.test.param.BoyInsertParam;
import com.jxshen.mybatis.test.ro.BoyInsertRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jxshen on 2018/07/19
 */
@RestController
public class BoyController {

    @Autowired
    private BoyDao boyDao;

    @RequestMapping("/boy/insert")
    public BoyInsertRO insertOrUpdateBoy(@RequestBody BoyInsertParam param) throws InvocationTargetException, IllegalAccessException {
        int num = 0;

        for (Method method : boyDao.getClass().getDeclaredMethods()) {
            if (method.getName().equals(param.getMethod())) {
                method.setAccessible(true);
                if (method.getParameterTypes()[0].equals(BoyEntity.class)) {
                    num = (int) method.invoke(boyDao, param.getBoy());
                }
                else {
                    num = (int) method.invoke(boyDao, param.getBoys());
                }
            }
        }

        return BoyInsertRO.builder()
                .boy(param.getBoy())
                .boys(param.getBoys())
                .num(num)
                .build();
    }
}
