package com.hackathon.sharedeconomy.repository.impl;

import com.hackathon.sharedeconomy.model.dto.ForSaleDto;
import com.hackathon.sharedeconomy.model.entity.ForSale;
import com.hackathon.sharedeconomy.model.entity.QUser;
import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.model.enums.SaleType;
import com.hackathon.sharedeconomy.repository.custom.ForSaleRepositoryCustom;
import com.hackathon.sharedeconomy.service.UserService;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.hackathon.sharedeconomy.model.entity.QForSale.forSale;

/**
 * Created by YoungMan on 2019-02-23.
 */

@Component
public class ForSaleRepositoryImpl extends QuerydslRepositorySupport implements ForSaleRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private QUser user = QUser.user;
    private UserService userService;

    public ForSaleRepositoryImpl(JPAQueryFactory queryFactory, UserService userService) {
        super(ForSale.class);
        this.queryFactory = queryFactory;
        this.userService = userService;
    }

    @Override
    public List<ForSaleDto.Response> getForSaleResponseDtos(ForSaleDto.Request requestDto) {
        return queryFactory.select(Projections.constructor(ForSaleDto.Response.class, forSale, user.id, user.phoneNumber, user.address))
                .from(forSale)
                .join(forSale.user, user)
                .where(eqUserAddress(requestDto.getUserId()), likeSearchAddress(requestDto.getAddress()), eqSale())
                .fetch();
    }

    private BooleanExpression eqUserAddress(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return null;
        }

        User loginUser = userService.findById(userId);
        return user.address.eq(loginUser.getAddress());
    }

    private BooleanExpression likeSearchAddress(String address) {
        if (StringUtils.isEmpty(address)) {
            return null;
        }
        return user.address.contains(address);
    }

    private BooleanExpression eqSale() {
        return forSale.saleType.eq(SaleType.SALE);
    }
}
