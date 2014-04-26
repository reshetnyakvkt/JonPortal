package ua.com.jon.utils;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import ua.com.jon.auth.domain.GitHubRepo;
import ua.com.jon.auth.domain.GitHubUser;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 26.04.14
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */
public class GitHubClient {

    public static Set<GitHubRepo> getRepositoriesAndCollaborators() throws IOException {
        GitHub github = GitHub.connectUsingPassword("sergey_zagalskiy@mail.ru", "darcyk123");
        Map<String, GHRepository> reposMap = github.getMyself().getAllRepositories();
        System.out.println(reposMap);
        GitHubRepo repo;
        Set<GitHubUser> gitHubUsers;
        Set<GitHubRepo> gitHubRepos = new HashSet<GitHubRepo>();
        //GHRepository repo = github.getMyself().getRepository("JonPortal");
        for (GHRepository repository : reposMap.values()) {
            gitHubUsers = new HashSet<GitHubUser>();
            for (GHUser collaborator : repository.getCollaborators()) {
                gitHubUsers.add(new GitHubUser(collaborator.getLogin()));
            }
            repo = new GitHubRepo(repository.getName(), gitHubUsers);
            gitHubRepos.add(repo);
        }
        return gitHubRepos;
    }

    public static void main (String [] args) throws IOException {
        getRepositoriesAndCollaborators();
    }
}
