package com.ma.jpa.repository;

import com.ma.jpa.entity.TUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaoma
 * @description ...
 * @date 2020-03-25 23:11:03
 */
@Repository
public interface JpaTestRepository extends JpaRepository<TUser,Integer>, JpaSpecificationExecutor<TUser> {

    List<TUser> findByNameLike(String name);

    @Query("from TUser where name like ?1% and age = ?2")
    List<TUser> findByUserName(String name , Integer age);

    @Query(value = "select * from t_user where name like ?1% and age = ?2",nativeQuery = true)
    List<TUser> findByUserName1(String name, Integer age);

    @Query("from TUser where name like :username% and age = :age")
    List<TUser> findByUserName2(@Param("username") String name, @Param("age") Integer age);

    @Modifying
    @Query("update TUser set age = ?1 where name = ?2")
    int updateUserAge(Integer age , String name);

    @Modifying
    @Query("delete from TUser where name = ?1")
    int deleteUser(String name);


}
