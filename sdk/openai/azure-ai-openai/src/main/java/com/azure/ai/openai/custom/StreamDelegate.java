package com.azure.ai.openai.custom;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.custom.models.StreamingCompletions;
import com.azure.ai.openai.models.CompletionsOptions;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamDelegate {

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public static Flux<StreamingCompletions> getCompletionsStreaming(
        String deploymentId,
        CompletionsOptions completionsOptions,
        OpenAIClient openAIClient
    ) {
        completionsOptions.setStream(true);
        var completions = openAIClient.getCompletions(deploymentId, completionsOptions);
        StreamingCompletions streamingCompletions = new StreamingCompletions(completions);
        return Flux.fromIterable(List.of(streamingCompletions));
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public static List<StreamingCompletions> getCompletionsStreamingAsync(
        CompletionsOptions completionsOptions,
        OpenAIAsyncClient openAIClient
    ) {
        completionsOptions.setStream(true);
        return null;
    }
}
