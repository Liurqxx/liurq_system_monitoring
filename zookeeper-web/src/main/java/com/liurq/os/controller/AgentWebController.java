package com.liurq.os.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.liurq.os.bean.AgentBean;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author liurq
 * @desc
 * @date 2022-02-22  21:53
 */
@RestController
public class AgentWebController implements InitializingBean {

    @Value("${zk:101.42.108.28:12181}")
    private String server;
    private ZkClient zkClient;
    private static final String rootPath = "/liurq";
    Map<String, AgentBean> map = new HashMap<>();

    @ResponseBody
    @RequestMapping("/list")
    public List<AgentBean> list() {
//        model.addAttribute("items", getCurrentOsBeans());
        return getCurrentOsBeans();
    }

    private List<AgentBean> getCurrentOsBeans() {
        List<AgentBean> items = zkClient.getChildren(rootPath).stream()
                .map(p -> rootPath + "/" + p)
                .map(p -> convert(zkClient.readData(p)))
                .collect(Collectors.toList());
        return items;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        zkClient = new ZkClient(server, 5000, 90000);
        initSubscribeListener();

    }

    // 初始化订阅事件
    public void initSubscribeListener() {
        zkClient.unsubscribeAll();
        // 获取所有子节点
        zkClient.getChildren(rootPath)
                .stream()
                .map(p -> rootPath + "/" + p)// 得出子节点完整路径
                .forEach(p -> {
                    zkClient.subscribeDataChanges(p, new DataChanges());// 数据变更的监听
                });
        //  监听子节点，的变更 增加，删除
        zkClient.subscribeChildChanges(rootPath, (parentPath, currentChilds) -> initSubscribeListener());
    }

    private AgentBean convert(String json) {
        try {
            return JSONUtil.toBean(json, AgentBean.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 子节点数据变化
    private class DataChanges implements IZkDataListener {

        @Override
        public void handleDataChange(String dataPath, Object data) throws Exception {
            AgentBean bean = JSONUtil.toBean(new String(data.toString()), AgentBean.class);
            map.put(dataPath, bean);
            doFilter(bean);
        }

        @Override
        public void handleDataDeleted(String dataPath) throws Exception {
            if (map.containsKey(dataPath)) {
                AgentBean bean = map.get(dataPath);
                System.err.println("服务已下线:" + bean);
                map.remove(dataPath);
            }
        }
    }

    // 警告过滤
    private void doFilter(AgentBean bean) {
        // cpu 超过10% 报警
        if (bean.getCpu() > 80) {
            System.err.println("CPU 报警..." + bean.getCpu());
        }
    }
}
