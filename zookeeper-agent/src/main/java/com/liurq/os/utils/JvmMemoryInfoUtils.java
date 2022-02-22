package com.liurq.os.utils;

import com.liurq.os.bean.JvmMemoryInfoBean;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * @author liurq
 * @desc jvm内存信息工具类
 * @date 2022-02-22  21:29
 */
public class JvmMemoryInfoUtils {

    private static final long MB = 1024 * 1024;

    public static JvmMemoryInfoBean getJvmMemoryInfo() {

        JvmMemoryInfoBean jvmMemoryInfoBean = new JvmMemoryInfoBean();

        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        MemoryUsage headMemory = memory.getHeapMemoryUsage();
//        System.out.println("head堆:");
//        System.out.println("\t初始(M):" + headMemory.getInit() / MB);
        jvmMemoryInfoBean.setHeadMemoryInit(headMemory.getInit() / MB + "M");
//        System.out.println("\t最大(上限)(M):" + headMemory.getMax() / MB);
        jvmMemoryInfoBean.setHeadMemoryMax(headMemory.getMax() / MB + "M");
//        System.out.println("\t当前(已使用)(M):" + headMemory.getUsed() / MB);
        jvmMemoryInfoBean.setHeadMemoryUsed(headMemory.getUsed() / MB + "M");
//        System.out.println("\t提交的内存(已申请)(M):" + headMemory.getCommitted() / MB);
        jvmMemoryInfoBean.setHeadMemoryCommited(headMemory.getCommitted() / MB + "M");
//        System.out.println("\t使用率:" + headMemory.getUsed() * 100 / headMemory.getCommitted() + "%");
        jvmMemoryInfoBean.setHeadMemoryUsage(headMemory.getUsed() * 100 / headMemory.getCommitted() + "%");

//        System.out.println("non-head非堆:");
//        System.out.println("\t初始(M):" + nonheadMemory.getInit() / MB);
//        System.out.println("\t最大(上限)(M):" + nonheadMemory.getMax() / MB);
//        System.out.println("\t当前(已使用)(M):" + nonheadMemory.getUsed() / MB);
//        System.out.println("\t提交的内存(已申请)(M):" + nonheadMemory.getCommitted() / MB);
//        System.out.println("\t使用率:" + nonheadMemory.getUsed() * 100 / nonheadMemory.getCommitted() + "%");
        MemoryUsage nonheadMemory = memory.getNonHeapMemoryUsage();
        jvmMemoryInfoBean.setNonHeadMemoryInit(nonheadMemory.getInit() / MB + "M");
        jvmMemoryInfoBean.setNonHeadMemoryMax(nonheadMemory.getMax() / MB + "M");
        jvmMemoryInfoBean.setNonHeadMemoryUsed(nonheadMemory.getUsed() / MB + "M");
        jvmMemoryInfoBean.setNonHeadMemoryCommited(nonheadMemory.getCommitted() / MB + "M");
        jvmMemoryInfoBean.setNonHeadMemoryUsage(nonheadMemory.getUsed() * 100 / nonheadMemory.getCommitted() + "%");

        return jvmMemoryInfoBean;
    }

}
