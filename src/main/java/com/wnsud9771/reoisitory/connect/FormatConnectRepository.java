package com.wnsud9771.reoisitory.connect;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.connect.FormatConnect;

public interface FormatConnectRepository extends JpaRepository<FormatConnect, Long>{
	List<FormatConnect> findAllByCampaignConnect_Id(Long campaignConnectId);
	FormatConnect findByCampaignConnect_IdAndFotmatKey(Long campaignConnectId,Long fotmatKey);
	void deleteByfotmatKey(Long fotmatKey);
}
