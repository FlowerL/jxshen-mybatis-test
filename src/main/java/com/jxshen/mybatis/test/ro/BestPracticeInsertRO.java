package com.jxshen.mybatis.test.ro;

import com.jxshen.mybatis.test.entity.BestPracticeEntity;
import com.jxshen.mybatis.test.entity.BoyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jxshen on 2018/07/19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BestPracticeInsertRO {

    private BestPracticeEntity bestPractice;
    private List<BestPracticeEntity> bestPractices;
    private Integer num;
}
