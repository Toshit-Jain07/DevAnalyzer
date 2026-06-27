package com.toshit.DevAnalyzer.service;

public abstract class BaseAnalyzer<P> {
    public AiService aiService;
    public BaseAnalyzer(AiService aiService){
        this.aiService = aiService;
    }

    public final P analyze(String username){
        return response(username);
    }

    public abstract P response(String username);
}
