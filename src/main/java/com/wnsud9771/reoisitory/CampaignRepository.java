package com.wnsud9771.reoisitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wnsud9771.entity.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

}
