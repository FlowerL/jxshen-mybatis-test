package com.jxshen.mybatis.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author jxshen on 2018/07/20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BestPracticeEntity {
    private Long id;
    private String name;
    private Integer age;
    private Date createdTime;
    private Date modifiedTime;
}
