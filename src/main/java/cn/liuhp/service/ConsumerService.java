package cn.liuhp.service;

import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ConsumerService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public Map<String, Object> getSome() {
        ServiceInstance si = loadBalancerClient.choose("eureka-server-provider");
        StringBuilder sb = new StringBuilder();
        sb.append("http://")
                .append(si.getHost()).append(":")
                .append(si.getPort())
                .append("/helloBoot?name=consumer");
        //springmvc 的restTemplate
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<Map<String, Object>> type = new
                ParameterizedTypeReference<Map<String, Object>>() {};
        ResponseEntity<Map<String, Object>> responseEntity =
                restTemplate.exchange(sb.toString(), HttpMethod.GET, null, type);
        Map<String, Object> map = responseEntity.getBody();
        return map;
    }

}
