package com.abdogouda.springsessionredisdocker.controller;

import com.abdogouda.springsessionredisdocker.config.InstanceInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
public class PagesController {

    private static Logger log = LoggerFactory.getLogger(PagesController.class);

    @Autowired
    private InstanceInformationService instanceInformationService;



    @RequestMapping("/")
    public String getIndex(Model model, HttpSession session){
        final InetAddress localHost;
        try {
            localHost = InetAddress.getLocalHost();
            model.addAttribute("host", instanceInformationService.retrieveInstanceInfo());
            model.addAttribute("ip", localHost.getHostAddress());
            session.setAttribute("name","abdo"+instanceInformationService.retrieveInstanceInfo());
        } catch (UnknownHostException e) {
            log.warn("An exception occurred while trying to determine the host and IP address: {}", e);
        }
        return "home";
    }

    @RequestMapping("/hello")
    public String getHello(Model model,HttpSession session){
        final InetAddress localHost;
        try {
            localHost = InetAddress.getLocalHost();
            model.addAttribute("host", instanceInformationService.retrieveInstanceInfo());
            model.addAttribute("ip", localHost.getHostAddress());
            model.addAttribute("name", session.getAttribute("name"));
        } catch (UnknownHostException e) {
            log.warn("An exception occurred while trying to determine the host and IP address: {}", e);
        }
        return "hello";
    }

}