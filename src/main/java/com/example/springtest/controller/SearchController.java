package com.example.springtest.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springtest.dto.CustomerDetail;
import com.example.springtest.dto.CustomerSummary;
import com.example.springtest.form.CustomerEditForm;
import com.example.springtest.service.SearchService;

/**
 * 顧客検索・詳細表示・編集・停止処理を担当するコントローラー。
 *
 * 検索画面、詳細画面、編集画面、停止確認画面への遷移と、
 * 各操作に対応するリクエストを処理する。
 */

@Controller
public class SearchController {

    private final SearchService searchService;

    /**
     * 顧客検索用サービスを受け取ってコントローラーを初期化する。
     *
     * @param searchService 顧客検索・詳細取得・更新処理を担当するサービス
     */

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * 顧客検索を行い、検索結果を画面に表示する。
     *
     * 入力された検索条件に応じて顧客一覧を取得し、
     * 画面へ検索条件と結果を渡す。
     *
     * @param lastName 姓
     * @param firstName 名
     * @param email メールアドレス
     * @param active 登録状態
     * @param country 国名
     * @param model 画面へ渡すデータを保持するModel
     * @return 検索画面
     * @throws SQLException 検索処理中にエラーが発生した場合
     */

    @GetMapping("/search")
    public String search(
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String active,
            @RequestParam(required = false) String country,
            @RequestParam(defaultValue = "1") int page,
            Model model
    ) throws SQLException {

        List<String> countries = List.of("Japan", "United States", "France", "Brazil");
        model.addAttribute("countries", countries);
        int limit = 20;
        int offset = (page - 1) * limit;


        Integer activeInt = (active == null || active.isBlank()) ? null : Integer.valueOf(active);

        boolean hasAny =
                (lastName != null && !lastName.isBlank()) ||
                (firstName != null && !firstName.isBlank()) ||
                (email != null && !email.isBlank()) ||
                activeInt != null ||
                (country != null && !country.isBlank());

        int totalCount = hasAny
                ? searchService.countCustomers(lastName, firstName, email, activeInt, country)
                : 0;

        int totalPages = (int) Math.ceil((double) totalCount / limit);

        if (totalPages == 0) {
            totalPages = 1;
        }

        List<CustomerSummary> customers = hasAny
                ? searchService.searchCustomers(lastName, firstName, email, activeInt, country, offset, limit)
                : List.of();

        model.addAttribute("customers", customers);
        model.addAttribute("qLastName", lastName);
        model.addAttribute("qFirstName", firstName);
        model.addAttribute("qEmail", email);
        model.addAttribute("qActive", active);
        model.addAttribute("qCountry", country);
        model.addAttribute("currentPage", page);
        model.addAttribute("hasPrevious", page > 1);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("hasNext", page < totalPages);
        return "search";
    }

    /**
     * 顧客詳細画面を表示する。
     *
     * 指定された顧客IDをもとに詳細情報を取得し、
     * 画面に表示する。
     *
     * @param id 顧客ID
     * @param lastName 検索条件の姓
     * @param firstName 検索条件の名
     * @param email 検索条件のメールアドレス
     * @param active 検索条件の登録状態
     * @param country 検索条件の国名
     * @param model 画面へ渡すデータを保持するModel
     * @return 顧客詳細画面
     * @throws SQLException 詳細取得時にエラーが発生した場合
     */

    @GetMapping("/customers/{id:\\d+}")
    public String showDetail(
            @PathVariable int id,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String active,
            @RequestParam(required = false) String country,
            Model model
    ) throws SQLException {

        CustomerDetail detail = searchService.findCustomerDetailById(id);

        model.addAttribute("customer", detail);

        model.addAttribute("lastName", lastName);
        model.addAttribute("firstName", firstName);
        model.addAttribute("email", email);
        model.addAttribute("active", active);
        model.addAttribute("country", country);

        return "detail";
    }

    /**
     * 顧客編集画面を表示する。
     *
     * 指定された顧客IDの情報を取得し、
     * 編集フォームに設定して画面へ渡す。
     *
     * @param id 顧客ID
     * @param model 画面へ渡すデータを保持するModel
     * @return 顧客編集画面
     * @throws SQLException 顧客情報取得時にエラーが発生した場合
     */

    @GetMapping("/customers/{id:\\d+}/edit")
    public String showEdit(@PathVariable int id, Model model) throws SQLException {

        CustomerDetail detail = searchService.findCustomerDetailById(id);

        CustomerEditForm form = new CustomerEditForm(
                detail.customerId(),
                detail.firstName(),
                detail.lastName(),
                detail.email(),
                detail.active(),
                detail.storeId()
        );

        model.addAttribute("form", form);
        return "customer-edit";
    }

    /**
     * 顧客編集内容を確認画面に表示する。
     *
     * 入力された編集内容を現在の顧客情報と組み合わせて
     * 確認用データを作成する。
     *
     * @param id 顧客ID
     * @param lastName 姓
     * @param firstName 名
     * @param email メールアドレス
     * @param active 登録状態
     * @param storeId 店舗ID
     * @param model 画面へ渡すデータを保持するModel
     * @return 編集確認画面
     * @throws SQLException 顧客情報取得時にエラーが発生した場合
     */

    @PostMapping("/customers/{id}/edit/confirm")
    public String confirm(
            @PathVariable int id,
            @RequestParam String lastName,
            @RequestParam String firstName,
            @RequestParam String email,
            @RequestParam int active,
            @RequestParam int storeId,
            Model model
    ) throws SQLException {

        CustomerDetail current = searchService.findCustomerDetailById(id);

        CustomerEditForm edited = new CustomerEditForm(
                current.customerId(),
                firstName,
                lastName,
                email,
                active,
                storeId
        );

        model.addAttribute("customer", edited);
        return "edit-confirm";
    }

    /**
     * 顧客情報を更新する。
     *
     * @param id 顧客ID
     * @param form 顧客編集フォーム
     * @return 顧客詳細画面へのリダイレクト
     * @throws SQLException 更新処理中にエラーが発生した場合
     */

    @PostMapping("/customers/{id:\\d+}/edit")
    public String update(
            @PathVariable int id,
            @ModelAttribute("form") CustomerEditForm form
    ) throws SQLException {

        searchService.updateCustomer(form);
        return "redirect:/customers/" + id;
    }

    /**
     * 顧客停止確認画面を表示する。
     *
     * 指定された顧客IDの情報を取得し、
     * 停止対象として確認画面へ渡す。
     *
     * @param id 顧客ID
     * @param model 画面へ渡すデータを保持するModel
     * @return 停止確認画面
     * @throws SQLException 顧客情報取得時にエラーが発生した場合
     */

    @GetMapping("/customers/{id:\\d+}/delete")
    public String showDeleteConfirm(@PathVariable int id, Model model){

        CustomerDetail detail = searchService.findCustomerDetailById(id);
        model.addAttribute("detail", detail);
        return "delete-confirm";
    }

    /**
     * 顧客を停止する。
     *
     * @param id 顧客ID
     * @return 顧客詳細画面へのリダイレクト
     * @throws SQLException 停止処理中にエラーが発生した場合
     */

    @PostMapping("/customers/{id:\\d+}/delete")
    public String delete(@PathVariable int id){

        searchService.deactivateCustomer(id);
        return "redirect:/customers/" + id;
    }
}