package cn.kevin.controller.rest;

import cn.kevin.dao.TaskListMapper;
import cn.kevin.domain.TaskList;
import cn.kevin.domain.query.TaskListQuery;
import cn.kevin.util.Constants;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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



    @PostMapping("/done")
    public String done(@Param(value = "id") int id) {
        try {
            TaskList taskList = taskListMapper.selectByPrimaryKey(id);
            if (taskList == null) {
                return "此任务不存在!";
            }
            taskList.setUpdateDate(new Date());
            taskList.setState("1");
            taskListMapper.updateByPrimaryKey(taskList);
        } catch (Exception e) {

        }
        return Constants.SUCCESS;
    }

    /**
     * 查询未完成的任务
     * @return
     */
    @GetMapping(value = "/selectNonFinish")
    public List<TaskList> selectNonFinish() {
        List<TaskList> taskLists = taskListMapper.selectByState("0");
        return taskLists;
    }

    /**
     * 查询已完成的任务
     * @return
     */
    @GetMapping(value = "/selectFinished")
    public List<TaskList> selectFinished() {
        List<TaskList> taskLists = taskListMapper.selectByState("1");
        return taskLists;
    }



    @PostMapping(value = "saveUpdate")
    public String saveOrUpdate(@Param(value = "id") int id, @Param(value = "label") String label,
                               @Param(value = "content") String content) {
        String resultMsg;
        if (id == 0) {
            resultMsg = save(label, content);
        } else {
            resultMsg = updateTaskList(label, content, id);
        }

        return resultMsg;
    }

    /**
     * 从saveUpdate接收然后delegate到这个方法
     * @param label
     * @param content
     * @return
     */
    private String save(String label, String content) {
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

    /**
     * 更新
     * @return
     */
    private String updateTaskList(String label, String content,
                                 int id) {
        String resultMsg;
        int updateCnt = 0;
        if (id != 0) {
            TaskList taskList = taskListMapper.selectByPrimaryKey(id);
            taskList.setLabel(label);
            taskList.setContent(content);
            updateCnt = taskListMapper.updateByPrimaryKey(taskList);
        }
        resultMsg = updateCnt == 1 ? "更新成功" : "更新失败";
        log.info("更新id为{}的taskList的返回信息是: {}", id, resultMsg);
        return resultMsg;
    }

    @PostMapping(value = "/query")
    public List<TaskList> getByTaskListQuery(@ModelAttribute(value = "taskListQuery")TaskListQuery query) {
        List<TaskList> list;
        log.info("执行方法{}, 参数是: {}", "getByTaskListQuery", query.toString());
        list = taskListMapper.selectByTaskListQuery(query);
        return list;
    }
}
