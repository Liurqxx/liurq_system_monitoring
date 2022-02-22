package com.liurq.os.bean;

import lombok.Data;

/**
 * @author liurq
 * @desc 垃圾收集器信息
 * @date 2022-02-22  21:05
 */
@Data
public class GarbageCollectorInfoBean {

    /**
     * 收集器名称
     */
    private String name;

    /**
     * 收集数量
     */
    private Long collectionCount;

    /**
     * 收集花费时间
     */
    private Long collectionTime;

    /**
     * 收集的内存区
     */
    private String[] memoryPoolNames;
}
