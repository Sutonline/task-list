package cn.kevin.controller.rest;

import cn.kevin.dao.TaskListMapper;
import cn.kevin.domain.TaskList;
import cn.kevin.util.Constants;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by yongkang.zhang on 2017/5/18.
 */
@RestController
@RequestMapping("/taskList")
@Slf4j
public class taskListRestController {

    @Autowired
    private TaskListMapper taskListMapper;

    @PostMapping("/save")
    public String save(@Param(value = "label") String label, @Param(value = "content") String content) {
        try {
            if (Strings.isNullOrEmpty(content) || Strings.isNullOrEmpty(label)) {
                log.error("参数不合法!");
                return "参数不合法!";
            }
            TaskList taskList = new TaskList();
            taskList.setLabel(label);
            taskList.setContent(content);
            taskList.setCreateDate(new Date());
            taskList.setState("0");
            taskListMapper.insert(taskList);
        } catch (Exception e) {
            log.error("保存taskList出错", e);
            e.printStackTrace();
        }
        return Constants.SUCCESS;
    }

    @PostMapping("/done")
    public String done(@Param(value = "id") int id) {
        try {
            TaskList taskList = taskListMapper.selectByPrimaryKey(id);
            if (taskList == null) {
                return "此任务不存在!";
            }
            taskList.setState("1");
            taskListMapper.updateByPrimaryKey(taskList);
        } catch (Exception e) {

        }
        return Constants.SUCCESS;
    }

    @GetMapping(value = "/selectNonFinish")
    public List<TaskList> selectNonFinish() {
        List<TaskList> taskLists = taskListMapper.selectByState("0");
        return taskLists;
    }

    @GetMapping(value = "/selectFinished")
    public List<TaskList> selectFinished() {
        List<TaskList> taskLists = taskListMapper.selectByState("1");
        return taskLists;
    }

}
