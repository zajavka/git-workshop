package pl.zajavka.git;

public interface GitConfig {

    String YOUR_LOCAL_USER_NAME = "Testowy Koleszka";
    String YOUR_LOCAL_USER_EMAIL = "testowykoleszka@zajavka.pl";
    String YOUR_LOCAL_PLAYGROUND_REPOSITORY_LOCATION = "./zajavka-git-workshop-playground/.git";

    String YOUR_GITHUB_USERNAME = "your_user_name";
    String YOUR_GITHUB_TOKEN = "your_token";
    String YOUR_REMOTE_PLAYGROUND_REPOSITORY_NAME = "repo_name";

    String GITHUB = "https://github.com";
    String ORIGINAL_GITHUB_USERNAME = "zajavka";
    String ORIGINAL_GITHUB_PLAYGROUND_REPOSITORY_NAME = "zajavka-git-workshop-playground";
    String ORIGINAL_GITHUB_PLAYGROUND_REPOSITORY_LOCATION = String.format("%s/%s/%s", GITHUB, ORIGINAL_GITHUB_USERNAME, ORIGINAL_GITHUB_PLAYGROUND_REPOSITORY_NAME);
    String YOUR_GITHUB_PLAYGROUND_REPOSITORY_LOCATION = String.format("%s/%s/%s", GITHUB, YOUR_GITHUB_USERNAME, YOUR_REMOTE_PLAYGROUND_REPOSITORY_NAME);



}
