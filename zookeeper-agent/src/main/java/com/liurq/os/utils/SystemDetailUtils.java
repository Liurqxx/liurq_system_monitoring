package com.liurq.os.utils;

import com.liurq.os.bean.SystemDetailBean;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author liurq
 * @desc 操作系统工具类
 * @date 2022-02-21  22:13
 */
public class SystemDetailUtils {

    private static final long MB = 1024 * 1024;
    private static OperatingSystemMXBean system = ManagementFactory.getOperatingSystemMXBean();

    public static OperatingSystemMXBean getInstance() {
        return system;
    }

    public static SystemDetailBean getSystemInfo() {

        SystemDetailBean systemDetailBean = new SystemDetailBean();

        //相当于System.getProperty("os.name")
//        System.out.println("系统名称:" + system.getName());
        systemDetailBean.setSystemName(system.getName());
        //相当于System.getProperty("os.version").
//        System.out.println("系统版本:" + system.getVersion());
        systemDetailBean.setSystemVersion(system.getVersion());
        //相当于System.getProperty("os.arch").
//        System.out.println("操作系统的架构:" + system.getArch());
        systemDetailBean.setSystemArch(systemDetailBean.getSystemArch());
        //相当于 Runtime.availableProcessors()
//        System.out.println("可用的内核数:" + system.getAvailableProcessors());
        systemDetailBean.setAvailableProcessors(system.getAvailableProcessors() + "");
        if (isSunOsMBean(system)) {
            long totalPhysicalMemory = getLongFromOperatingSystem(system, "getTotalPhysicalMemorySize");
            long freePhysicalMemory = getLongFromOperatingSystem(system, "getFreePhysicalMemorySize");
            long usedPhysicalMemorySize = totalPhysicalMemory - freePhysicalMemory;

//            System.out.println("总物理内存(M):" + totalPhysicalMemory / MB);
//            System.out.println("已用物理内存(M):" + usedPhysicalMemorySize / MB);
//            System.out.println("剩余物理内存(M):" + freePhysicalMemory / MB);
            systemDetailBean.setTotalPhysicalMemory(totalPhysicalMemory / MB + "M");
            systemDetailBean.setUsedPhysicalMemory(usedPhysicalMemorySize / MB + "M");
            systemDetailBean.setFreePhysicalMemory(freePhysicalMemory / MB + "M");

            long totalSwapSpaceSize = getLongFromOperatingSystem(system, "getTotalSwapSpaceSize");
            long freeSwapSpaceSize = getLongFromOperatingSystem(system, "getFreeSwapSpaceSize");
            long usedSwapSpaceSize = totalSwapSpaceSize - freeSwapSpaceSize;

//            System.out.println("总交换空间(M):" + totalSwapSpaceSize / MB);
//            System.out.println("已用交换空间(M):" + usedSwapSpaceSize / MB);
//            System.out.println("剩余交换空间(M):" + freeSwapSpaceSize / MB);

            systemDetailBean.setTotalSwapSpace(totalSwapSpaceSize / MB + "M");
            systemDetailBean.setUsedSwapSpace(usedSwapSpaceSize / MB + "M");
            systemDetailBean.setFreeSwapSpace(freeSwapSpaceSize / MB + "M");

        }

        return systemDetailBean;
    }

    private static boolean isSunOsMBean(OperatingSystemMXBean operatingSystem) {
        final String className = operatingSystem.getClass().getName();
        return "com.sun.management.OperatingSystem".equals(className)
                || "com.sun.management.UnixOperatingSystem".equals(className);
    }

    private static long getLongFromOperatingSystem(OperatingSystemMXBean operatingSystem, String methodName) {
        try {
            final Method method = operatingSystem.getClass().getMethod(methodName,
                    (Class<?>[]) null);
            method.setAccessible(true);
            return (Long) method.invoke(operatingSystem, (Object[]) null);
        } catch (final InvocationTargetException e) {
            if (e.getCause() instanceof Error) {
                throw (Error) e.getCause();
            } else if (e.getCause() instanceof RuntimeException) {
                throw (RuntimeException) e.getCause();
            }
            throw new IllegalStateException(e.getCause());
        } catch (final NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        } catch (final IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }


}
