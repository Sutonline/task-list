package cn.kevin.service;

import cn.kevin.domain.Activity;
import cn.kevin.domain.query.ActivityQuery;

import java.util.List;

/**
 * 活动实现类
 * Created by yongkang.zhang on 2017/8/11.
 */
public interface ActivityService {

    /**
     * 单独插入一个activity
     * @param activity activity
     * @return 插入行数
     */
    int insert(Activity activity);

    /**
     * 根据activityId进行更新
     * @param activity 更新对象
     * @return 更新结果
     */
    int update(Activity activity);

    /**
     * 根据唯一id查询
     * @param activityId 活动id
     * @return 活动对象
     */
    Activity getByActivityId(Integer activityId);

    /**
     * 根据activity_id进行查询
     * @param activityId 活动id
     * @return 活动对象
     */
    Activity getActivityAndNoes(Integer activityId);

    /**
     * 根据查询对象进行获取
     * @param activityQuery 查询对象
     * @return activity的列表
     */
    List<Activity> listByQuery(ActivityQuery activityQuery);
}
