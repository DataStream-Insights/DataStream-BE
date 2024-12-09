package com.wnsud9771.reoisitory.connect;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.connect.CampaignConnect;

public interface CampaignConnectRepository extends JpaRepository<CampaignConnect, Long>{
	CampaignConnect findBycampaignKey(Long campaignKey);
	void deleteBycampaignKey(Long campaignKey);
}
