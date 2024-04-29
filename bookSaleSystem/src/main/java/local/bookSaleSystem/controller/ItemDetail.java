package local.bookSaleSystem.controller;

import local.bookSaleSystem.bean.Item;
import local.bookSaleSystem.dao.ItemDateAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//[itemsearchresults.html]から[itemdetail.html]へ遷移
//処理内容:ユーザが選択した商品の詳細ページ表示
@Controller
public class ItemDetail {
    //---フィールド---
    private final ItemDateAccessor itemDateAccessor;

    //---コンストラクタ---
    public ItemDetail(ItemDateAccessor itemDateAccessor){
        this.itemDateAccessor = itemDateAccessor;
    }

    //---メソッド---
    //@RequestParamで取得した商品コードを元に、商品詳細ページに商品情報を表示
    @RequestMapping("/itemdetail")
    public String exec(
            @RequestParam("itemcode") String itemcode,
            Model model
    ){
        //商品コードを元に取得した商品情報をmodel(Model型)にadd、ページ遷移
        try{
            Item item = itemDateAccessor.itemDetailSearch(itemcode);
            model.addAttribute("item",item);
        }catch(Exception e){
            //商品詳細情報の取得に失敗した場合、遷移を中止、メッセージを表示
            String message = "お客様が選択された商品は、現在お取り扱いしておりません。";
            model.addAttribute("message",message);
            return "itemsearchresults.html";
        }
        return "itemdetail.html";
    }
}
