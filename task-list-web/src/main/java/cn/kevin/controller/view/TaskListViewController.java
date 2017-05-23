package cn.kevin.controller.view;

import cn.kevin.dao.TaskLabelMapper;
import cn.kevin.dao.TaskListMapper;
import cn.kevin.domain.TaskLabel;
import cn.kevin.domain.TaskList;
import cn.kevin.domain.query.TaskListQuery;
import cn.kevin.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    public ModelAndView taskList(HttpSession session) {
        //对登录进行判断，没有登录的话，跳到登录页面
        String name = (String) session.getAttribute(Constants.LOGIN_USER);
        /*if (Strings.isNullOrEmpty(name)) {
            return new ModelAndView("redirect:/login/index");
        }*/
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("task-list");
        modelAndView.addObject("tasks", taskListMapper.selectByState("0"));
        modelAndView.addObject("labels", taskLabelMapper.selectAll());
        return modelAndView;
    }

    /**
     * taskListQuery
     * @param taskListQuery
     * @return
     */
    @GetMapping(value = "/history")
    public ModelAndView history(@ModelAttribute(value = "taskListQuery")TaskListQuery taskListQuery) {
        ModelAndView modelAndView = new ModelAndView("history");
        modelAndView.addObject("labels", getSelectedLable(taskLabelMapper.selectAll()));
        return modelAndView;
    }

    @PostMapping(value = "/historySearch")
    public ModelAndView historySearch(@ModelAttribute(value = "taskListQuery") TaskListQuery taskListQuery) {
        ModelAndView modelAndView = new ModelAndView();
        if (taskListQuery != null && taskListQuery.getOrderByClause() == null) {
            taskListQuery.setOrderByClause("update_date desc");
        }
        log.info("执行方法{}, 参数是: {}", "getByTaskListQuery", taskListQuery.toString());
        List<TaskList> list = taskListMapper.selectByTaskListQuery(taskListQuery);
        modelAndView.addObject("tasks", list);
        modelAndView.addObject("labels", getSelectedLable(taskLabelMapper.selectAll()));
        modelAndView.setViewName("history");
        return modelAndView;
    }

    /**
     * 为list增加全部的label
     * @param list
     * @return
     */
    private List<TaskLabel> getSelectedLable(List<TaskLabel> list) {
        list = taskLabelMapper.selectAll();
        TaskLabel taskLabel = new TaskLabel();
        taskLabel.setValue("全部");
        list.add(taskLabel);
        return list;
    }


}
