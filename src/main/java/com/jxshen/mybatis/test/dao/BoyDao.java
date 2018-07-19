package com.jxshen.mybatis.test.dao;

import com.jxshen.mybatis.test.entity.BoyEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jxshen on 2018/07/19
 */
public interface BoyDao {

    int insertSelectKeyParam(@Param("entity") BoyEntity entity);

    int insertUseGeneratedKeysParam(@Param("entity") BoyEntity entity);

    int insertSelectKey(BoyEntity entity);

    int insertUseGeneratedKeys(BoyEntity entity);

    int insertBatchParam(@Param("entityList") List<BoyEntity> entityList);

    int insertBatch(List<BoyEntity> entityList);

    int insertOrUpdateSelectKeyParam(@Param("entity") BoyEntity entity);

    int insertOrUpdateUseGeneratedKeysParam(@Param("entity") BoyEntity entity);

    int insertOrUpdateSelectKey(BoyEntity entity);

    int insertOrUpdateUseGeneratedKeys(BoyEntity entity);

    int insertOrUpdateBatchParam(@Param("entityList") List<BoyEntity> entityList);

    int insertOrUpdateBatch(List<BoyEntity> entityList);

}
