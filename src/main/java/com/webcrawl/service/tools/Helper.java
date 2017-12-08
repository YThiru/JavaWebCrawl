package com.webcrawl.service.tools;

import com.webcrawl.service.MainCrawl;
import org.apache.commons.validator.routines.UrlValidator;

public class Helper {

    public String validateInput(String[] args) {
        if(args.length != 2) {
            return "Please enter url and crawl stage";
        } else {
            if (!args[1].matches(MainCrawl.integerRegEx)) {
                return "Invalid crawl stage number";
            }

            UrlValidator urlValidator = new UrlValidator();
            if (!urlValidator.isValid(args[0])){
                return "Invalid URL";
            }
            return null;
        }
    }
}
