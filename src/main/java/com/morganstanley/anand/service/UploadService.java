package com.morganstanley.anand.service;

import com.morganstanley.anand.model.Indicator;
import com.morganstanley.anand.model.Trade;
import com.morganstanley.anand.model.TradeInput;
import com.morganstanley.anand.repository.TradeRepository;
import com.morganstanley.anand.validation.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UploadService {

    @Autowired
    ValidatorService validatorService;

    public List<Trade> uploadFile(MultipartFile file) throws IOException {
        BufferedReader br;
        List<Trade> result = new ArrayList<>();
        String line;
        InputStream is = file.getInputStream();
        br = new BufferedReader(new InputStreamReader(is));
        String[] split;
        Optional<List<String>> optional;
        while ((line = br.readLine()) != null) {
            split = line.split("[|][\\s]*");
            Arrays.stream(split).map(s -> s.trim()).collect(Collectors.toList()).toArray(split);
            optional = validatorService.validate(split);
            if (!optional.isPresent()){
                Trade trade = Trade.builder().tradeId(split[0])
                        .stockName(split[1])
                        .brokerCode(split[2])
                        .brokerName(split[3])
                        .quantity(Integer.valueOf(split[4]))
                        .tradeDate(new Date(split[5]))
                        .settlementDate(new Date(split[6]))
                        .indicator(Indicator.valueOf(split[7]))
                        .build();
                result.add(trade);
            }else {
                System.out.println("****************** "+split[0] +" NOT valid ******************");
            }
        }
        return result;
    }

    public Trade saveTrade(@Valid TradeInput trade){
        String[] tradeString = new String[8];
        tradeString[0] = trade.getTradeId();
        tradeString[1] = trade.getStockName();
        tradeString[2] = trade.getBrokerCode();
        tradeString[3] = trade.getBrokerName();
        tradeString[4] = String.valueOf(trade.getQuantity());
        tradeString[5] = trade.getTradeDate();
        tradeString[6] = trade.getSettlementDate();
        tradeString[7] = trade.getIndicator();
        Optional<List<String>> optional;
        optional = validatorService.validate(tradeString);
        if (!optional.isPresent()){
            Trade tradeObj = Trade.builder().tradeId(tradeString[0])
                    .stockName(tradeString[1])
                    .brokerCode(tradeString[2])
                    .brokerName(tradeString[3])
                    .quantity(Integer.valueOf(tradeString[4]))
                    .tradeDate(new Date(tradeString[5]))
                    .settlementDate(new Date(tradeString[6]))
                    .indicator(Indicator.valueOf(tradeString[7]))
                    .build();
            return tradeObj;
        }else {
            System.out.println("****************** "+tradeString[0] +" NOT valid ******************");
            return null;
        }
    }
}
