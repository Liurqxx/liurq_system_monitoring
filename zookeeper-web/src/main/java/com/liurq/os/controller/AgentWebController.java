package com.liurq.os.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.liurq.os.bean.AgentBean;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author liurq
 * @desc
 * @date 2022-02-22  21:53
 */
@Slf4j
@Controller
public class AgentWebController implements InitializingBean {

    @Value("${zk:101.42.108.28:12181}")
    private String server;
    private ZkClient zkClient;
    private static final String rootPath = "/liurq";
    Map<String, AgentBean> map = new HashMap<>();

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("items", getCurrentOsBeans());
        return "index";
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

    /**
     * 初始化订阅事件
     */
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

    /**
     * 数据格式转化
     *
     * @param json
     * @return
     */
    private AgentBean convert(String json) {
        try {
            return JSONUtil.toBean(json, AgentBean.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 监控子节点变化
     */
    private class DataChanges implements IZkDataListener {

        /**
         * 节点数据变换回调
         *
         * @param dataPath
         * @param data
         * @throws Exception
         */
        @Override
        public void handleDataChange(String dataPath, Object data) throws Exception {
            log.info("节点-{}，数据发生了改变!!", dataPath);
            AgentBean bean = JSONUtil.toBean(new String(data.toString()), AgentBean.class);
            map.put(dataPath, bean);
            doFilter(bean);
        }

        /**
         * 服务下线回调
         *
         * @param dataPath
         * @throws Exception
         */
        @Override
        public void handleDataDeleted(String dataPath) throws Exception {
            if (map.containsKey(dataPath)) {
                AgentBean bean = map.get(dataPath);
                log.info("服务已下线-{}", bean);
                map.remove(dataPath);
            }
        }
    }

    /**
     * 警告过滤
     *
     * @param bean
     */
    private void doFilter(AgentBean bean) {
        // cpu 超过80% 报警
        System.out.println();
        if (bean.getSystemDetail().getCpu() > 80) {
            log.error("CPU数值-{}到达预警值！！！", bean.getSystemDetail().getCpu());
        }
    }
}
