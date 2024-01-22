package com.freeze.stock.controller;

//import com.freeze.stock.dto.StockDTO;
import com.freeze.stock.dto.CommentDTO;
import com.freeze.stock.dto.StockDTO;
import com.freeze.stock.service.CommentService;
import com.freeze.stock.service.StockService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private final StockService stockService;
    private final CommentService commentService;

    @GetMapping("/save")
    public String saveForm() {

        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute StockDTO stockDTO) {
        System.out.println("stockDTO = " + stockDTO);
        stockService.save(stockDTO);
        return "index";
    }

    // 데이터를 가져올때는 모델 객체를 사용한다
    @GetMapping("/")
    public String findAll(Model model) {
        // DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다
        List<StockDTO> stockDTOList = stockService.findAll();
        model.addAttribute("stockList", stockDTOList);
        return "list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        StockDTO stockDTO = stockService.findById(id);
        /* 댓글 목록 가져오기 */
        List<CommentDTO> commentDTOList = commentService.findAll(id);
        model.addAttribute("commentList", commentDTOList);

        model.addAttribute("stock", stockDTO);
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        StockDTO stockDTO = stockService.findById(id);
        model.addAttribute("stockUpdate", stockDTO);
        return "update";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute StockDTO stockDTO, Model model) {
        StockDTO stock = stockService.update(stockDTO);
        model.addAttribute("stock", stock);
        return "detail";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        stockService.delete(id);
        return "redirect:/stock/";
    }
//
//    @PostMapping("/save")
//    public String save(@ModelAttribute StockDTO stockDTO) throws IOException {
//        System.out.println("stockDTO = " + stockDTO);
//        stockService.save(stockDTO);
//        return "index";
//    }
//
//    @GetMapping("/")
//    public String findAll(Model model) {
//        // DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
//        // 전체 데이터를 가져올 때는 model 객체를 사용하면 된다.
//        List<StockDTO> stockDTOList = stockService.findAll();
//        model.addAttribute("stockList", stockDTOList);
//        return "list";
//    }
}
