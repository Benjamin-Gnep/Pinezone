package com.sgxqwyh.pinezone.dao;

import com.sgxqwyh.pinezone.pojo.SuggestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingDAO extends JpaRepository<SuggestionEntity,Integer> {
}
