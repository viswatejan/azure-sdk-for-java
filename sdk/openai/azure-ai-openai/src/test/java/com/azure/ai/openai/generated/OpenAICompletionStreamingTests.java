package com.azure.ai.openai.generated;

import com.azure.ai.openai.custom.StreamDelegate;
import com.azure.ai.openai.custom.models.StreamingCompletions;
import com.azure.ai.openai.models.Completions;
import com.azure.ai.openai.models.CompletionsOptions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OpenAICompletionStreamingTests extends OpenAIClientTestBase {

    @Test
    public void getCompletionsStreaming() {
        String deploymentId = "text-davinci-003";
        List<String> prompt = new ArrayList<>();
        prompt.add("Say this is a test");
        var streamingCompletions = StreamDelegate.getCompletionsStreaming(
            deploymentId,
            new CompletionsOptions(prompt),
            openAIClient);
        assertNotNull(streamingCompletions);
    }
}
