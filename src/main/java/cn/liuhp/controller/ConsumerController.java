package cn.liuhp.controller;

import cn.liuhp.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 消费服务controller
 * @author: liuhp534
 * @create: 2019-03-17 22:41
 */
@RestController
public class ConsumerController {


    @Autowired
    private ConsumerService consumerService;

    @RequestMapping(value = "consumer")
    public Map<String, Object> consumer() {
        Map<String, Object> map = this.consumerService.getSome();
        if (null == map) {
            map = new HashMap<>();
            map.put("value", "没有获取到");
        }
        return  map;
    }
}
