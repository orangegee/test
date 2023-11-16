package com.shop.dto;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDto {

    /** 현재 시간과 상품 등록일 비교하여 조회 */
    private String searchDateType;

    /** 판매 상태 기준 조회 */
    private ItemSellStatus searchSellStatus;

    /** 조회 유형 선택
     * itemNm : 상품명
     * createdBy : 상품 등록자 아이디 */
    private String searchBy;

    /** 조회 검색어 저장 변수
     * searchBy가 itemNm일 경우 상품명 기준으로 검색,
     * createdBy일 경우 상품 등록자 아이디 기준으로 검색
     */
    private String searchQuery = "";

}