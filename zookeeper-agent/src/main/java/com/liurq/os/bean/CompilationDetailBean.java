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

    /**
     * 已加载类总数
     */
    private Long totalLoadedClassCount;

    /**
     * 当前类加载数量
     */
    private Integer loadedClassCount;

    /**
     * 卸载类数量
     */
    private Long unloadedClassCount;
}
