package cn.kevin.controller.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yongkang.zhang on 2017/5/9.
 */
@Controller
@Slf4j
public class taskListController {

    @RequestMapping(value = "/taskList")
    public String taskList() {
        log.info("开始执行方法: {}", "taskList");
        return "task-list";
    }


}
