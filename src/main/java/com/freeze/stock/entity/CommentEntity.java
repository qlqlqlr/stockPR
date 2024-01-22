package com.freeze.stock.entity;

import com.freeze.stock.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.stream.Stream;

@Entity
@Data
@Table(name = "comment_table")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String commentWriter;

    @Column
    private  String commentContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private StockEntity stockEntity;

    public static CommentEntity toSaveEntity(CommentDTO commentDTO, StockEntity stockEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setStockEntity(stockEntity);
        return commentEntity;
    }
}
