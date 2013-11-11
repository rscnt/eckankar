package io.rscnt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    @RequestMapping(value="/")
    public String index () {
        return "base";
    }
    
	public IndexController() {
		// TODO Auto-generated constructor stub
	}

}
