package local.bookSaleSystem.controller;

import jakarta.servlet.http.HttpSession;
import local.bookSaleSystem.bean.Users;
import local.bookSaleSystem.dao.UsersDataAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//[login.html]から[login.html] or [top.html]に遷移
//処理内容:
@Controller
public class UserLoginout {
    //----フィールド----
    private final HttpSession usersession;
    private final UsersDataAccessor usersDataAccessor;

    //----コンストラクタ----
    @Autowired
    public UserLoginout(HttpSession usersession,UsersDataAccessor usersDataAccessor){
        this.usersession = usersession;
        this.usersDataAccessor = usersDataAccessor;
    }

    //---メソッド---
    //ログイン処理
    @RequestMapping("/userlogin")
    public String exec1(
            @RequestParam("userid") String userid,
            @RequestParam("userpw") String userpw,
            Model model
    ){
        //@RequestParamで取得したID、パスワードをusers(User型)にset
        Users users = new Users();
        users.setUserid(userid);
        users.setUserpw(userpw);

        //users.dbに登録している場合は、usersessionにusers.dbの情報をsetしてページ遷移
        if(usersDataAccessor.userSearch(users)==true){
            usersession.getAttribute("users");
            return "top.html";
        }

        //ログイン失敗はページ遷移を中止、エラーメッセージを表示
        String message = "ログインできませんでした。再度、IDとパスワードをご入力ください。";
        model.addAttribute("message",message);
        return "login.html";
    }

    //ログアウト処理
    @RequestMapping("/userlogout")
    public String exec2(){
        //usersessionからユーザ情報を削除
        usersession.removeAttribute("users");
        return "login.html";
    }
}
