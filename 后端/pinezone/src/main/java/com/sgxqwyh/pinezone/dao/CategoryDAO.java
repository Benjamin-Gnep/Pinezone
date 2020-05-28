package com.sgxqwyh.pinezone.dao;

import com.sgxqwyh.pinezone.pojo.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends JpaRepository<CategoryEntity, Integer> {
}
