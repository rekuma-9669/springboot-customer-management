package com.example.springtest.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 顧客新規登録画面の入力値を保持するフォームクラス。
 *
 * 顧客登録画面で入力された氏名、メールアドレス、
 * 住所情報などを受け取り、登録処理へ渡すために使用する。
 */

@Getter
@Setter
public class CustomerCreateForm {

	/** 名 */
    @NotBlank(message = "名は必須です")
    @Size(max = 45, message = "名は45文字以内で入力してください")
    private String firstName;

    /** 姓 */
    @NotBlank(message = "姓は必須です")
    @Size(max = 45, message = "姓は45文字以内で入力してください")
    private String lastName;

    /** メールアドレス */
    @NotBlank(message = "メールアドレスは必須です")
    @Email(message = "メールアドレスの形式で入力してください")
    private String email;

    /** 登録状態 */
    @NotBlank(message = "登録状態を選択してください")
    private String active;

    /** 住所 */
    @NotBlank(message = "住所は必須です")
    @Size(max = 50, message = "住所は50文字以内で入力してください")
    private String address;

    /** 地区 */
    @NotBlank(message = "地区は必須です")
    @Size(max = 20, message = "地区は20文字以内で入力してください")
    private String district;

    /** 都市ID */
    @NotBlank(message = "都市を選択してください")
    private String cityId;

    /** 郵便番号 */
    @Size(max = 10, message = "郵便番号は10文字以内で入力してください")
    private String postalCode;

    /** 電話番号 */
    @NotBlank(message = "電話番号は必須です")
    @Size(max = 20, message = "電話番号は20文字以内で入力してください")
    private String phone;

    /** 店舗ID */
    @NotBlank(message = "店舗を選択してください")
    private String storeId;
}
