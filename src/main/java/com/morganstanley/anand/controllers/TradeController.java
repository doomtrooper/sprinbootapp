package com.morganstanley.anand.controllers;

import com.morganstanley.anand.error.GenericException;
import com.morganstanley.anand.model.BlacklistStock;
import com.morganstanley.anand.model.Indicator;
import com.morganstanley.anand.model.Trade;
import com.morganstanley.anand.model.TradeInput;
import com.morganstanley.anand.repository.BlockStockRepository;
import com.morganstanley.anand.repository.TradeRepository;
import com.morganstanley.anand.service.UploadService;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TradeController {

    @Autowired
    UploadService service;
    @Autowired
    TradeRepository repository;
    @Autowired
    BlockStockRepository blockStockRepository;

    @PostMapping(value = "/trade", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void conductTrade(@RequestParam("file") MultipartFile file) throws IOException {
        List<Trade> trades = service.uploadFile(file);
        for (Trade trade : trades) {
            Optional<Trade> optional = repository.findById(trade.getTradeId());
            Optional<BlacklistStock> blockOptional = blockStockRepository.findById(trade.getStockName());
            if (!optional.isPresent() && !blockOptional.isPresent()) repository.save(trade);
            else System.out.println("****************** "+trade.getTradeId()+" already found ******************");
        }
    }

    @GetMapping(value = "/trade/{broker_code}" , consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Trade> tradeBook(@PathVariable(value = "broker_code") String brokerCode){
        return repository.findByBrokerCode(brokerCode);
    }

    @GetMapping(value = "/trade/top" , consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<String> topTrade(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal.toString());
        List<String> list = repository.findTopStock();
        if (list.size()>5) return list.subList(0,5);
        else return list;
    }

    @GetMapping(value = "/trade/block/{stock}" , consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void blacklistStock(@PathVariable(value = "stock") String stockName){
        blockStockRepository.save(new BlacklistStock(stockName));
    }


    @PostMapping(value = "/trade/conduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Trade trade(@Valid @RequestBody TradeInput trade) throws IOException, InvalidArgumentException {
        Trade tradeobj = service.saveTrade(trade);
        if (tradeobj!=null){
            Optional<Trade> optional = repository.findById(trade.getTradeId());
            Optional<BlacklistStock> blockOptional = blockStockRepository.findById(trade.getStockName());
            List<Trade> byBrokerCode = repository.findByBrokerCodeAndStockName(tradeobj.getBrokerCode(), tradeobj.getStockName());
            int count = 0;
            for (Trade trade1 : byBrokerCode) {
                if (trade1.getIndicator()== Indicator.B) count += trade1.getQuantity();
                else count -= trade1.getQuantity();
            }
            if (!optional.isPresent() && !blockOptional.isPresent() && !(count<tradeobj.getQuantity() && tradeobj.getIndicator().equals(Indicator.S))) {
                repository.save(tradeobj);
                return tradeobj;
            }
        }
        System.out.println("****************** "+trade.getTradeId()+" already found ******************");
        throw new InvalidArgumentException(new String[]{"NOT valid trade"});
    }
}
