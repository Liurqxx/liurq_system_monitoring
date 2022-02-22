package com.liurq.os.utils;

import com.liurq.os.bean.ThreadInfoBean;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * @author liurq
 * @desc 线程相关信息工具类
 * @date 2022-02-22  21:34
 */
public class ThreadInfoUtils {


    public static ThreadInfoBean getThreadInfo() {

        ThreadInfoBean threadInfoBean = new ThreadInfoBean();
        ThreadMXBean thread = ManagementFactory.getThreadMXBean();
//        System.out.println("ObjectName=" + thread.getObjectName());
//        System.out.println("仍活动的线程总数=" + thread.getThreadCount());
//        System.out.println("峰值=" + thread.getPeakThreadCount());
//        System.out.println("线程总数（被创建并执行过的线程总数）=" + thread.getTotalStartedThreadCount());
//        System.out.println("当初仍活动的守护线程（daemonThread）总数=" + thread.getDaemonThreadCount());
        threadInfoBean.setThreadName(thread.getObjectName().toString());
        threadInfoBean.setThreadCount(thread.getThreadCount());
        threadInfoBean.setPeakThreadCount(thread.getPeakThreadCount());
        threadInfoBean.setTotalStartedThreadCount(thread.getTotalStartedThreadCount());
        threadInfoBean.setDaemonThreadCount(thread.getDaemonThreadCount());
        return threadInfoBean;
    }

}
