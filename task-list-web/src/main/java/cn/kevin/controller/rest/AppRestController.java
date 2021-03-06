package cn.kevin.controller.rest;

import cn.kevin.common.enums.VultrRequestTypeEnum;
import cn.kevin.domain.AppPlan;
import cn.kevin.domain.AppPlanHistory;
import cn.kevin.domain.AppUpWord;
import cn.kevin.domain.dto.VultrDomain;
import cn.kevin.helper.ResponseResultWrapper;
import cn.kevin.service.AppPlanService;
import cn.kevin.service.AppUpWordService;
import cn.kevin.service.VultrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    private final VultrService vultrService;

    @Autowired
    public AppRestController(AppPlanService appPlanService, AppUpWordService appUpWordService, VultrService vultrService) {
        this.appPlanService = appPlanService;
        this.appUpWordService = appUpWordService;
        this.vultrService = vultrService;
    }

    @RequestMapping(value = "/listAllPlan", method = RequestMethod.GET)
    public List<AppPlan> listAll() {
         return appPlanService.listAll();
    }


    @RequestMapping(value = "/changePlan/{planId}", method = RequestMethod.PUT)
    public Boolean changePlan(@PathVariable(value = "planId") Long planId) {
        appPlanService.changePlan(planId);
        return Boolean.TRUE;
    }

    @RequestMapping(value = "/giveUp/{planId}", method = RequestMethod.PUT)
    public Boolean giveUp(@PathVariable(value = "planId") Long planId) {
        appPlanService.giveUp(planId);
        return Boolean.TRUE;
    }

    @RequestMapping(value = "/updatePlan", method = RequestMethod.PUT)
    public Boolean updatePlan(@RequestBody AppPlan plan) {
        appPlanService.update(plan);
        return Boolean.TRUE;
    }

    @GetMapping(value = "/listRecentPlan")
    public List<AppPlan> listRecentPlan() {
        return appPlanService.listRecent();
    }

    @PutMapping(value = "/deleteRecentPlan")
    public Boolean deleteRecentPlan() {
        return appPlanService.deleteRecent();
    }

    @GetMapping(value = "/getCurrent")
    public AppPlan getCurrent() {
        return appPlanService.getCurrent();
    }

    @GetMapping(value = "/isCheck")
    public Boolean isCheck(@RequestParam(value = "planId") Long planId, @RequestParam(value = "checkDate") Date checkDate) {
        return appPlanService.isCheck(planId, checkDate);
    }

    @PutMapping(value = "/check")
    public Boolean checkPlan(@RequestBody AppPlan plan) {
        return appPlanService.check(plan);
    }

    @GetMapping(value = "/listHistory/{planId}")
    public List<AppPlanHistory> listHistory(@PathVariable(value = "planId") Long planId) {
        return appPlanService.listByPlanId(planId);
    }

    @GetMapping(value = "/listAllUpWord")
    public List<AppUpWord> listAllUpWord() {
        return appUpWordService.listAll();
    }

    @PutMapping(value = "/deleteUpWord/{id}")
    public Boolean deleteUpWord(@PathVariable(value = "id") Long id) {
        return appUpWordService.delete(id);
    }

    @PutMapping(value = "/save")
    public Boolean save(@RequestBody AppUpWord word) {
        return appUpWordService.save(word);
    }

    @GetMapping(value = "/getVultr/{type}")
    public VultrDomain getVultr(@PathVariable(value = "type") Integer type) {
        return vultrService.get(VultrRequestTypeEnum.getByCode(type));
    }
}
