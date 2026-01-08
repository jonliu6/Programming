package org.freecode.demo.springbootaidemo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIDemoController {

    private final ChatClient.Builder builder;

    public AIDemoController(ChatClient.Builder builder) {
        this.builder = builder;
    }

    @GetMapping("/chat")
    public String getDemo(@RequestParam("prompt") String promptString) {
        ChatClient chatClient = builder.build();
		String response = chatClient.prompt(promptString).call().content();							
        return response;
    }
}
