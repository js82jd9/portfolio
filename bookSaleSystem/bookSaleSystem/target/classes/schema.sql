--カテゴリー情報(テーブル)
CREATE TABLE IF NOT EXISTS category(
    categoryno int,   --カテゴリー番号
    categoryname VARCHAR(50), --カテゴリー名
    PRIMARY KEY (categoryno)
);

--商品情報
CREATE TABLE IF NOT EXISTS item(
    itemcode VARCHAR(10),   --商品コード
    categoryno int,         --カテゴリーコード
    itemname VARCHAR(100),  --商品名
    listprice　int,         --定価
    sellingprice int,       --売価
    stock int,              --在庫
    PRIMARY KEY (itemcode),
    FOREIGN KEY(categoryno) REFERENCES category(categoryno)
);

--ユーザ情報
CREATE TABLE IF NOT EXISTS users(
     userid VARCHAR(50),   --ユーザ番号
     userpw VARCHAR(50),   --ユーザパスワード
     sei VARCHAR(15),      --姓
     mei VARCHAR(15),      --名
     seif VARCHAR(15),     --姓(ふりがな)
     meif VARCHAR(15),     --名(ふりがな)
     zipcode VARCHAR(10),  --郵便番号
     address VARCHAR(100), --住所
     tel VARCHAR(15),      --電話番号
     mail VARCHAR(50),     --メールアドレス
     date TIMESTAMP,        --登録日
     PRIMARY KEY (userid)
);

--ユーザ注文情報
CREATE TABLE IF NOT EXISTS userorder(
    no int,                 --番号
    orderno VARCHAR(10),    --注文番号
    itemcode VARCHAR(10),   --商品コード
    quantity int,           --商品注文数量
    userid VARCHAR(10),   　--ユーザ番号
    dsei VARCHAR(15),       --姓(送付先)
    dmei VARCHAR(15),       --名(送付先)
    dzipcode VARCHAR(10),   --郵便番号(送付先)
    daddress VARCHAR(100),  --住所(送付先)
    dtel VARCHAR(15),       --電話番号(送付先)
    orderdatetime DATETIME, --注文日時
    PRIMARY KEY (no),
    FOREIGN KEY(itemcode) REFERENCES item(itemcode),
    FOREIGN KEY(userid) REFERENCES users(userid)
);

--ゲスト注文情報
CREATE TABLE IF NOT EXISTS guestorder(
    no int,                --番号
    orderno VARCHAR(10),   --注文番号
    itemcode VARCHAR(10),  --商品コード
    quantity int,          --商品注文数量
    sei VARCHAR(15),       --姓
    mei VARCHAR(15),       --名
    seif VARCHAR(15),      --姓(ふりがな)
    meif VARCHAR(15),      --名(ふりがな)
    zipcode VARCHAR(10),   --郵便番号
    address VARCHAR(100),  --住所
    tel VARCHAR(15),       --電話番号
    mail VARCHAR(50),      --メールアドレス
    dsei VARCHAR(15),      --姓(送付先)
    dmei VARCHAR(15),      --名(送付先)
    dzipcode VARCHAR(10),  --郵便番号(送付先)
    daddress VARCHAR(100), --住所(送付先)
    dtel VARCHAR(15),      --電話番号(送付先)
    PRIMARY KEY (no),
    FOREIGN KEY(itemcode) REFERENCES item(itemcode)
);