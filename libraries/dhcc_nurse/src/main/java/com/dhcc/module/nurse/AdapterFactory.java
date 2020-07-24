package com.dhcc.module.nurse;

import com.dhcc.module.nurse.education.adapter.HealthEduAddAdapter;
import com.dhcc.module.nurse.education.adapter.HealthEduEndAdapter;
import com.dhcc.module.nurse.education.adapter.HealthEduItemAdapter;
import com.dhcc.module.nurse.education.adapter.HealthEduNeedAdapter;

/**
 * 管理适配器
 * @author:gaoruishan
 * @date:202019-04-28/11:05
 * @email:grs0515@163.com
 */
public class AdapterFactory {
    /**
     * 健康宣教(已宣教)
     * @return
     */
    public static HealthEduEndAdapter getHealthEducationAdapter() {
        return new HealthEduEndAdapter(R.layout.item_health_education, null);
    }
    /**
     * 健康宣教(需宣教)
     * @return
     */
    public static HealthEduNeedAdapter getHealthEducationNeedAdapter() {
        return new HealthEduNeedAdapter(R.layout.item_health_education, null);
    }

    /**
     * 添加宣教
     * @return
     */
    public static HealthEduAddAdapter getHealthEduAddAdapter() {
        return new HealthEduAddAdapter(R.layout.item_health_education_add, null);
    }

    /**
     * 宣教执行-Item配置
     * @return
     */
    public static HealthEduItemAdapter getHealthEduItemAdapter() {
        return new HealthEduItemAdapter(null);
    }
}