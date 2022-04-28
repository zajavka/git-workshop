package pl.zajavka.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ResetCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.RemoteConfig;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.IOException;
import java.util.List;

import static pl.zajavka.git.GitConfig.*;

public class GitOperations {

    public static void setUser(Git git) throws IOException {
        StoredConfig config = git.getRepository().getConfig();
        config.setString("user", null, "name", YOUR_LOCAL_USER_NAME);
        config.setString("user", null, "email", YOUR_LOCAL_USER_EMAIL);
        config.save();
    }

    public static void changeRemoteRepositoryToOriginal(Git git) throws IOException {
        StoredConfig config = git.getRepository().getConfig();
        config.setString("remote", "origin", "url", ORIGINAL_GITHUB_PLAYGROUND_REPOSITORY_LOCATION);
        config.save();
    }

    public static void changeRemoteRepositoryToYours(Git git) throws IOException {
        StoredConfig config = git.getRepository().getConfig();
        config.setString("remote", "origin", "url", YOUR_GITHUB_PLAYGROUND_REPOSITORY_LOCATION);
        config.save();
    }

    public static void checkoutBranch(Git git, String feature) throws GitAPIException {
        git.checkout()
            .setName(feature)
            .call();
    }

    public static void forcePush(Git git) throws GitAPIException {
        git.push()
            .setForce(true)
            .setCredentialsProvider(new UsernamePasswordCredentialsProvider(YOUR_GITHUB_USERNAME, YOUR_GITHUB_TOKEN))
            .call();
    }

    public static void push(Git git) throws GitAPIException {
        git
            .push()
            .setCredentialsProvider(new UsernamePasswordCredentialsProvider(YOUR_GITHUB_USERNAME, YOUR_GITHUB_TOKEN))
            .call();
    }

    public static void commit(Git git, String message) throws GitAPIException {
        git.commit()
            .setMessage(message)
            .call();
    }

    public static void addAll(Git git) throws GitAPIException {
        git.add()
            .addFilepattern(".")
            .call();
    }

    public static void createFeatureBranch(Git git) throws GitAPIException {
        git.checkout()
            .setCreateBranch(true)
            .setName("feature")
            .call();
    }

    public static void removeAllNonUsedBranches(Git git) throws GitAPIException {
        removeLocalBranches(git);
        removeRemoteBranches(git);
    }

    private static void removeLocalBranches(Git git) throws GitAPIException {
        List<String> branches = git.branchList()
            .call()
            .stream()
            .map(Ref::getName)
            .filter(name -> !name.contains("master"))
            .toList();

        for (String branch : branches) {
            git.branchDelete()
                .setBranchNames(branch)
                .setForce(true)
                .call();
        }
    }

    private static void removeRemoteBranches(Git git) throws GitAPIException {
        List<String> branches = fetchRemoteGitBranches();

        for (String branch : branches) {
            git.branchDelete()
                .setBranchNames(branch)
                .setForce(true)
                .call();

            RefSpec refSpec = new RefSpec()
                .setSource(null)
                .setDestination(branch);
            git.push()
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider(YOUR_GITHUB_USERNAME, YOUR_GITHUB_TOKEN))
                .setRefSpecs(refSpec)
                .setRemote(YOUR_GITHUB_PLAYGROUND_REPOSITORY_LOCATION)
                .call();
        }
    }

    public static List<String> fetchRemoteGitBranches() throws GitAPIException {
        return Git.lsRemoteRepository()
            .setHeads(true)
            .setRemote(YOUR_GITHUB_PLAYGROUND_REPOSITORY_LOCATION)
            .call()
            .stream()
            .map(Ref::getName)
            .filter(name -> !name.contains("master"))
            .sorted()
            .toList();
    }

    public static void resetHardOriginMaster(Git git) throws GitAPIException {
        git.reset()
            .setMode(ResetCommand.ResetType.HARD)
            .setRef("origin/master")
            .call();
    }

    public static void fetch(Git git) throws GitAPIException {
        for(RemoteConfig remote : git.remoteList().call()) {
            git.fetch()
                .setRemote(remote.getName())
                .setRefSpecs(remote.getFetchRefSpecs())
                .setRemoveDeletedRefs(true)
                .call();
        }
    }
}
