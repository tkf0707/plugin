package com.tangkf1.firstplugin;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.vcs.ProjectLevelVcsManager;
import com.intellij.openapi.vcs.impl.ProjectLevelVcsManagerImpl;
import git4idea.GitLocalBranch;
import git4idea.branch.GitBranchUtil;
import org.zmlx.hg4idea.util.HgUtil;

public class FirstPluginAction extends AnAction {
 
    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        NotificationGroup notificationGroup = new NotificationGroup("firstplugin_id", NotificationDisplayType.BALLOON, true);
        Notification notification = notificationGroup.createNotification("点击测试"+extractBranchName(e.getProject())+"111", MessageType.INFO);
        Notifications.Bus.notify(notification);
        System.out.println(extractBranchName(e.getProject()));
    }

    public static String extractBranchName(Project project) {
        String branch = "";
        ProjectLevelVcsManager instance = ProjectLevelVcsManagerImpl.getInstance(project);
        if (instance.checkVcsIsActive("Git")) {
            GitLocalBranch currentBranch = GitBranchUtil.getCurrentRepository(project).getCurrentBranch();

            if (currentBranch != null) {
                // Branch name  matches Ticket Name
                branch = currentBranch.getName().trim();
            }
        } else if (instance.checkVcsIsActive("Mercurial")) {
            branch = HgUtil.getCurrentRepository(project).getCurrentBranch().trim();
        }

        return branch;
    }
}
