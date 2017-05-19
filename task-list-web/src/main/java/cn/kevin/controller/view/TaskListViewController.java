package cn.kevin.controller.view;

import cn.kevin.dao.TaskLabelMapper;
import cn.kevin.dao.TaskListMapper;
import cn.kevin.util.ThreadLocalHelper;
import com.google.common.base.Strings;
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

    @Autowired
    private TaskLabelMapper taskLabelMapper;

    @RequestMapping(value = "/list")
    public ModelAndView taskList() {
        //对登录进行判断，没有登录的话，跳到登录页面
        String name = ThreadLocalHelper.get();
        if (Strings.isNullOrEmpty(name)) {
            return new ModelAndView("redirect:/login/index");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("task-list");
        modelAndView.addObject("tasks", taskListMapper.selectByState("0"));
        modelAndView.addObject("labels", taskLabelMapper.selectAll());
        return modelAndView;
    }


}
