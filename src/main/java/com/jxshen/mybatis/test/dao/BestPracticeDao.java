package com.jxshen.mybatis.test.dao;

import com.jxshen.mybatis.test.entity.BestPracticeEntity;

import java.util.List;

/**
 * @author jxshen on 2018/07/20
 */
public interface BestPracticeDao {

    int insert(BestPracticeEntity entity);

    int insertBatch(List<BestPracticeEntity> entityList);

    int insertOrUpdate(BestPracticeEntity entity);

    int insertOrUpdateBatch(List<BestPracticeEntity> entityList);
}
