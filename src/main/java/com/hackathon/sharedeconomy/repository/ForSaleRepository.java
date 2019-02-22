package com.hackathon.sharedeconomy.repository;

import com.hackathon.sharedeconomy.domain.entity.ForSale;
import com.hackathon.sharedeconomy.repository.custom.ForSaleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by YoungMan on 2019-02-14.
 */

public interface ForSaleRepository extends JpaRepository<ForSale, Long>, ForSaleRepositoryCustom {

    ForSale findByName(@Param("name") String name);

    @Query("select f from ForSale f join f.user u where u.address like concat('%',:address,'%')")
    List<ForSale> getForSaleByUserId(@Param("address") String address);

//    @Query("select f from ForSale f join f.user u where u.address = :address")
    @Query("select f from ForSale f join f.user u where u.address like concat('%',:address,'%')")
    List<ForSale> getSearchForSaleByAddress(@Param("address") String address);

}
