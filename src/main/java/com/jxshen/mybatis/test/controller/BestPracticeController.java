package com.jxshen.mybatis.test.controller;

import com.jxshen.mybatis.test.dao.BestPracticeDao;
import com.jxshen.mybatis.test.entity.BestPracticeEntity;
import com.jxshen.mybatis.test.param.BestPracticeInsertParam;
import com.jxshen.mybatis.test.ro.BestPracticeInsertRO;
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
public class BestPracticeController {

    @Autowired
    private BestPracticeDao bestPracticeDao;

    @RequestMapping("/best/practice/insert")
    public BestPracticeInsertRO insertOrUpdate(@RequestBody BestPracticeInsertParam param) throws InvocationTargetException, IllegalAccessException {
        int num = 0;

        for (Method method : bestPracticeDao.getClass().getDeclaredMethods()) {
            if (method.getName().equals(param.getMethod())) {
                method.setAccessible(true);
                if (method.getParameterTypes()[0].equals(BestPracticeEntity.class)) {
                    num = (int) method.invoke(bestPracticeDao, param.getBestPractice());
                }
                else {
                    num = (int) method.invoke(bestPracticeDao, param.getBestPractices());
                }
            }
        }

        return BestPracticeInsertRO.builder()
                .bestPractice(param.getBestPractice())
                .bestPractices(param.getBestPractices())
                .num(num)
                .build();
    }
}
