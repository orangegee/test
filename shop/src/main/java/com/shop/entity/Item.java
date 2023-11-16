package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;

import com.shop.dto.ItemFormDto;

import com.shop.exception.OutOfStockException;

@Entity
@Table(name="item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity {

    /** 상품 코드 */
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** 상품명 */
    @Column(nullable = false, length = 50)
    private String itemNm;

    /** 가격 */
    @Column(name="price", nullable = false)
    private int price;

    /** 재고 수량 */
    @Column(nullable = false)
    private int stockNumber;

    /** 상품 상세 설명 */
    @Lob
    @Column(nullable = false)
    private String itemDetail;

    /** 상품 판매 상태 */
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    public void updateItem(ItemFormDto itemFormDto){
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    public void removeStock(int stockNumber){
        int restStock = this.stockNumber - stockNumber; // 남은 수량 구하기.
        if(restStock<0){
            throw new OutOfStockException("상품의 재고가 부족 합니다. (현재 재고 수량: " + this.stockNumber + ")");
        }

        /** 주문 후 남은 재고의 수를 상품의 현재 재고 값으로 할당 */
        this.stockNumber = restStock;
    }

    /** 주문 취소 시 상품 재고 증가(기존 재고 수로 원복) 메소드 */
    public void addStock(int stockNumber){
        this.stockNumber += stockNumber;
    }

}
