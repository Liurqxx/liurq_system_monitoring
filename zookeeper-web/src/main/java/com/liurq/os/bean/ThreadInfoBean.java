package com.liurq.os.bean;

import lombok.Data;

/**
 * @author liurq
 * @desc 线程信息
 * @date 2022-02-22  21:21
 */
@Data
public class ThreadInfoBean {


    /**
     * 线程名称
     */
    private String threadName;

    /**
     * 活动线程
     */
    private Integer threadCount;

    /**
     * 线程峰值
     */
    private Integer peakThreadCount;

    /**
     * 线程总数
     */
    private Long totalStartedThreadCount;

    /**
     * 守护线程总数
     */
    private Integer daemonThreadCount;
}
