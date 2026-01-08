package org.freecode.demo.springbootaidemo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.http.ResponseEntity;
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
    /**
     * http://localhost:8080/chat?prompt=Tell%20me%20a%20joke
     * @param promptString
     * @return
     */
    public ResponseEntity<?> getDemo(@RequestParam("prompt") Optional<String> promptString) {
        if (promptString.isEmpty() || promptString.get().isBlank()) {
            IllegalArgumentException ex = new IllegalArgumentException("Prompt cannot be empty. Please provide a valid prompt. eg http://<server:port>/chat?prompt=Tell%20me%20a%20joke");
            Map<String, String> error = new HashMap<>();
            error.put("error", ex.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
        ChatClient chatClient = builder.defaultOptions(ChatOptions.builder().model("gpt-4.1-mini").build()).build();
		String response = chatClient.prompt(promptString.get()).call().content();							
        return ResponseEntity.ok(response);
    }
}
