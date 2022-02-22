package com.liurq.os.bean;

import lombok.Data;

/**
 * @author liurq
 * @desc jvm运行信息
 * @date 2022-02-21  22:07
 */
@Data
public class RuntimeDetailBean {

    private String pid;

    /**
     * 进程规范
     */
    private String specName;

    /**
     * jvm规范运营商
     */
    private String specVendor;

    /**
     * jvm规范版本
     */
    private String specVersion;

    /**
     * 启动时间 毫秒
     */
    private String startTime;

    /**
     * 运行时间 毫秒
     */
    private String runTime;

    /**
     * jvm名称
     */
    private String vmName;

    /**
     * jvm运营商
     */
    private String vmVendor;

    /**
     * jvm版本
     */
    private String vmVersion;

    /**
     * jvm参数
     */
    private String vmArgs;

    /**
     * 类路径
     */
//    private String classPath;

    /**
     * 引导类路径
     */
//    private String bootClassPath;

    /**
     * 库路径
     */
//    private String libraryPath;

}
