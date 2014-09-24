package net.weixy.autotest.selenium.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.changes.BackgroundFromStartOption;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import net.weixy.autotest.selenium.psi.ObjectMapFile;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jim Wei on 23/09/14.
 */
public class ObjectMapGenerateHooksAction extends AnAction {

    @Override
    public void update(AnActionEvent aae) {
        Project project = getEventProject(aae);
        VirtualFile[] files = LangDataKeys.VIRTUAL_FILE_ARRAY.getData(aae.getDataContext());
        boolean mapFound = false;
        if (null != project && null != files) {
            PsiManager manager = PsiManager.getInstance(project);
            for (VirtualFile virtualFile : files) {
                PsiFile psiFile = manager.findFile(virtualFile);
                mapFound = psiFile instanceof ObjectMapFile;
                if (mapFound) break;
            }
        }
        aae.getPresentation().setEnabled(mapFound);
        aae.getPresentation().setVisible(mapFound);
    }

    public void actionPerformed(AnActionEvent e) {
        final Project project = e.getProject();
        final VirtualFile[] files = LangDataKeys.VIRTUAL_FILE_ARRAY.getData(e.getDataContext());
        if (null == project || null == files) return;

        PsiDocumentManager.getInstance(project).commitAllDocuments();
        FileDocumentManager.getInstance().saveAllDocuments();

        final Set<File> pathsToRefresh = new HashSet<File>();
        ProgressManager.getInstance().run(new Task.Backgroundable(project, "Parser Generation", true, new BackgroundFromStartOption()) {
            @Override
            public void onCancel() {
                refreshFiles(pathsToRefresh);
            }

            @Override
            public void onSuccess() {
                refreshFiles(pathsToRefresh);
            }

            @Override
            public void run(@NotNull ProgressIndicator progressIndicator) {
                progressIndicator.setIndeterminate(true);
                ApplicationManager.getApplication().runReadAction(new Runnable() {
                    @Override
                    public void run() {
                        PsiManager psiManager = PsiManager.getInstance(project);
                        for (VirtualFile file : files) {
                            PsiFile objMapFile = psiManager.findFile(file);
                            if (!(objMapFile instanceof ObjectMapFile)) continue;

                            VirtualFile virtualFile = objMapFile.getVirtualFile();
                            String sourcePath = virtualFile == null ? "" : VfsUtil.virtualToIoFile(virtualFile).getParentFile().getAbsolutePath();

                        }
                    }
                });
            }
        });
    }

    private static void refreshFiles(Set<File> pathsToRefresh) {
        LocalFileSystem.getInstance().refreshIoFiles(pathsToRefresh, true, true, null);
    }
}
