package local.bookSaleSystem.controller;

import jakarta.servlet.http.HttpSession;
import local.bookSaleSystem.dao.ItemDateAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//[top.html]から[itemsearchresults.html]へ遷移
//処理内容:ユーザが入力したキーワードを元に部分・完全一致する商品を検索
@Controller
public class ItemSearch {
    //---フィールド---
    private final ItemDateAccessor itemDateAccessor;
    private final HttpSession itemlistsession;

    //---コンストラクタ---
    @Autowired
    public ItemSearch(ItemDateAccessor itemDateAccessor,HttpSession itemlistsession){
        this.itemDateAccessor = itemDateAccessor;
        this.itemlistsession = itemlistsession;
    }

    //---メソッド---
    //商品検索
    @RequestMapping("/itemsearch")
    public String exec(
            @RequestParam("itemnamekey") String itemnamekey,
            Model model
    ){
        //ユーザが検索する前に、保存している検索内容(itemlistsession)を削除する
        itemlistsession.removeAttribute("itemlist");

        //@RequestParamで受け取ったitemnamekeyが空白なら全商品表示、
        //空白でなければitemnamekeyを元に商品名を検索
        try {
            if (itemnamekey.isBlank()) {
                itemDateAccessor.itemSearchAll();
            }
            boolean result = itemDateAccessor.itemSearch(itemnamekey);
            if(result==false){
                //商品検索をして該当する商品がない場合、遷移を中止、メッセージを表示
                String message = "該当商品が見つかりませんでした、検索ワードを変えて再検索してください";
                model.addAttribute("message",message);
                return "top.html";
            }
        }catch (Exception e){
            //何らかの異常が発生した場合、遷移を中止、メッセージを表示
            String message = "該当商品が見つかりませんでした、検索ワードを変えて再検索してください";
            model.addAttribute("message",message);
            return "top.html";
        }
        return "itemsearchresults.html";
    }
}
