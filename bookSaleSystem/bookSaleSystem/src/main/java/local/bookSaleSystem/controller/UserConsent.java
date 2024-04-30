package local.bookSaleSystem.controller;

import jakarta.servlet.http.HttpSession;
import local.bookSaleSystem.bean.Users;
import local.bookSaleSystem.dao.UsersDataAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//[usercreate.html]から[userconsent.html]へ遷移
//処理内容:前ページで入力した内容を、次ページにて再表示(ユーザ側で入力内容の最終確認)
@Controller
public class UserConsent {
    //---フィールド---
    private final UsersDataAccessor usersDataAccessor;
    private final HttpSession usersession;

    //---コンストラクタ---
    @Autowired
    public UserConsent(UsersDataAccessor usersDataAccessor,HttpSession usersession){
        this.usersDataAccessor = usersDataAccessor;
        this.usersession = usersession;
    }

    //---メソッド---
    @RequestMapping("/userconsent")
    public String exec(
            @RequestParam("userid") String userid,
            @RequestParam("userpw") String userpw,
            @RequestParam("sei") String sei,
            @RequestParam("mei") String mei,
            @RequestParam("seif") String seif,
            @RequestParam("meif") String meif,
            @RequestParam("zipcode") String zipcode,
            @RequestParam("address") String address,
            @RequestParam("tel") String tel,
            @RequestParam("mail") String mail,
            Model model
    ){
        //ユーザが入力内容を修正する場合、既にあるsessionを破棄する
        usersession.invalidate();

        Users users = new Users();
        users.setUserid(userid);
        users.setUserpw(userpw);
        users.setSei(sei);
        users.setMei(mei);
        users.setSeif(seif);
        users.setMeif(meif);
        users.setZipcode(zipcode);
        users.setAddress(address);
        users.setTel(tel);
        users.setMail(mail);

        usersession.setAttribute("users", users);

        try{
            //IDの重複があった場合、ページ遷移を中止、メッセージを表示する
            String key = userid;
            if(null != usersDataAccessor.userSearch(key)) {
                String message = "入力いただいたIDは既に使用されています、別のIDを入力してください";
                model.addAttribute("message",message);
                return "usercreate.html";
            }
            return "userconsent.html";
        }catch (EmptyResultDataAccessException e){
            //重複IDが見当たらない場合、次ページへ遷移
            return "userconsent.html";
        }catch (Exception e){
            //ID重複以外で登録時に問題が発生した場合、ページ遷移を中止、メッセージを表示する
            String message = "登録できませんでした。入力内容に誤りがないかご確認いただき、再登録してください";
            model.addAttribute("message",message);
            return "usercreate.html";
        }
    }
}
