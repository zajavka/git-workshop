package pl.zajavka.case2;

import org.eclipse.jgit.api.Git;
import pl.zajavka.git.GitOperations;

import java.io.File;

import static pl.zajavka.git.GitConfig.YOUR_LOCAL_PLAYGROUND_REPOSITORY_LOCATION;

public class Case2 {

    public static void run() {
        try {
            Git git = Git.open(new File(YOUR_LOCAL_PLAYGROUND_REPOSITORY_LOCATION));

            GitOperations.setUser(git);
            GitOperations.changeRemoteRepositoryToOriginal(git);
            GitOperations.checkoutBranch(git, "master");
            GitOperations.fetch(git);
            GitOperations.resetHardOriginMaster(git);

            GitOperations.changeRemoteRepositoryToYours(git);
            GitOperations.checkoutBranch(git, "master");
            GitOperations.removeAllNonUsedBranches(git);
            GitOperations.resetHardOriginMaster(git);
            GitOperations.forcePush(git);
            GitOperations.fetch(git);

            GitOperations.createFeatureBranch(git);
            FileModifications.modification1();
            GitOperations.addAll(git);
            GitOperations.commit(git, "Person class modification 1");

            FileModifications.modification2();
            GitOperations.addAll(git);
            GitOperations.commit(git, "Person class modification 2");

            GitOperations.push(git);

            GitOperations.checkoutBranch(git, "master");
            FileModifications.modification3();
            GitOperations.addAll(git);
            GitOperations.commit(git, "Person class modification 3");

            GitOperations.checkoutBranch(git, "feature");
        } catch (Exception e) {
            System.err.println("CASE_1 Failure: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
