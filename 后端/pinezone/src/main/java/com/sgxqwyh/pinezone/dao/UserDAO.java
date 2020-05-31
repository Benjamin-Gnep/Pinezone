package com.sgxqwyh.pinezone.dao;
import com.sgxqwyh.pinezone.pojo.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<UserEntity, Integer> {

    //章权
    @Query(value = "SELECT sex,count(id) FROM pinezone.user group by sex;", nativeQuery = true)
    public List<Object[]> findSex();

    //俊鹏
    UserEntity findByNameAndPassword(String name, String password);
    UserEntity findByPhone(String phone);
}
