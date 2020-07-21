package com.dhcc.module.nurse;

import com.dhcc.module.nurse.education.adapter.HealthEduAddAdapter;
import com.dhcc.module.nurse.education.adapter.HealthEduAdapter;

/**
 * 管理适配器
 * @author:gaoruishan
 * @date:202019-04-28/11:05
 * @email:grs0515@163.com
 */
public class AdapterFactory {
    /**
     * 健康宣教
     * @return
     */
    public static HealthEduAdapter getHealthEducationAdapter() {
        return new HealthEduAdapter(R.layout.item_health_education, null);
    }

    public static HealthEduAddAdapter getHealthEduAddAdapter() {
        return new HealthEduAddAdapter(R.layout.item_health_education_add, null);
    }
}