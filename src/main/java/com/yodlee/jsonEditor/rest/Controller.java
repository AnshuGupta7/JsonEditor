/*
 * Copyright (c) 2016 Envestnet | Yodlee, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Envestnet | Yodlee, Inc. 
 * Use is subject to license terms.
 */
package com.yodlee.jsonEditor.rest;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@org.springframework.stereotype.Controller
public class Controller {

    private static final String FQCN = Controller.class.getName();
    private static Logger logger = LogManager.getLogger(FQCN);

   /* @Autowired
    private ApplicationContext applicationContext;*/

   /* @Autowired
    ServletContext servletContext;*/

    private static JSONObject dataJsonObject = null;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage() {
        return "index";
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHoldingPage() {
        return "holding";
    }
}