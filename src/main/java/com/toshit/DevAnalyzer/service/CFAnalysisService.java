package com.toshit.DevAnalyzer.service;

import com.toshit.DevAnalyzer.dto.CFAnalysisResponse;
import com.toshit.DevAnalyzer.model.CFUser;
import org.springframework.stereotype.Service;

@Service
public class CFAnalysisService extends BaseAnalyzer<CFAnalysisResponse> {
    private final CFService cfService;
    CFAnalysisService (AiService aiService,CFService cfService){
        super(aiService);
        this.cfService = cfService;
    }


    @Override
    public CFAnalysisResponse response(String username){
        CFUser user = cfService.getUser(username);

        String handle = cfService.handle(user);
        int maxRating = cfService.maxRanting(user);
        int freinds = cfService.numFriends(user);

        long active = cfService.TotalActive(user);

        int currRating = cfService.currRating(user);

        String summary = "Handle name is: "+ handle +
                            "\nMax rating: "+ maxRating +
                            "\nCurrent Rating "+ currRating +
                            "\nTotal Friends of this person are: "+freinds +
                            "\nTotal Active days on CFs: "+active;

        String critique = aiService.getCritique(summary);

        CFAnalysisResponse resp = new CFAnalysisResponse();
        resp.setFriends(freinds);
        resp.setHandle(handle);
        resp.setCritique(critique);
        resp.setCurrRating(currRating);
        resp.setTotalActive(active);
        resp.setMaxRating(maxRating);

        return resp;
    }


}
