package com.shuzijun.leetcode.plugin.listener;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
import com.intellij.ui.jcef.JBCefApp;
import com.shuzijun.leetcode.plugin.model.PluginConstant;
import org.jetbrains.annotations.NotNull;

/**
 * @author shuzijun
 */
public class SupportCheck implements ProjectActivity, DumbAware {

    public static boolean isFirstProject = true;

    @Override
    public Object execute(@NotNull Project project, @NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        if (ApplicationManager.getApplication().isUnitTestMode() || !isFirstProject) {
            continuation.resumeWith(kotlin.Unit.INSTANCE);
            return kotlin.Unit.INSTANCE;
        }
        if (!JBCefApp.isSupported()) {
            Notifications.Bus.notify(new Notification(PluginConstant.NOTIFICATION_GROUP, "Not Support JCEF", "Your environment does not support JCEF, cannot use LeetCode Editor.Check the Registry 'ide.browser.jcef.enabled'.", NotificationType.ERROR));
        }
        continuation.resumeWith(kotlin.Unit.INSTANCE);
        return kotlin.Unit.INSTANCE;
    }
}