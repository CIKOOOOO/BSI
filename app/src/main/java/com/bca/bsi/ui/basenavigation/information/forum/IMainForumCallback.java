package com.bca.bsi.ui.basenavigation.information.forum;

import com.bca.bsi.model.Forum;

import java.util.List;

public interface IMainForumCallback {

    void onLoadReportData(List<Forum.Report> reportList);

}
