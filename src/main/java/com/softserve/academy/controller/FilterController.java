package com.softserve.academy.controller;

import com.softserve.academy.entity.filter.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users/{uid}/filter")
public class FilterController {

    @PostMapping
    public void createFilter(@RequestBody Filter filter, @PathVariable Integer uid){
        System.out.print(" Success ");
        //return filter;
    }

}
