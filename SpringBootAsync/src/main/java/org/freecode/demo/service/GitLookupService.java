package org.freecode.demo.service;

import org.freecode.demo.model.GitUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
// Spring managed bean holding business logic
public class GitLookupService {
    private static final Logger log = LoggerFactory.getLogger(GitLookupService.class);
    private static final String GITHUB_USERS_URL = "https://api.github.com/users/%s";
    private final RestTemplate restTemp;

    public GitLookupService(RestTemplateBuilder restTempBuilder) {
        this.restTemp = restTempBuilder.build();
    }

    @Async
    /**
     * annotated for asign Spring to run this code method in a separate threads
     * CompletableFuture holds the results until the async call is completed
     */
    public CompletableFuture<GitUser> findUser(String user) throws InterruptedException {
        log.info("Look up " + user);
        String url = String.format(GITHUB_USERS_URL, user);
        GitUser results = restTemp.getForObject(url, GitUser.class);
        Thread.sleep(5000L); // sleep 5 seconds after each call of this method, but does not block others call this method

        return CompletableFuture.completedFuture(results);
    }
}