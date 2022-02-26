package com.liurq.os.start;

import cn.hutool.json.JSONUtil;
import com.liurq.os.bean.AgentBean;
import com.liurq.os.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.lang.instrument.Instrumentation;

@Slf4j
@Component
public class Agent implements ApplicationRunner {
    private static Agent ourInstance = new Agent();
    private String server = "127.0.0.1:12181";
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

    /**
     * 注册节点，开启定时任务更新节点数据
     */
    public void init() {
        zkClient = new ZkClient(server, 5000, 10000);
//        zkClient.setZkSerializer(new SerializableSerializer());
        log.info("zk客户端连接成功-{}", server);
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

    /**
     * 数据写到当前的临时节点中去
     */
    public void updateServerNode() {
        zkClient.writeData(nodePath, getOsInfo());
    }

    /**
     * 生成服务节点
     */
    public void createServerNode() {
        nodePath = zkClient.createEphemeralSequential(servicePath, getOsInfo());
        log.info("创建节点成功-{}", nodePath);
    }

    /**
     * 更新服务节点状态
     *
     * @return
     */
    public String getOsInfo() {
        AgentBean agentBean = new AgentBean();
        agentBean.setCompilationDetail(CompilationDetailUtils.getCompilationDetail());
        agentBean.setGarbageCollectorInfo(GarbageCollectorInfoUtils.getGarbageCollectorInfo());
        agentBean.setJvmMemoryInfo(JvmMemoryInfoUtils.getJvmMemoryInfo());
        agentBean.setRuntimeDetail(RuntimeDetailUtils.getRuntimeDetail());
        agentBean.setThreadInfo(ThreadInfoUtils.getThreadInfo());
        agentBean.setSystemDetail(SystemDetailUtils.getSystemInfo());
        return JSONUtil.toJsonStr(agentBean);
    }

    public void buildRoot() {
        if (!zkClient.exists(rootPath)) {
            zkClient.createPersistent(rootPath);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        this.init();
    }
}
