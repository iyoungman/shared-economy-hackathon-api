package com.hackathon.sharedeconomy.repository.impl;

import com.hackathon.sharedeconomy.model.dto.ForSaleDto;
import com.hackathon.sharedeconomy.model.entity.*;
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

@SuppressWarnings("Duplicates")
@Component
public class ForSaleRepositoryImpl extends QuerydslRepositorySupport implements ForSaleRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private UserService userService;
    private QUser user = QUser.user;
    private QImage image = QImage.image;
    private QShopping shopping = QShopping.shopping;

    public ForSaleRepositoryImpl(JPAQueryFactory queryFactory, UserService userService) {
        super(ForSale.class);
        this.queryFactory = queryFactory;
        this.userService = userService;
    }

    @Override
    public List<ForSaleDto.ForSaleInfo> getForSaleResponseDtos(ForSaleDto.SearchRequest searchRequestDto) {
        return queryFactory.select(Projections.constructor(ForSaleDto.ForSaleInfo.class, forSale.id, forSale.price, forSale.name, image.url, user.id, user.phoneNumber, user.address, user.name, user.age, forSale.additionalInfo))
                .from(forSale)
                .join(forSale.user, user)
                .join(forSale.images, image)
                .where(eqUserAddress(searchRequestDto.getUserId()), likeSearchAddress(searchRequestDto.getAddress()), eqSale())
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


    @Override
    public List<ForSaleDto.ForSaleInfo> getForSaleResponseDtosByShopping(String userId) {
        return queryFactory.select(Projections.constructor(ForSaleDto.ForSaleInfo.class, forSale.id, forSale.price, forSale.name, image.url, user.id, user.phoneNumber, user.address, user.name, user.age, forSale.additionalInfo))
                .from(forSale)
                .join(forSale.user, user)
                .join(forSale.images, image)
                .join(forSale.shoppings, shopping)
                .where(eqShopping(userId))
                .fetch();
    }

    private BooleanExpression eqShopping(String userId) {
        User user = userService.findById(userId);
        return shopping.user.eq(user);
    }


}
