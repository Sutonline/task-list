package cn.kevin.controller.rest;

import cn.kevin.dao.TaskLabelMapper;
import cn.kevin.domain.TaskLabel;
import cn.kevin.domain.page.Page;
import cn.kevin.domain.page.PageRequest;
import cn.kevin.helper.WrapperResponseBody;
import cn.kevin.dao.TaskListMapper;
import cn.kevin.domain.TaskList;
import cn.kevin.domain.query.TaskListQuery;
import cn.kevin.util.Constants;
import com.google.common.base.Strings;
import javafx.concurrent.Task;
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
@WrapperResponseBody
public class TaskListRestController {

    @Autowired
    private TaskListMapper taskListMapper;

    private TaskLabelMapper labelMapper;

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
    @RequestMapping(value = "/selectNonFinish")
    @WrapperResponseBody
    public List<TaskList> selectNonFinish() {
        List<TaskList> taskLists = taskListMapper.selectByState("0");
        if (taskLists == null || taskLists.isEmpty()) {
            TaskList taskList = new TaskList();
            taskList.setContent("xxxx");
            taskList.setLabel("xxx");
            taskLists.add(taskList);
        }

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

    @GetMapping("/getLabels")
    public List<TaskLabel> getAllLabels() {
        log.info("执行方法{}", "getAllLabels");
        return labelMapper.selectAll();
    }

    /**
     * @param query
     * @param pageRequest
     * @return
     */
    @GetMapping("/listByPage")
    public Page<TaskList> listByPage(TaskListQuery query,
                                     PageRequest pageRequest) {
        if (query == null || query.getState() == null) {
            query = new TaskListQuery();
            query.setState("1");
        }
        log.info("执行方法listByPage，参数是{}, 分页参数是{}", query.toString(), pageRequest.toString());
        Page<TaskList> page = new Page<>();
        page.setList(taskListMapper.listByPage(query, pageRequest));
        page.setPageIndex(pageRequest.getPageIndex());
        page.setPageSize(pageRequest.getPageSize());
        page.setTotalItems(taskListMapper.countByQuery(query));
        return page;
    }


    @Autowired
    public void setLabelMapper(TaskLabelMapper labelMapper) {
        this.labelMapper = labelMapper;
    }
}
