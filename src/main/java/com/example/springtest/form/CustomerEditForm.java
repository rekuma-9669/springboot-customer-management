package com.example.springtest.form;

/**
 * 顧客編集画面で使用するフォームデータ。
 *
 * 顧客編集画面から送信された入力値を保持し、
 * 顧客情報の更新処理に使用する。
 *
 * @param customerId 顧客ID
 * @param firstName  名
 * @param lastName   姓
 * @param email      メールアドレス
 * @param active     顧客の有効状態
 * @param storeId    店舗ID
 */
public record CustomerEditForm(
        int customerId,
        String firstName,
        String lastName,
        String email,
        int active,
        int storeId
) {}
