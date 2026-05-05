package com.example.springtest.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import com.example.springtest.form.CustomerCreateForm;
import com.example.springtest.service.CustomerService;

/**
 * 顧客新規登録に関する画面遷移を処理するコントローラー。
 *
 * 新規登録画面の表示、確認画面への遷移、
 * および顧客登録確定処理を担当する。
 */

@Controller
public class CustomerController {

    private final CustomerService customerService;

    /**
     * 顧客登録用サービスを受け取ってコントローラーを初期化する。
     *
     * @param customerService 顧客登録処理を担当するサービス
     */

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * 顧客新規登録画面を表示する。
     *
     * 入力フォームと都市一覧を画面に渡す。
     *
     * @param model 画面へ渡すデータを保持するModel
     * @return 顧客新規登録画面
     * @throws SQLException 都市一覧取得時にエラーが発生した場合
     */

    @GetMapping("/customers/new")
    public String showCreate(Model model){
        model.addAttribute("form", new CustomerCreateForm());
        model.addAttribute("cities", customerService.findAllCities());
        return "customer-new";
    }

    /**
     * 入力内容を確認画面に表示する。
     *
     * バリデーションエラーがある場合は入力画面へ戻す。
     *
     * @param form 顧客登録フォーム
     * @param bindingResult バリデーション結果
     * @param model 画面へ渡すデータを保持するModel
     * @return 確認画面、または入力画面
     */

    @PostMapping("/customers/new/confirm")
    public String confirmCreate(
            @Valid @ModelAttribute("form") CustomerCreateForm form,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("cities", customerService.findAllCities());
            return "customer-new";
        }

        return "customer-new-confirm";
    }

    /**
     * 顧客を新規登録する。
     *
     * @param form 顧客登録フォーム
     * @return 検索画面へのリダイレクト
     * @throws SQLException 登録処理中にエラーが発生した場合
     */

    @PostMapping("/customers/new")
    public String create(@ModelAttribute("form") CustomerCreateForm form){
        customerService.createCustomer(form);
        return "redirect:/search";
    }
}
