package local.bookSaleSystem.bean;

//商品一時保存用
public class Item {
    //---フィールド---
    private String itemcode;  //商品コード
    private int categoryno;   //カテゴリーコード
    private String itemname;  //商品名
    private int listprice;    //定価
    private int sellingprice; //売価
    private int stock;        //在庫

    //---コンストラクタ---
    public Item(){
        this.itemcode = "";
        this.categoryno = 0;
        this.itemname = "";
        this.listprice = 0;
        this.sellingprice = 0;
        this.stock = 0;
    }

    //---メソッド---
    //===set===
    public void setItemcode(String itemcode){
        this.itemcode = itemcode;
    }
    public void setCategoryno(int categoryno){
        this.categoryno = categoryno;
    }
    public void setItemname(String itemname){
        this.itemname = itemname;
    }
    public void setListprice(int listprice){
        this.listprice = listprice;
    }
    public void setSellingprice(int sellingprice){
        this.sellingprice = sellingprice;
    }
    public void setStock(int stock){
        this.stock = stock;
    }

    //===get===
    public String getItemcode(){
        return itemcode;
    }
    public int getCategoryno(){
        return categoryno;
    }
    public String getItemname(){
        return itemname;
    }
    public int getListprice(){
        return listprice;
    }
    public int getSellingprice(){
        return sellingprice;
    }
    public int getStock(){
        return stock;
    }
}
