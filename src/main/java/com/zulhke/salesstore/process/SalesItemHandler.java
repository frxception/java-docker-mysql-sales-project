package com.zulhke.salesstore.process;

import com.zulhke.salesstore.domain.Sales;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class SalesItemHandler implements ItemProcessor<Sales, Sales> {

    public SalesItemHandler() {}

    @Override
    public Sales process(Sales sales) throws Exception {
        sales.setTimestamp(new Date());
        if(sales.getProfit() == null){
            sales.setProfit(new BigDecimal(0));
        }
        if(sales.getQuantity() == null){
            sales.setQuantity(0);
        }
        
        //TODO: Sanitize item
        
        return sales;
    }
}
