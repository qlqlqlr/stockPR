package com.freeze.stock.repository;

import com.freeze.stock.entity.CommentEntity;
import com.freeze.stock.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByStockEntityOrderByIdDesc(StockEntity stockEntity);
}
