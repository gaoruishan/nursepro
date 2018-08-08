package com.dhcc.nursepro.utils.wsutils;

import java.util.List;

public interface BaseView {
    // 显示进度1
    void showLoading();
    // 显示学生
    void showStudents(List<String> list, String wsCode);
}
