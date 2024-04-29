package local.bookSaleSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//画面遷移処理
@Controller
public class Transition {
    //---メソッド---
    @RequestMapping(value = {"/index","/login"})
    public String exec0(){
        return "login.html";
    }
    @RequestMapping("")
    public String exec1(){
        return "login.html";
    }
    @RequestMapping("/usercreate")
    public String exec2(){
        return "usercreate.html";
    }
    @RequestMapping("/top")
    public String exec3(){
        return "top.html";
    }
}
