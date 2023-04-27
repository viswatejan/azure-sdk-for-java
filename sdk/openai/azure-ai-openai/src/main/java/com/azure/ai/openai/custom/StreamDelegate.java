package com.azure.ai.openai.custom;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.custom.models.StreamingCompletions;
import com.azure.ai.openai.models.CompletionsOptions;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;

public class StreamDelegate {

    @ServiceMethod(returns = ReturnType.SINGLE)
    public static StreamingCompletions GetCompletionsStreaming(
        CompletionsOptions completionsOptions,
        OpenAIClient openAIClient
    ) {
        completionsOptions.setStream(true);
        return null;
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public static StreamingCompletions GetCompletionsStreamingAsync(
        CompletionsOptions completionsOptions,
        OpenAIAsyncClient openAIClient
    ) {
        completionsOptions.setStream(true);
        return null;
    }
}
