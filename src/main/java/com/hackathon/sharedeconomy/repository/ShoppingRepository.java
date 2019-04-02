package com.hackathon.sharedeconomy.repository;

import com.hackathon.sharedeconomy.model.entity.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by YoungMan on 2019-02-14.
 */

public interface ShoppingRepository extends JpaRepository<Shopping, Long> {

    @Query("select s from Shopping s join s.forSale f join s.user u where u.id = :userId and f.user.id = :forSaleUserId")
    Shopping findByUserIdAndForSaleUserId(@Param("userId") String userId, @Param("forSaleUserId") String forSaleUserId);
}
