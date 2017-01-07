package com.sp.allinone.config.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

/**
 * Created by i82298 on 1/6/2017.
 */

@Controller
public abstract class AbsctractController {

    public AbsctractController() {
        init();
    }

    @PostConstruct
    public void init() {
        System.out.println("Hello----------------------------------44444444444444444444444444444444444444444444ooo" +
                "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" +
                "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" +
                "k" +
                "kkkkkkkkkkkkkkkkkkkkkkkkkk" +
                "kkkkkkk" +
                "kkkkkkkkkkkkkkkkkkk" +
                "kkkkkkkkkkkkkkkk" +
                "kkkkk" +
                "///////////////////" +
                "//////////////////" +
                "//////////////");
    }


}
