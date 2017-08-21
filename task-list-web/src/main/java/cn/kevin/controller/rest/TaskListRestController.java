package cn.kevin.controller.rest;

import cn.kevin.dao.ActivityNodeMapper;
import cn.kevin.dao.TaskLabelMapper;
import cn.kevin.dao.TaskListMapper;
import cn.kevin.domain.Activity;
import cn.kevin.domain.ActivityNode;
import cn.kevin.domain.TaskLabel;
import cn.kevin.domain.TaskList;
import cn.kevin.domain.page.Page;
import cn.kevin.domain.page.PageRequest;
import cn.kevin.domain.query.ActivityQuery;
import cn.kevin.domain.query.TaskListQuery;
import cn.kevin.helper.WrapperResponseBody;
import cn.kevin.service.ActivityService;
import cn.kevin.util.Constants;
import cn.kevin.util.ValidationUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * task list controller
 * Created by yongkang.zhang on 2017/5/18.
 */
@RestController
@RequestMapping("/taskList")
@Slf4j
@WrapperResponseBody
public class TaskListRestController {

    private final TaskListMapper taskListMapper;
    private TaskLabelMapper labelMapper;
    private ActivityService activityService;
    private ActivityNodeMapper activityNodeMapper;

    @Autowired
    public TaskListRestController(TaskListMapper taskListMapper, ActivityService activityService, ActivityNodeMapper activityNodeMapper) {
        this.taskListMapper = taskListMapper;
        this.activityService = activityService;
        this.activityNodeMapper = activityNodeMapper;
    }

    @PostMapping("/done")
    @ApiOperation(value = "完成任务")
    @ApiImplicitParam(name = "id", value = "任务id")
    public String done(@Param(value = "id") int id) {
        try {
            TaskList taskList = taskListMapper.selectByPrimaryKey(id);
            if (taskList == null) {
                return "此任务不存在";
            }
            taskList.setUpdateDate(new Date());
            taskList.setState("1");
            taskListMapper.updateByPrimaryKey(taskList);
        } catch (Exception e) {
            log.error("{}任务完成错误", id, e);
            return "完成错误";
        }
        return Constants.SUCCESS;
    }

    /**
     * 查询未完成的任务
     * @return task list
     */
    @RequestMapping(value = "/selectNonFinish")
    public List<TaskList> selectNonFinish() {
        List<TaskList> taskLists = taskListMapper.selectByState("0");
        if (taskLists == null || taskLists.isEmpty()) {
            TaskList taskList = new TaskList();
            taskList.setContent("xxxx");
            taskList.setLabel("xxx");
            assert taskLists != null;
            taskLists.add(taskList);
        }

        return taskLists;
    }

    /**
     * 查询已完成的任务
     * @return 任务列表
     */
    @GetMapping(value = "/selectFinished")
    public List<TaskList> selectFinished() {
        return taskListMapper.selectByState("1");
    }



    @PostMapping(value = "/saveUpdate")
    public String saveOrUpdate(@RequestParam(value = "id") int id, @RequestParam(value = "label") String label,
                               @RequestParam(value = "content") String content) {
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
     * @param label 标签
     * @param content 内容
     * @return 更新结果
     */
    private String save(String label, String content) {
        try {
            if (Strings.isNullOrEmpty(content) || Strings.isNullOrEmpty(label)) {
                log.error("参数不合法");
                return "参数不合法";
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
     * @return 更新结果
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
     * @param query 查询
     * @param pageRequest 分页信息
     * @return 返回结果
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

    @GetMapping("/listActivities")
    public List<Activity> listActivities(ActivityQuery query) {
        return activityService.listByQuery(query);
    }

    @GetMapping(value = "/getActivityAndNodes")
    public Activity getActivityAndNodes(Integer activityId) {
        return activityService.getActivityAndNoes(activityId);
    }

    @RequestMapping(value = "/saveActivity", method = RequestMethod.POST)
    public String saveActivity(String activityJson) {
        Activity activity = JSON.parseObject(activityJson, Activity.class);
        String validate = ValidationUtil.validate(activity);
        if (validate != null) {
            return validate;
        }
        activityService.addCreateInformation(activity);
        int cnt = activityService.insert(activity);
        if (cnt == 1) {
            return "保存成功";
        } else {
            return "保存失败";
        }
    }

    @PutMapping(value = "/saveNode")
    public String saveActivityNode(ActivityNode activityNode) {
        int cnt = activityNodeMapper.insert(activityNode);
        if (cnt == 1) {
            return "保存成功";
        } else {
            return "保存失败";
        }
    }

    @DeleteMapping(value = "/deleteActivity/{activityId}")
    public String deleteActivity(@PathVariable Integer activityId) {
        activityService.deleteActivityAndNode(activityId);
        return "删除成功";
    }

    @PutMapping(value = "/completeNode/{nodeId}")
    public String completeNode(@PathVariable Integer nodeId) {
        activityService.completeActivityNode(nodeId);
        return "完成成功";
    }

    @PutMapping(value = "/updateActivity")
    public String updateActivity(Activity activity) {
        int cnt = activityService.update(activity);
        if (cnt == 1) {
            return "更新成功";
        } else {
            return "更新失败";
        }
    }

    @PutMapping(value = "/updateActivityNode")
    public String updateActivityNode(ActivityNode activityNode) {
        int cnt = activityNodeMapper.updateByPrimaryKey(activityNode);
        if (cnt == 1) {
            return "更新成功";
        } else {
            return "更新失败";
        }
    }

    @Autowired
    public void setLabelMapper(TaskLabelMapper labelMapper) {
        this.labelMapper = labelMapper;
    }

}
