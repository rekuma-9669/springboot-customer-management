# Spring Boot Customer Management

Spring Boot / MyBatis / Thymeleaf / Docker を使用して作成した顧客管理Webアプリケーションです。
MySQL のサンプルデータベース **Sakila** を利用し、顧客検索・詳細表示・新規登録・編集などの機能を実装しています。

Spring Boot を用いた Web アプリケーション開発と、Docker を利用した開発環境構築の学習アウトプットとして作成しました。

---

# 使用技術

| 技術                      | 内容                 |
| ----------------------- | ------------------ |
| Java                    | 17                 |
| Spring Boot             | Webアプリケーションフレームワーク |
| Thymeleaf               | テンプレートエンジン         |
| MyBatis                 | ORM（SQLマッピング）      |
| MySQL                   | データベース             |
| Docker / Docker Compose | 開発環境構築             |
| Maven                   | ビルドツール             |
| Lombok                  | Javaコード簡略化         |

---

# 主な機能

* 顧客検索
* 顧客詳細表示
* 顧客新規登録
* 顧客情報編集
* 顧客停止（アクティブ状態の変更）

---

# 画面一覧

* 顧客検索画面
* 顧客詳細画面
* 顧客新規登録画面
* 顧客新規登録確認画面
* 顧客編集画面
* 削除（停止）確認画面

---

# プロジェクト構成

```
src/main/java/com/example/springtest

controller
 ├ CustomerController
 └ SearchController

service
 ├ CustomerService
 └ SearchService

mapper
 └ CustomerMapper

form
 ├ CustomerCreateForm
 ├ CustomerEditForm
 └ SearchForm

dto
 ├ CityOption
 ├ CustomerDetail
 └ CustomerSummary

param
 ├ AddressInsertParam
 └ CustomerInsertParam
```

```
src/main/resources

mapper
 └ CustomerMapper.xml

templates
 ├ search.html
 ├ detail.html
 ├ customer-new.html
 ├ customer-new-confirm.html
 ├ customer-edit.html
 ├ edit-confirm.html
 └ delete-confirm.html
```

---

# 起動方法

## 1. リポジトリを取得

```
git clone https://github.com/rekuma-9669/springboot-customer-management.git
cd springboot-customer-management
```

---

## 2. MySQL (Docker) を起動

```
docker compose up -d
```

MySQL コンテナが起動し、初回起動時に `initdb` フォルダ内の SQL によって
Sakila データベースが自動投入されます。

---

## 3. アプリケーションをビルド

```
mvn clean package
```

---

## 4. アプリケーションを起動

### Eclipse / Pleiades から起動

`SpringtestApplication` を実行

---

### Dockerから起動

```
docker compose up -d --build
```

---

# アクセスURL
```
http://localhost:8080/search
```

---

# 工夫した点

* Controller / Service / Mapper に役割を分割し、責務を整理しました
* MyBatis を使用し SQL と Java コードを分離しました
* Form / DTO / Param パッケージを分け、データの役割を明確にしました
* Docker Compose を使用し、MySQL と Sakila データベースを自動構築できるようにしました

---

# 学習ポイント

このアプリケーションを通して以下の内容を学習しました。

* Spring Boot による Web アプリケーション開発
* Thymeleaf を使用した画面表示
* MyBatis によるデータベース操作
* Docker を利用した開発環境構築
* MVC構造によるアプリケーション設計

---

# 今後の改善

* テストコードの追加
* 例外処理の共通化
* 画面UIの改善
* Springアプリケーションの完全Docker化
* バリデーションの強化

---
