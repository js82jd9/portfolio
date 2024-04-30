package local.bookSaleSystem.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//
@Controller
public class CartInOut {
    //---フィールド--
    private final HttpSession cartsession;

    //---コンストラクタ---
    @Autowired
    public CartInOut(HttpSession cartsession){
        this.cartsession = cartsession;
    }

    //---メソッド---
    //cartsessionに商品を追加
    @RequestMapping("/cartitemadd")
    public String exec1(){
        return "top.html";
    }

    //cartsession内の商品削除
    @RequestMapping("/cartitemdelete")
    public String exec2(){
        return "cartitemlist.html";
    }

    //cartsession内の商品数量修正
    @RequestMapping("/cartitemcorrection")
    public String exec3(){
        return "cartitemlist.html";
    }
}
