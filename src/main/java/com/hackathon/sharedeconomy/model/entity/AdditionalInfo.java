package com.hackathon.sharedeconomy.model.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by YoungMan on 2019-03-28.
 */

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdditionalInfo {

    private String precautionFirst;
    private String precautionSecond;
    private String offerFirst;
    private String offerSecond;

    @Builder
    public AdditionalInfo(String precautionFirst, String precautionSecond, String offerFirst, String offerSecond) {
        this.precautionFirst = precautionFirst;
        this.precautionSecond = precautionSecond;
        this.offerFirst = offerFirst;
        this.offerSecond = offerSecond;
    }

    public void updateAdditionalInfo(String precautionFirst, String precautionSecond, String offerFirst, String offerSecond) {
        this.precautionFirst = precautionFirst;
        this.precautionSecond = precautionSecond;
        this.offerFirst = offerFirst;
        this.offerSecond = offerSecond;
    }
}
