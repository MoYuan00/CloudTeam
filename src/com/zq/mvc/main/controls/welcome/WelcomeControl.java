package com.zq.mvc.main.controls.welcome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeControl {

    @RequestMapping("/index.do")
    public ModelAndView exec(@Autowired ModelAndView mv){
        mv.setViewName("index.html");
        return mv;
    }
}
