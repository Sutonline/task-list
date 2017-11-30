package cn.kevin.controller.rest;

import cn.kevin.domain.AppPlan;
import cn.kevin.domain.AppUpWord;
import cn.kevin.helper.ResponseResultWrapper;
import cn.kevin.service.AppPlanService;
import cn.kevin.service.AppUpWordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * app rest controller
 * created by yongkang.zhang
 * added at 2017/11/30
 */
@RestController
@RequestMapping("/app")
@Slf4j
@ResponseResultWrapper
@SuppressWarnings("unchecked")
public class AppRestController {

    private final AppPlanService appPlanService;
    private final AppUpWordService appUpWordService;

    @Autowired
    public AppRestController(AppPlanService appPlanService, AppUpWordService appUpWordService) {
        this.appPlanService = appPlanService;
        this.appUpWordService = appUpWordService;
    }

    @RequestMapping(value = "listAllPlan", method = RequestMethod.GET)
    public List<AppPlan> listAll() {
         return appPlanService.listAll();
    }


    @RequestMapping(value = "/changePlan/{planId}", method = RequestMethod.PUT)
    public Boolean changePlan(@PathVariable(value = "planId") Long planId) {
        appPlanService.changePlan(planId);
        return Boolean.TRUE;
    }

    @RequestMapping(value = "/giveUp/${planId}", method = RequestMethod.PUT)
    public Boolean giveUp(@PathVariable(value = "planId") Long planId) {
        appPlanService.giveUp(planId);
        return Boolean.TRUE;
    }

    @RequestMapping(value = "/updatePlan", method = RequestMethod.PUT)
    public Boolean updatePlan(AppPlan plan) {
        appPlanService.update(plan);
        return Boolean.TRUE;
    }

    @GetMapping(value = "/listRecentPlan")
    public List<AppPlan> listRecentPlan() {
        return appPlanService.listRecent();
    }

    @GetMapping(value = "/deleteRecentPlan")
    public Boolean deleteRecentPlan() {
        return appPlanService.deleteRecent();
    }

    @GetMapping(value = "/listAllUpWord")
    public List<AppUpWord> listAllUpWord() {
        return appUpWordService.listAll();
    }

    @PutMapping(value = "/deleteUpWord/{{id}")
    public Boolean deleteUpWord(@PathVariable(value = "id") Long id) {
        return appUpWordService.delete(id);
    }

    @PutMapping(value = "/save")
    public Boolean save(AppUpWord word) {
        return appUpWordService.save(word);
    }

}
