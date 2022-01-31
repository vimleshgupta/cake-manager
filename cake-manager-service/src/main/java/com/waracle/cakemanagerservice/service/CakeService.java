package com.waracle.cakemanagerservice.service;

import com.waracle.cakemanagerservice.client.CakeAPIClient;
import com.waracle.cakemanagerservice.client.response.CakeResponse;
import com.waracle.cakemanagerservice.model.Cake;
import com.waracle.cakemanagerservice.repository.CakeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CakeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CakeService.class);

    @Autowired
    CakeRepository cakeRepository;
    @Autowired
    CakeAPIClient cakeAPIClient;

    @EventListener(ApplicationReadyEvent.class)
    public void initialiseData() {

        LOGGER.debug("Initialize data");
        if (!isSampleDataExist()) {
            CakeResponse[] body = getCakeData();
            if (body != null && body.length != 0) {
                Set<Cake> cakes = Arrays.stream(body).map(cakeResponse -> Cake.builder()
                        .title(cakeResponse.getTitle())
                        .description(cakeResponse.getDesc())
                        .imageUrl(cakeResponse.getImage()).build())
                        .collect(Collectors.toSet());
                cakeRepository.saveAll(cakes);
            }
        }
    }

    public List<Cake> finaAllCakes() {
        return cakeRepository.findAll();
    }

    public Cake addCake(Cake cake) {
        return cakeRepository.save(cake);
    }

    private CakeResponse[] getCakeData() {
        try {
            return cakeAPIClient.getCakes().getBody();
        } catch (Exception ex) {
            LOGGER.debug("Unable to get sample cake data", ex);
        }
        return null;
    }

    private boolean isSampleDataExist() {
        return cakeRepository.count() > 0;
    }
}
