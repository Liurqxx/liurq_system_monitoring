package com.liurq.os.utils;

import com.liurq.os.bean.GarbageCollectorInfoBean;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liurq
 * @desc 垃圾收集器工具类
 * @date 2022-02-22  21:24
 */
public class GarbageCollectorInfoUtils {

    public static List<GarbageCollectorInfoBean> getGarbageCollectorInfo() {

        List<GarbageCollectorInfoBean> garbageCollectorInfoBeans = new ArrayList<>();

        List<GarbageCollectorMXBean> garbages = ManagementFactory.getGarbageCollectorMXBeans();
        garbages.stream().forEach(v -> {

            GarbageCollectorInfoBean garbageCollectorInfoBean = new GarbageCollectorInfoBean();
            garbageCollectorInfoBean.setName(v.getName());
            garbageCollectorInfoBean.setCollectionCount(v.getCollectionCount());
            garbageCollectorInfoBean.setCollectionTime(v.getCollectionTime());
            garbageCollectorInfoBean.setMemoryPoolNames(v.getMemoryPoolNames());
            garbageCollectorInfoBeans.add(garbageCollectorInfoBean);
        });
//        for (GarbageCollectorMXBean garbage : garbages) {
//            System.out.println("垃圾收集器：名称=" + garbage.getName() + ",收集=" + garbage.getCollectionCount() + ",总花费时间="
//                    + garbage.getCollectionTime() + ",内存区名称=" + Arrays.deepToString(garbage.getMemoryPoolNames()));
//        }
        return garbageCollectorInfoBeans;
    }


}
