package org.freecode.demo.controller;

import org.freecode.demo.model.GitUser;
import org.freecode.demo.service.GitLookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
/**
 * annotated this class is Spring managed - implementing CommandLineRunner
 */
public class GitLookupRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(GitLookupRunner.class);

    @Autowired
    private GitLookupService service;

    public void run(String... args) throws Exception {
        CompletableFuture<GitUser> info1 = service.findUser("jonliu6");
        CompletableFuture<GitUser> info2 = service.findUser("Tensorflow");
        CompletableFuture<GitUser> info3 = service.findUser("spring-boot");
        CompletableFuture<GitUser> info4 = service.findUser("spring-security");

        CompletableFuture.allOf(info1, info2, info3, info4).join();

        log.info("-->" + info1.get());
        log.info("-->" + info2.get());
        log.info("-->" + info3.get());
        log.info("-->" + info4.get());
    }
}
