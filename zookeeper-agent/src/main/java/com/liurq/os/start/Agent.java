package com.liurq.os.start;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liurq.os.bean.AgentBean;
import com.liurq.os.bean.OsBean;
import com.liurq.os.bean.SystemDetailBean;
import com.liurq.os.utils.*;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class Agent implements ApplicationRunner {
    private static Agent ourInstance = new Agent();
    private String server = "101.42.108.28:12181";
    private ZkClient zkClient;
    private static final String rootPath = "/liurq";
    private static final String servicePath = rootPath + "/service";
    private String nodePath; //liurq/service0000001 当前节点路径
    private Thread stateThread;


    public static Agent getInstance() {
        return ourInstance;
    }

    private Agent() {
    }

    // javaagent 数据监控
    public static void premain(String args, Instrumentation instrumentation) {
        Agent.getInstance().init();
    }

    public void init() {
        zkClient = new ZkClient(server, 5000, 10000);
//        zkClient.setZkSerializer(new SerializableSerializer());
        System.out.println("zk连接成功" + server);
        // 创建根节点
        buildRoot();
        // 创建临时节点
        createServerNode();
        // 启动更新的线程
        stateThread = new Thread(() -> {
            while (true) {
                updateServerNode();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "zk_stateThread");
        stateThread.setDaemon(true);
        stateThread.start();
    }

    // 数据写到 当前的临时节点中去
    public void updateServerNode() {
        zkClient.writeData(nodePath, getOsInfo());
    }

    // 生成服务节点
    public void createServerNode() {
        nodePath = zkClient.createEphemeralSequential(servicePath, getOsInfo());
        System.out.println("创建节点:" + nodePath);
    }

    // 更新服务节点状态
    public String getOsInfo() {
/*        OsBean bean = new OsBean();
        bean.lastUpdateTime = System.currentTimeMillis();
        bean.ip = getLocalIp();
//        bean.cpu = CPUMonitorCalc.getInstance().getProcessCpu();
        MemoryUsage memoryUsag = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        bean.usedMemorySize = memoryUsag.getUsed() / 1024 / 1024;
        bean.usableMemorySize = memoryUsag.getMax() / 1024 / 1024;
        bean.pid = ManagementFactory.getRuntimeMXBean().getName();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String s = mapper.writeValueAsString(bean);
            System.out.println(s);
            return s;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }*/
        MemoryUsage memoryUsag = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        AgentBean agentBean = new AgentBean();
        agentBean.setCompilationDetailBean(CompilationDetailUtils.getCompilationDetail());
        agentBean.setGarbageCollectorInfoBean(GarbageCollectorInfoUtils.getGarbageCollectorInfo());
        agentBean.setJvmMemoryInfoBean(JvmMemoryInfoUtils.getJvmMemoryInfo());
        agentBean.setRuntimeDetailBean(RuntimeDetailUtils.getRuntimeDetail());
        agentBean.setThreadInfoBean(ThreadInfoUtils.getThreadInfo());
        agentBean.setSystemDetailBean(SystemDetailUtils.getSystemInfo());
        return JSONUtil.toJsonStr(agentBean);
    }

    public void buildRoot() {
        if (!zkClient.exists(rootPath)) {
            zkClient.createPersistent(rootPath);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

//        zkClient = new ZkClient(server, 5000, 10000);
////        zkClient.setZkSerializer(new SerializableSerializer());
//        System.out.println("zk连接成功" + server);
//        // 创建根节点
//        buildRoot();
//        // 创建临时节点
//        createServerNode();
//
//        SystemDetailBean systemInfo = SystemDetailUtils.getSystemInfo();
//        System.out.println(systemInfo.toString());
//        zkClient.writeData(nodePath, JSONUtil.toJsonStr(systemInfo));

//        String osInfo = this.getOsInfo();
//        System.out.println(osInfo);
        this.init();
    }
}
