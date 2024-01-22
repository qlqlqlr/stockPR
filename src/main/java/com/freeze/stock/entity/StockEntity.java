package com.freeze.stock.entity;

import com.freeze.stock.dto.StockDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// DB의 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(name = "stock_table")
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long Id;

    @Column
    private String category;

    @Column
    private String productName;

    @Column
    private Long count;

    @Column
    private String exp;

    // 부모가 삭제되면 자식도 함께 삭제된다
    @OneToMany(mappedBy = "stockEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();



    public static StockEntity toSaveEntity(StockDTO stockDTO) {
        StockEntity stockEntity = new StockEntity();
        stockEntity.setCategory(stockDTO.getCategory());
        stockEntity.setProductName(stockDTO.getProductName());
        stockEntity.setCount(stockDTO.getCount());
        stockEntity.setExp(stockDTO.getExp());
//        System.out.println("stockDTO = " + stockDTO);
        return stockEntity;
    }

    public static StockEntity toUpdateEntity(StockDTO stockDTO) {
        StockEntity stockEntity = new StockEntity();
        stockEntity.setId(stockDTO.getId());
        stockEntity.setCategory(stockDTO.getCategory());
        stockEntity.setProductName(stockDTO.getProductName());
        stockEntity.setCount(stockDTO.getCount());
        stockEntity.setExp(stockDTO.getExp());
//        System.out.println("stockDTO = " + stockDTO);
        return stockEntity;
    }
}
