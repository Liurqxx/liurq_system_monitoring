package com.liurq.os.bean;

import lombok.Data;

/**
 *
 */
@Data
public class OsBean implements java.io.Serializable {
    public String ip;
    public Double cpu;
    public long usedMemorySize;
    public long usableMemorySize;
    public String pid;
    public long lastUpdateTime;

}
