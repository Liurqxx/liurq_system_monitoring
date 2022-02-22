package com.liurq.os.utils;

import com.liurq.os.bean.RuntimeDetailBean;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

/**
 * @author liurq
 * @desc jvm运行信息工具类
 * @date 2022-02-22  20:51
 */
public class RuntimeDetailUtils {


    public static RuntimeDetailBean getRuntimeDetail() {


        RuntimeDetailBean runtimeDetailBean = new RuntimeDetailBean();
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
//        System.out.println("进程PID=" + runtime.getName().split("@")[0]);
        runtimeDetailBean.setPid(runtime.getName().split("@")[0]);
//        System.out.println("jvm规范名称:" + runtime.getSpecName());
        runtimeDetailBean.setSpecName(runtime.getSpecName());
//        System.out.println("jvm规范运营商:" + runtime.getSpecVendor());
        runtimeDetailBean.setSpecVendor(runtime.getSpecVendor());
//        System.out.println("jvm规范版本:" + runtime.getSpecVersion());
        runtimeDetailBean.setSpecVersion(runtime.getSpecVersion());
        //返回虚拟机在毫秒内的开始时间。该方法返回了虚拟机启动时的近似时间
//        System.out.println("jvm启动时间（毫秒）:" + runtime.getStartTime());
        runtimeDetailBean.setStartTime(runtime.getStartTime() + "毫秒");
        //相当于System.getProperties
//        System.out.println("获取System.properties:" + runtime.getSystemProperties());
//        System.out.println("jvm正常运行时间（毫秒）:" + runtime.getUptime());
        runtimeDetailBean.setRunTime(runtime.getUptime() + "毫秒");
        //相当于System.getProperty("java.vm.name").
//        System.out.println("jvm名称:" + runtime.getVmName());
        runtimeDetailBean.setVmName(runtime.getVmName());
        //相当于System.getProperty("java.vm.vendor").
//        System.out.println("jvm运营商:" + runtime.getVmVendor());
        runtimeDetailBean.setVmVendor(runtime.getVmVendor());
        //相当于System.getProperty("java.vm.version").
//        System.out.println("jvm实现版本:" + runtime.getVmVersion());
        runtimeDetailBean.setVmVersion(runtime.getVmVersion());
        List<String> args = runtime.getInputArguments();
        if (args != null && !args.isEmpty()) {
//            System.out.println("vm参数:");
//            for (String arg : args) {
//                System.out.println(arg);
//            }
            runtimeDetailBean.setVmArgs(String.join(",", args));
        }
//        System.out.println("类路径:" + runtime.getClassPath());
        runtimeDetailBean.setClassPath(runtime.getClassPath());
//        System.out.println("引导类路径:" + runtime.getBootClassPath());
        runtimeDetailBean.setBootClassPath(runtime.getBootClassPath());
//        System.out.println("库路径:" + runtime.getLibraryPath());
        runtimeDetailBean.setLibraryPath(runtime.getLibraryPath());

        return runtimeDetailBean;
    }


}
