package com.hackathon.sharedeconomy.repository.impl;

import com.hackathon.sharedeconomy.domain.dto.ForSaleResponseDto;
import com.hackathon.sharedeconomy.domain.entity.ForSale;
import com.hackathon.sharedeconomy.domain.entity.QUser;
import com.hackathon.sharedeconomy.repository.custom.ForSaleRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.hackathon.sharedeconomy.domain.entity.QForSale.forSale;

/**
 * Created by YoungMan on 2019-02-23.
 */

@Component
public class ForSaleRepositoryImpl extends QuerydslRepositorySupport implements ForSaleRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private QUser user = QUser.user;
    //q클래스들은 sttic으로 설정되어있으므로 따로 안 불러와도 된다.

    public ForSaleRepositoryImpl(JPAQueryFactory queryFactory) {
        super(ForSale.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<ForSaleResponseDto> getForSaleResponseDtos(String userId, String address) {
        return queryFactory.select(Projections.constructor(ForSaleResponseDto.class, forSale, user.id, user.phoneNumber, user.address))
                .from(forSale)
                .join(forSale.user, user)
                .where(eqUserId(userId), likeAddress(address))
                .fetch();
    }

    private BooleanExpression eqUserId(String userId) {
        if (userId.isEmpty()) {
            return null;
        }
        return user.id.eq(userId);
    }

    private BooleanExpression likeAddress(String address) {
        if (address.isEmpty()) {
            return null;
        }
        return user.address.contains(address);
    }
}
