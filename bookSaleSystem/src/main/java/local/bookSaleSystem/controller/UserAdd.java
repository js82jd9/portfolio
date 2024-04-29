package local.bookSaleSystem.controller;

import jakarta.servlet.http.HttpSession;
import local.bookSaleSystem.bean.Users;
import local.bookSaleSystem.dao.UsersDataAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//[userconsent.html]から[usercreatecompletion.html]へ遷移
//処理内容:ユーザが入力したユーザ情報をusers.dbへ登録
@Controller
public class UserAdd {
    //---フィールド---
    private final HttpSession usersession;
    private final UsersDataAccessor usersDataAccessor;

    //---コンストラクタ---
    @Autowired
    public UserAdd(HttpSession usersession, UsersDataAccessor usersDataAccessor){
        this.usersession = usersession;
        this.usersDataAccessor = usersDataAccessor;
    }

    //---メソッド---
    //ユーザ登録処理
    @RequestMapping("/useradd")
    public String exec(
            Model model
    ){
        try {
            Users users = (Users) usersession.getAttribute("users");
            usersDataAccessor.userAdd(users);
            return "usercreatecompletion.html";
        }catch (Exception e){
            //何らかの理由でusers.dbへの登録が出来なかった場合、[usercreate.html]]ページへ遷移後、メッセージを表示
            String message = "登録できませんでした。入力内容に誤りがないかご確認いただき、再登録してください";
            model.addAttribute("message",message);
            return "usercreate.html";
        }
    }
}
