package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ItemRepository extends JpaRepository<Item, Long>,
        QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {

    /** 쿼리 메소드 이용하여 상품 조회 */
    List<Item> findByItemNm(String itemNm);

    /** OR 조건 처리하기 */
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    /** LessThan 조건 처리하기 */
    List<Item> findByPriceLessThan(Integer price);

    /** OrderBy로 정렬 처리하기 */
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    /** @Query를 이용한 검색 처리하기 */
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    /**
     * @Query - nativeQuery
     *  기존 데이터베이스에서 사용하던 쿼리를 그대로 사용해야 할 때 활용.
     *  특정 데이터베이스에 종속되는 쿼리문을 사용하기 떄문에 데이터베이스에 독립적이라는 장점을 잃게 됨.
     *  복잡한 쿼리를 그대로 사용해야 하는 경우에 활용 !
     */
    @Query(value="select * from item i where i.item_detail like " +
            "%:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);



}
