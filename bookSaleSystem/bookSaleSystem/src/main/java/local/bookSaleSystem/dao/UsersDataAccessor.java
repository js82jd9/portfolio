package local.bookSaleSystem.dao;

import jakarta.servlet.http.HttpSession;
import local.bookSaleSystem.bean.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

//users.dbとのやり取り用
@Service
public class UsersDataAccessor {
    //---フィールド---
    private final JdbcTemplate jdbcTemplate;
    private final HttpSession usersession;

    //---コンストラクタ---
    @Autowired
    public UsersDataAccessor(JdbcTemplate jdbcTemplate,HttpSession usersession){
        this.jdbcTemplate = jdbcTemplate;
        this.usersession = usersession;
    }

    //---メソッド---
    //useridを元に検索(ユーザ登録時に重複チェックで使用)
    public Users userSearch(String key){
        Users users = new Users();
        Map<String, Object> result = jdbcTemplate.queryForMap(
                "SELECT userid,userpw,sei,mei,seif,meif,zipcode,address,tel,mail,date FROM users WHERE userid = '" + key + "'");

        return users;
    }

    //userid、userpwを元に検索(ユーザログイン時に登録情報と合致するか使用)
    public boolean userSearch(Users users){
        //レコードが見つかった場合はusersessionにusers.dbの情報を全てsetしてtrueを返す、
        //無ければfalseを返す
        try{
            Map<String, Object> u = jdbcTemplate.queryForMap("SELECT * FROM users WHERE userid = '" + users.getUserid() + "' AND userpw = '" + users.getUserpw() + "'");
            users.setSei(u.get("sei").toString());
            users.setMei(u.get("mei").toString());
            users.setSeif(u.get("seif").toString());
            users.setMeif(u.get("meif").toString());
            users.setZipcode(u.get("zipcode").toString());
            users.setAddress(u.get("address").toString());
            users.setTel(u.get("tel").toString());
            users.setMail(u.get("mail").toString());
            users.setDate(u.get("date").toString());

            usersession.setAttribute("users",users);
            return true;
        }catch (Exception e) {
            return false;
        }

    }

    //ユーザ登録処理
    public void userAdd(Users users){
        jdbcTemplate.update("INSERT INTO users(userid,userpw,sei,mei,seif,meif,zipcode,address,tel,mail,date) VALUES(?,?,?,?,?,?,?,?,?,?,?)",
                users.getUserid(),
                users.getUserpw(),
                users.getSei(),
                users.getMei(),
                users.getSeif(),
                users.getMeif(),
                users.getZipcode(),
                users.getAddress(),
                users.getTel(),
                users.getMail(),
                LocalDateTime.now()
        );
    }
}
