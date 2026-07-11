package com.toshit.DevAnalyzer.service;

import com.toshit.DevAnalyzer.dto.CFResponse;
import com.toshit.DevAnalyzer.model.CFUser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class CFService {

    private final RestTemplate restTemplate ;
    CFService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public CFUser getUser(String userName){
        String url = "https://codeforces.com/api/user.info?handles="+userName;
        try {
            CFResponse resp = new CFResponse();
            resp = restTemplate.getForObject(url, CFResponse.class);
            return resp.getResult().get(0);

        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
    }

    public String handle(CFUser user){
        return user.getHandle();
    }

    public int numFriends(CFUser user){
        return user.getFriends();
    }

    public int maxRanting(CFUser user){
        return user.getMaxRating();
    }

    public int currRating(CFUser user){
        return user.getCurrRating();
    }

    public long TotalActive(CFUser user){
        long sec = user.getLastTime() - user.getRegisterTime();
        long min = sec/60;
        long hr = min/60;
        long days = hr/24;
        return days;
    }


}
