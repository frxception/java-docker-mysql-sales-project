package com.zulhke.salesstore.process;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;

import java.io.FileNotFoundException;

public class CSVVerifier implements SkipPolicy {
    
    @Override
    public boolean shouldSkip(Throwable exception, int skipCount) throws SkipLimitExceededException {
        if (exception instanceof FileNotFoundException) {
            return false;
        } else if (exception instanceof FlatFileParseException && skipCount <= 1) {
            FlatFileParseException ffpe = (FlatFileParseException) exception;
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("An error occured while processing the " + ffpe.getLineNumber()
                    + " line of the file. Below was the faulty " + "input.\n");
            errorMessage.append(ffpe.getInput() + "\n");
            System.out.println(errorMessage.toString());
            return true;
        } else {
            return false;
        }
    }

}
