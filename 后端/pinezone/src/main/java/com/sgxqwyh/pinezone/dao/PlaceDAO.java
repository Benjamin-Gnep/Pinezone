package com.sgxqwyh.pinezone.dao;

import com.sgxqwyh.pinezone.pojo.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceDAO extends JpaRepository<PlaceEntity, Long> {
}
