package local.bookSaleSystem.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//カートアイコンから[cartitemlist.html]へ遷移
//処理内容:ユーザがカートに追加した商品一覧を表示する
@Controller
public class CartItemList {
    //---フィールド--
    private final HttpSession cartsession;

    //---コンストラクタ---
    @Autowired
    public CartItemList(HttpSession cartsession){
        this.cartsession = cartsession;
    }

    //---メソッド---
    //cartsession内にある商品を表示する
    @RequestMapping("/cartitemlist")
    public String exec(Model model){
        if(cartsession.getAttribute("cart")==null) {
            String message = "カート内は空です";
            model.addAttribute("message", message);
        }
        return "cartitemlist.html";
    }
}
