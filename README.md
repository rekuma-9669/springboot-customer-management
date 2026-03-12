# Spring Boot Customer Management

Spring Boot / MyBatis / Thymeleaf を使用して作成した顧客管理Webアプリケーションです。
Sakilaデータベースを使用し、顧客の検索・登録・編集・停止機能を実装しています。

Docker を使用して MySQL 環境を構築しています。

---

# Application Overview

顧客情報を管理するためのWebアプリケーションです。

主な機能

* 顧客検索
* 顧客登録
* 顧客編集
* 顧客停止
* ページング
* 検索結果件数表示
* モーダルによる削除確認

---

# Tech Stack

| Technology  | Description          |
| ----------- | -------------------- |
| Java        | 17                   |
| Spring Boot | Web Framework        |
| MyBatis     | ORM / SQL Mapper     |
| Thymeleaf   | Template Engine      |
| Bootstrap   | UI Framework         |
| MySQL       | Database             |
| Docker      | Database Environment |
| Maven       | Build Tool           |

---

# Architecture

Layered architecture を意識して実装しています。

Controller
↓
Service
↓
Mapper (MyBatis)
↓
Database (MySQL)

---

# Features

## Customer Search

顧客情報を条件指定して検索できます。

検索条件

* 姓
* 名
* Email
* 有効状態
* 国

検索結果はページング表示されます。

---

## Customer Detail

顧客の詳細情報を確認できます。

表示情報

* 顧客ID
* 氏名
* Email
* 有効状態
* 住所
* 電話番号
* 店舗

---

## Customer Create

顧客を新規登録できます。

入力項目

* 姓
* 名
* Email
* 有効状態
* 住所
* 地区
* 都市
* 郵便番号
* 電話番号
* 店舗

バリデーションを実装しています。

---

## Customer Update

顧客情報を編集できます。

---

## Customer Deactivate

顧客を停止状態に変更できます。

削除確認には **モーダルウィンドウ** を使用しています。

---

# Screenshots

## Search Page

![search](docs/search.png)

---

## Customer Detail

![detail](docs/detail.png)

---

## Customer Create

![create](docs/create.png)

---

# Project Structure

src/main/java

controller
service
mapper
dto
form
param

src/main/resources

templates
mapper
static

---

# Setup

## 1 Start MySQL container

docker compose up -d

---

## 2 Import Sakila database

docker exec -i springtest-mysql mysql -uroot -proot sakila < sakila-schema.sql
docker exec -i springtest-mysql mysql -uroot -proot sakila < sakila-data.sql

---

## 3 Start Application

mvn spring-boot:run

---

# Access

http://localhost:8080

---

# Improvements

今後の改善予定

* ソート機能
* ログイン機能
* REST API対応
* テストコード追加

