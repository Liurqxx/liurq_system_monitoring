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

    public String ip;
    public Double cpu;
    public long usedMemorySize;
    public long usableMemorySize;
    public String pid;

    private SystemDetailBean systemDetailBean;

    private CompilationDetailBean compilationDetailBean;

    private List<GarbageCollectorInfoBean> garbageCollectorInfoBean;

    private JvmMemoryInfoBean jvmMemoryInfoBean;

    private RuntimeDetailBean runtimeDetailBean;

    private ThreadInfoBean threadInfoBean;

}
