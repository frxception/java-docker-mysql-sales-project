package com.zulhke.salesstore.configuration;

import com.zulhke.salesstore.domain.Sales;
import com.zulhke.salesstore.process.CSVVerifier;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class SalesBatchCfg {

    @Bean
    public FlatFileItemReader<Sales> reader(@Value("${csvfile}") Resource resource) {

        FlatFileItemReader<Sales> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setName("ZUHLKE-SALES-CSV-ITEM-READER");
        flatFileItemReader.setLinesToSkip(1); //NOTE: This will skip the first line of the csv which is the header
        flatFileItemReader.setStrict(false);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<Sales> lineMapper() {

        DefaultLineMapper<Sales> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"id", "orderId", "orderDate", "shipDate", "shipMode", "customerId", "customerName", "segment", "country", "city", "state", "postalCode", "region" , "productId", "category", "subCategory", "productName", "sales", "quantity", "discount", "profit"});
        BeanWrapperFieldSetMapper<Sales> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setStrict(false);
        fieldSetMapper.setTargetType(Sales.class);
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }

    @Bean
    public SkipPolicy fileVerificationSkipper() {
        return new CSVVerifier();
    }
    
    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                   ItemReader<Sales> reader, ItemProcessor<Sales, Sales> processor,
                   ItemWriter<Sales> writer) {

        Step step = stepBuilderFactory.get("ZUHLKE-SALES-STEP-EXEC-CSVFILE-LOAD")
                .<Sales, Sales>chunk(10) //NOTE: use 10 batch job to process
                .reader(reader)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(12)
                .skipPolicy(fileVerificationSkipper())
                .processor(processor)
                .writer(writer)
                .build();

        return jobBuilderFactory.get("ZUHLKE-SALES-CSVFILE-LOAD")
                .incrementer(new RunIdIncrementer()) //TODO: Create a custom incrementer for the batch job
                .start(step)
                .build();
    }

}
