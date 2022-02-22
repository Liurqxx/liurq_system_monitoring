package com.liurq.os.bean;

import lombok.Data;

/**
 * @author liurq
 * @desc 操作系统相关信息
 * @date 2022-02-21  21:41
 */

@Data
public class SystemDetailBean {

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 系统版本
     */
    private String systemVersion;

    /**
     * 架构
     */
    private String systemArch;

    /**
     * 可用核数
     */
    private String availableProcessors;

    /**
     * 总物理内存
     */
    private String totalPhysicalMemory;

    /**
     * 已用物理内存
     */
    private String usedPhysicalMemory;

    /**
     * 剩余物理内存
     */
    private String freePhysicalMemory;

    /**
     * 总交换空间
     */
    private String totalSwapSpace;

    /**
     * 已用交换空间
     */
    private String usedSwapSpace;

    /**
     * 剩余交换空间
     */
    private String freeSwapSpace;

}
