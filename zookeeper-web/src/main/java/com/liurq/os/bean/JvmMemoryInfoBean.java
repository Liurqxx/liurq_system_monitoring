package com.liurq.os.bean;

import lombok.Data;

/**
 * @author liurq
 * @desc jvm内存信息
 * @date 2022-02-22  21:11
 */
@Data
public class JvmMemoryInfoBean {


    /**
     * 初始大小 M
     */
    private String headMemoryInit;


    /**
     * 上限大小M
     */
    private String headMemoryMax;

    /**
     * 已使用的 M
     */
    private String headMemoryUsed;

    /**
     * 已申请的 M
     */
    private String headMemoryCommited;

    /**
     * 使用率 %
     */
    private String headMemoryUsage;


    /**
     * 初始大小 M
     */
    private String nonHeadMemoryInit;


    /**
     * 上限大小M
     */
    private String nonHeadMemoryMax;

    /**
     * 已使用的 M
     */
    private String nonHeadMemoryUsed;

    /**
     * 已申请的 M
     */
    private String nonHeadMemoryCommited;

    /**
     * 使用率 %
     */
    private String nonHeadMemoryUsage;

}
