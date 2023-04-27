package com.azure.ai.openai.custom.models;

import com.azure.ai.openai.models.Completions;

public class StreamingCompletions {
    private Completions completions;

    public StreamingCompletions(Completions completions) {
        this.completions = completions;
    }

    public Completions getCompletions() {
        return completions;
    }
}
