package cn.zko0.remotelabel.controller;

import cn.zko0.remotelabel.entity.Collector;
import cn.zko0.remotelabel.service.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duanfuqiang
 * @date 2023/2/27 10:35
 * @description
 */
@RestController
@RequestMapping("/collector")
public class CollectorController {

    @Autowired
    private CollectorService collectorService;


    @RequestMapping("/add")
    public void addCollector(@RequestBody Collector collector){
        collectorService.addCollector(collector);
    }

    @RequestMapping("/delete")
    public void deleteCollector(@RequestBody Collector collector){
        collectorService.deleteCollector(collector);
    }

    @RequestMapping("/getExist")
    public Boolean getExist(@RequestBody String collectorId){
        return collectorService.getExist(collectorId);
    }
}
