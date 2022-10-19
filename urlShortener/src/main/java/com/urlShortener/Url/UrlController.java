package com.urlShortener.Url;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "1.Url")
@RequestMapping("/shortenUrl")
public class UrlController {

    @RequestMapping(path = "/create",method = RequestMethod.POST)
    public void createUrl(){

    }

    @RequestMapping(path = "/urlChanged",method = RequestMethod.GET)
    public void redirect(){

    }

    @RequestMapping(path = "/popularRequest",method = RequestMethod.GET)
    public void popularRequest(){

    }

    @RequestMapping(path = "/countRequest",method = RequestMethod.GET)
    public void countRequest(){

    }


}
