package local.bookSaleSystem.dao;

import jakarta.servlet.http.HttpSession;
import local.bookSaleSystem.bean.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

//item.dbとのやり取り用
@Service
public class ItemDateAccessor {
    //---フィールド---
    private final JdbcTemplate jdbcTemplate;
    private final HttpSession itemlistsession;

    //---コンストラクタ--
    @Autowired
    public ItemDateAccessor(JdbcTemplate jdbcTemplate,HttpSession itemlistsession){
        this.jdbcTemplate = jdbcTemplate;
        this.itemlistsession = itemlistsession;
    }

    //---メソッド---
    //全商品をitemlistsessionに追加
    public void itemSearchAll(){
        //itemlistsession挿入用(これを検索結果として表示)
        ArrayList<Item> itemArrayList = new ArrayList<>();

        //item.dbから全レコードを取得
        List<Map<String, Object>> items = jdbcTemplate.queryForList(
                "SELECT * FROM item"
        );

        //item.dbから取得したレコードを順にitem(Item型)にsetしてitemArrayListにaddを繰り返す
        for(Map<String, Object> i : items){
            Item item = new Item();
            item.setItemcode(i.get("itemcode").toString());
            item.setCategoryno((int)i.get("categoryno"));
            item.setItemname(i.get("itemname").toString());
            item.setListprice((int)i.get("listprice"));
            item.setSellingprice((int)i.get("sellingprice"));
            item.setStock((int)i.get("stock"));

            itemArrayList.add(item);
        }

        itemlistsession.setAttribute("itemlist",itemArrayList);
    }

    //ユーザが商品名で検索して、部分一致・完全一致した商品をitemlistsessionに追加
    public boolean itemSearch(String itemnamekey){
        //itemlistsession挿入用(これを検索結果として表示)
        ArrayList<Item> itemArrayList = new ArrayList<>();

        //ユーザの検索条件に合致するレコードのみ取得
        List<Map<String, Object>> items = jdbcTemplate.queryForList(
                "SELECT * FROM item WHERE itemname LIKE '%" + itemnamekey + "%'"
        );

        //取得したレコードが空の場合はfalseを返す
        if(items.isEmpty()){
            return false;
        }

        //item.dbから取得したレコードを順にitem(Item型)にsetしてitemArrayListにaddを繰り返す
        for(Map<String, Object> i : items){
            Item item = new Item();
            item.setItemcode(i.get("itemcode").toString());
            item.setCategoryno((int)i.get("categoryno"));
            item.setItemname(i.get("itemname").toString());
            item.setListprice((int)i.get("listprice"));
            item.setSellingprice((int)i.get("sellingprice"));
            item.setStock((int)i.get("stock"));

            itemArrayList.add(item);
        }

        //itemArrayListをitemlistsessionにset、trueを返す
        itemlistsession.setAttribute("itemlist",itemArrayList);
        return true;
    }

    //商品コードを元に商品詳細情報を取得、item(Item型)で返す
    public Item itemDetailSearch(String itemcode){
        Item item = new Item();

        //item.dbから取得したレコードをitem(Item型)にset
        Map<String, Object> i = jdbcTemplate.queryForMap("SELECT * FROM item WHERE itemcode = '" + itemcode + "'");
        item.setItemcode(i.get("itemcode").toString());
        item.setCategoryno((int)i.get("categoryno"));
        item.setItemname(i.get("itemname").toString());
        item.setListprice((int)i.get("listprice"));
        item.setSellingprice((int)i.get("sellingprice"));
        item.setStock((int)i.get("stock"));

        return item;
    }
}
