package cn.kevin.controller.view;

import cn.kevin.dao.TaskListMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yongkang.zhang on 2017/5/9.
 */
@Controller
@Slf4j
@RequestMapping("/taskList")
public class TaskListViewController {

    @Autowired
    private TaskListMapper taskListMapper;

    @RequestMapping(value = "/list")
    public ModelAndView taskList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("task-list");
        modelAndView.addObject("tasks", taskListMapper.selectByState("0"));
        return modelAndView;
    }


}
