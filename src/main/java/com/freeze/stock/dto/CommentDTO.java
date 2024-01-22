package com.freeze.stock.dto;

import com.freeze.stock.entity.CommentEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long stockId;
    private LocalDateTime commentCreatedTime;


    public static CommentDTO toCommentDTO(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
//        commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
        commentDTO.setStockId(commentEntity.getStockEntity().getId());
        return commentDTO;
    }
}
