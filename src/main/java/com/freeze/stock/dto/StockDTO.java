package com.freeze.stock.dto;

import com.freeze.stock.entity.StockEntity;
import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor      // 기본생성자
@AllArgsConstructor   // 모든 필드를 매개변수로 하는 생성자
public class StockDTO {
    private Long id;
    private String category;
    private String productName;
    private Long count;
    private String exp;


    public static StockDTO toStockDTO(StockEntity stockEntity) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(stockEntity.getId());
        stockDTO.setCategory(stockEntity.getCategory());
        stockDTO.setProductName(stockEntity.getProductName());
        stockDTO.setCount(stockEntity.getCount());
        stockDTO.setExp(stockEntity.getExp());
        return stockDTO;
    }
}
