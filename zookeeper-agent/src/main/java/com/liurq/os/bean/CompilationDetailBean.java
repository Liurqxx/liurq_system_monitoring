package com.liurq.os.bean;

import lombok.Data;

/**
 * @author liurq
 * @desc 编译器基本信息
 * @date 2022-02-21  21:52
 */
@Data
public class CompilationDetailBean {

    /**
     * JIT编译器名称
     */
    private String compilationName;

    /**
     * 总编译时间
     */
    private String totalCompilationTime;
}
