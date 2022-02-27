package com.surya.starter.service;

import com.surya.starter.client.BetaClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlphaService {

    BetaClient betaClient;

    public String getNameOfDependentService() {
        return betaClient.getName();
    }
}
