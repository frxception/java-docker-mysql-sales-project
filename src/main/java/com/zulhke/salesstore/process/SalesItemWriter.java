package com.zulhke.salesstore.process;

import com.zulhke.salesstore.domain.Sales;
import com.zulhke.salesstore.repository.SalesRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SalesItemWriter implements ItemWriter<Sales> {

    @Autowired
    private SalesRepository repo;

    @Override
    public void write(List<? extends Sales> sales) throws Exception {
        repo.save(sales);
    }
}
