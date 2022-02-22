package com.liurq.os.utils;

import com.liurq.os.bean.CompilationDetailBean;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.ManagementFactory;

/**
 * @author liurq
 * @desc 编译信息工具类
 * @date 2022-02-22  20:46
 */
public class CompilationDetailUtils {


    public static CompilationDetailUtils instance = new CompilationDetailUtils();

    public static CompilationDetailUtils getInstance() {
        return instance;
    }

    public static CompilationDetailBean getCompilationDetail() {

        CompilationDetailBean compilationDetailBean = new CompilationDetailBean();

        //编译信息
        CompilationMXBean compilation = ManagementFactory.getCompilationMXBean();
        compilationDetailBean.setCompilationName(compilation.getName());
//        System.out.println("JIT编译器名称：" + compilation.getName());
        //判断jvm是否支持编译时间的监控
        if (compilation.isCompilationTimeMonitoringSupported()) {
//            System.out.println("总编译时间：" + compilation.getTotalCompilationTime() + "秒");
            compilationDetailBean.setTotalCompilationTime(compilation.getTotalCompilationTime() + "秒");
        }
        ClassLoadingMXBean classLoad = ManagementFactory.getClassLoadingMXBean();
//        System.out.println("已加载类总数：" + classLoad.getTotalLoadedClassCount());
//        System.out.println("已加载当前类：" + classLoad.getLoadedClassCount());
//        System.out.println("已卸载类总数：" + classLoad.getUnloadedClassCount());

        compilationDetailBean.setTotalLoadedClassCount(classLoad.getTotalLoadedClassCount());
        compilationDetailBean.setLoadedClassCount(classLoad.getLoadedClassCount());
        compilationDetailBean.setUnloadedClassCount(classLoad.getUnloadedClassCount());

        return compilationDetailBean;

    }

}
