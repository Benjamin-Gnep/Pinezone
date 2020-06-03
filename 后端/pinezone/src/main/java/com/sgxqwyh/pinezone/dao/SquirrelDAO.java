package com.sgxqwyh.pinezone.dao;

import com.sgxqwyh.pinezone.pojo.SquirrelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquirrelDAO extends JpaRepository<SquirrelEntity, Long> {
    SquirrelEntity findByUid(Integer uid);
}
