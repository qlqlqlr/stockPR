package com.freeze.stock.service;

import com.freeze.stock.dto.StockDTO;
import com.freeze.stock.entity.StockEntity;
import com.freeze.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public void save(StockDTO stockDTO) {
        StockEntity stockEntity = StockEntity.toSaveEntity(stockDTO);
        stockRepository.save(stockEntity);
    }

    public List<StockDTO> findAll() {
        List<StockEntity> stockEntityList = stockRepository.findAll();
        List<StockDTO> stockDTOList = new ArrayList<>();
        for (StockEntity stockEntity: stockEntityList) {
            stockDTOList.add(StockDTO.toStockDTO(stockEntity));
        }
        return stockDTOList;
    }

    public StockDTO findById(Long id) {
        Optional<StockEntity> optionalStockEntity = stockRepository.findById(id);
        if (optionalStockEntity.isPresent()) {
            StockEntity stockEntity = optionalStockEntity.get();
            StockDTO stockDTO = StockDTO.toStockDTO(stockEntity);
            return stockDTO;
        } else {
            return null;
        }
    }

    public StockDTO update(StockDTO stockDTO) {
        StockEntity stockEntity = StockEntity.toUpdateEntity(stockDTO);
        stockRepository.save(stockEntity);
        return findById(stockDTO.getId());
    }

    public void delete(Long id) {
        stockRepository.deleteById(id);
    }
}

