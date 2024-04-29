package local.bookSaleSystem.bean;

//ユーザ一時保存用
public class Users {
    //---フィールド---
    private String userid;  //ユーザ番号
    private String userpw;  //ユーザパスワード
    private String sei;     //姓
    private String mei;     //名
    private String seif;    //姓(ふりがな)
    private String meif;    //名(ふりがな)
    private String zipcode; //郵便番号
    private String address; //住所
    private String tel;     //電話番号
    private String mail;    //メールアドレス
    private String date;    //登録日

    //---コンストラクタ---
    public Users(){
        this.userid = "";
        this.userpw = "";
        this.sei = "";
        this.mei = "";
        this.seif = "";
        this.meif = "";
        this.zipcode = "";
        this.address = "";
        this.tel = "";
        this.mail = "";
        this.date = "";
    }

    //---メソッド---
    //===set===
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public void setUserpw(String userpw) {
        this.userpw = userpw;
    }
    public void setSei(String sei) {
        this.sei = sei;
    }
    public void setMei(String mei) {
        this.mei = mei;
    }
    public void setSeif(String seif) {
        this.seif = seif;
    }
    public void setMeif(String meif) {
        this.meif = meif;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public void setDate(String date) {
        this.date = date;
    }

    //===get===
    public String getUserid() {
        return userid;
    }
    public String getUserpw() {
        return userpw;
    }
    public String getSei() {
        return sei;
    }
    public String getMei() {
        return mei;
    }
    public String getSeif() {
        return seif;
    }
    public String getMeif() {
        return meif;
    }
    public String getZipcode() {
        return zipcode;
    }
    public String getAddress() {
        return address;
    }
    public String getTel() {
        return tel;
    }
    public String getMail() {
        return mail;
    }
    public String getDate() {
        return date;
    }
}
