package com.jxshen.mybatis.test.param;

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
public class BoyInsertParam {

    private BoyEntity boy;
    private List<BoyEntity> boys;
    private String method;
}
