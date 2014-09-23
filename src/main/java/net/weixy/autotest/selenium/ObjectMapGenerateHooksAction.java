package net.weixy.autotest.selenium;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import net.weixy.autotest.selenium.psi.ObjectMapFile;

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
        // TODO: insert action logic here
        System.out.println("=================================");
    }
}
