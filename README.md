# 使用した技術要素

java 1.8.0_172
play framework 2.6

# 全体の設計・構成について

## ディレクトリの構成

```
app
 ├-models
 |   └-Product.java     //Productモデル
 |
 ├-controllers
 |       └-ProductController.java   //コントローラー
 |
 └conf
    └-routes   //各アクションへのパス
```

## APIについて

|メソッド|URL|機能
|-------|---|---|
|GET|/products|商品一覧取得|
|POST|/products|一件の商品を登録|
|GET|/products/search|商品の検索|
|PUT|/products/:id|選択した商品の編集|
|DELETE|/products/:id|選択した商品を削除|


# 開発環境のセットアップ手順

## javaのインストール

```
brew cask install java8
export JAVA_HOME=`/usr/libexec/java_home -v 1.8
```

##sbtのインストール

```
brew install sbt
```

## playのインストール

```
https://github.com/YukiYanagikubo/restful-api.git
cd restful-api
```

## playの起動

```
sbt run
```

