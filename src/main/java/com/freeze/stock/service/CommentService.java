package com.freeze.stock.service;

import com.freeze.stock.dto.CommentDTO;
import com.freeze.stock.entity.CommentEntity;
import com.freeze.stock.entity.StockEntity;
import com.freeze.stock.repository.CommentRepository;
import com.freeze.stock.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;

    private final StockRepository stockRepository;

    public Long save(CommentDTO commentDTO) {
        Optional<StockEntity> optionalStockEntity = stockRepository.findById(commentDTO.getStockId());
        if (optionalStockEntity.isPresent()) {
            StockEntity stockEntity = optionalStockEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, stockEntity);
            return commentRepository.save(commentEntity).getId();
        } else {
            return null;
        }
    }

    public List<CommentDTO> findAll(Long stockId) {
        // select * from comment_table where stock_id=? order by id dec;
        StockEntity stockEntity = stockRepository.findById(stockId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByStockEntityOrderByIdDesc(stockEntity);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
