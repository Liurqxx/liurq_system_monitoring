package com.liurq.os.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liurq
 * @desc
 * @date 2022-02-22  21:39
 */
@Data
public class AgentBean implements Serializable {

    private SystemDetailBean systemDetail;

    private CompilationDetailBean compilationDetail;

    private List<GarbageCollectorInfoBean> garbageCollectorInfo;

    private JvmMemoryInfoBean jvmMemoryInfo;

    private RuntimeDetailBean runtimeDetail;

    private ThreadInfoBean threadInfo;

}
