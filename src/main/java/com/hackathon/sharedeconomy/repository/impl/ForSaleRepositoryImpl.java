package com.hackathon.sharedeconomy.repository.impl;

import com.hackathon.sharedeconomy.model.dto.ForSaleRequestDto;
import com.hackathon.sharedeconomy.model.dto.ForSaleResponseDto;
import com.hackathon.sharedeconomy.model.entity.ForSale;
import com.hackathon.sharedeconomy.model.entity.QUser;
import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.repository.custom.ForSaleRepositoryCustom;
import com.hackathon.sharedeconomy.service.LoginService;
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
    private LoginService loginService;

    public ForSaleRepositoryImpl(JPAQueryFactory queryFactory, LoginService loginService) {
        super(ForSale.class);
        this.queryFactory = queryFactory;
        this.loginService = loginService;
    }

    @Override
    public List<ForSaleResponseDto> getForSaleResponseDtos(ForSaleRequestDto forSaleRequestDto) {
        return queryFactory.select(Projections.constructor(ForSaleResponseDto.class, forSale, user.id, user.phoneNumber, user.address))
                .from(forSale)
                .join(forSale.user, user)
                .where(eqUserAddress(forSaleRequestDto.getUserId()), likeSearchAddress(forSaleRequestDto.getAddress()))
                .fetch();
    }

    private BooleanExpression eqUserAddress(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return null;
        }

        User loginUser = loginService.findById(userId);
        return user.address.eq(loginUser.getAddress());
    }

    private BooleanExpression likeSearchAddress(String address) {
        if (StringUtils.isEmpty(address)) {
            return null;
        }
        return user.address.contains(address);
    }
}
