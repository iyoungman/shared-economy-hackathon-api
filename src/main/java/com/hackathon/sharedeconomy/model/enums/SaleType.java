package com.hackathon.sharedeconomy.model.enums;

/**
 * Created by YoungMan on 2019-02-14.
 */

public enum SaleType {
    SALE("판매중"), COMPLETE("판매완료");

    private String saleExplain;

    SaleType(String saleExplain) {
        this.saleExplain = saleExplain;
    }

    private String getSaleExplain() {
        return saleExplain;
    }

    public static SaleType convertSaleType(String role) {
        if (role.equals(SALE.getSaleExplain())) {
            return SALE;
        } else {
            return COMPLETE;
        }
    }
}
