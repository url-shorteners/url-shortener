package com.urlShortener.Url;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "1.Url")
@RequestMapping("/shorten-url")
public class UrlController {

    @RequestMapping(path = "/create",method = RequestMethod.POST)
    @ApiResponse(responseCode = "200",description = "요청에 성공하였습니다.")
    @ApiResponse(responseCode = "400",description = "올바른 URL형식이 아닙니다.")
    public void createUrl(){

    }

    @RequestMapping(path = "/{key}",method = RequestMethod.GET)
    @ApiResponse(responseCode = "301",description = "생성된 단축 URL입니다.")
    public void redirect(@PathVariable String key){

    }

    @RequestMapping(path = "/top-urls",method = RequestMethod.GET)
    @ApiResponse(responseCode = "200",description = "요청에 성공하였습니다.")
    public void popularRequest(){

    }

    @RequestMapping(path = "/keys/{key}/summary",method = RequestMethod.GET)
    @ApiResponse(responseCode = "200",description = "요청에 성공하였습니다.")
    public void countRequest(@PathVariable String key){

    }


}
