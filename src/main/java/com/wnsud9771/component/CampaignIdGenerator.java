package com.wnsud9771.component;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.wnsud9771.reoisitory.CampaignRepository;

import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CampaignIdGenerator {
	private final CampaignRepository campaignRepository;
	
	 public String generateUniqueId() {
	        Random random = new Random();
	        String generatedId;
	        int maxAttempts = 10;
	        int attempts = 0;
	        
	        do {
	            int randomNumber = 100000 + random.nextInt(900000);
	            generatedId = String.format("CAM%06d", randomNumber);
	            attempts++;
	            
	            if (attempts >= maxAttempts) {
	                long timestamp = System.currentTimeMillis();
	                generatedId = String.format("CAM%06d%03d", 
	                    randomNumber, 
	                    timestamp % 1000);
	                break;
	            }
	        } while (campaignRepository.existsByCampaign_id(generatedId));
	        
	        return generatedId;
	    }
}
