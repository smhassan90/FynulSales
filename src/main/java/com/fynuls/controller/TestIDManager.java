package com.fynuls.controller;

import com.fynuls.entity.login.User;
import com.fynuls.utils.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * $Date
 */
@Controller
public class TestIDManager {

    public static Logger LOG = LogManager.getLogger(TestIDManager.class);

        @RequestMapping(value = "/testid", method = RequestMethod.GET)
    @ResponseBody
    public String index(){

            List<User> users= new ArrayList<>();
            users = (List<User>) HibernateUtil.getMyDBObjects("from User");
        //long id = HibernateUtil.getNextBaseID(4);
        return "Done " ;
    }
}
