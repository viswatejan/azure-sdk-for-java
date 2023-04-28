package com.azure.ai.openai.generated;

import com.azure.ai.openai.models.Completions;
import com.azure.ai.openai.models.CompletionsOptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OpenAICompletionsTests  extends OpenAIClientTestBase {

    @Test
    public void getCompletions() {
        getCompletionsRunner((deploymentId, prompt) -> {
            Completions resultCompletions = openAIClient.getCompletions(deploymentId, new CompletionsOptions(prompt));
            assertNotNull(resultCompletions.getUsage());
        });
    }
}
