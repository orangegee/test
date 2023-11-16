package com.shop.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name="cart_item")
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;

    /**
     * 하나의 장바구니에는 여러 개의 상품을 담을 수 있으므로, @ManyToOne 어노테이션 (다대일) 이용.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    /** 같은 상품을 장바구니에 몇 개 담을지 저장 */
    private int count;

    public static CartItem createCartItem(Cart cart, Item item, int count) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(count);
        return cartItem;
    }

    /**
     * 장바구니 안에 담겨 있는 상품이 있는데 해당 상품을
     * 추가로 더 담을 때 !
     * 기존 수량에 추가로 담을 수량을 더하는 메소드
     */
    public void addCount(int count){
        this.count += count;
    }

    public void updateCount(int count){
        this.count = count;
    }

}
