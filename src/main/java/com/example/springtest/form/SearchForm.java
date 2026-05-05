package com.example.springtest.form;

import lombok.Getter;
import lombok.Setter;

/**
 * 顧客検索画面の検索条件を保持するフォームクラス。
 *
 * 検索画面で入力された条件を保持し、
 * 顧客検索処理に使用する。
 */
@Getter
@Setter
public class SearchForm {

    /** 姓（検索条件） */
    private String lastName;

    /** 名（検索条件） */
    private String firstName;

    /** メールアドレス（検索条件） */
    private String email;

    /** 顧客の有効状態（検索条件） */
    private String active;

    /** 国名（検索条件） */
    private String country;

}
