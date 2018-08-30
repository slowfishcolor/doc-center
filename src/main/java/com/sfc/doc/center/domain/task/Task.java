package com.sfc.doc.center.domain.task;

import com.sfc.doc.center.domain.menu.MenuNode;

public class Task {

    private String taskId;

    private String gitRepoUrl;

    private boolean finish;

    private MenuNode menu;

    private String baseUrl;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getGitRepoUrl() {
        return gitRepoUrl;
    }

    public void setGitRepoUrl(String gitRepoUrl) {
        this.gitRepoUrl = gitRepoUrl;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public MenuNode getMenu() {
        return menu;
    }

    public void setMenu(MenuNode menu) {
        this.menu = menu;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
