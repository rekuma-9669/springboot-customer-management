package com.example.springtest.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springtest.dto.CustomerDetail;
import com.example.springtest.dto.CustomerSummary;
import com.example.springtest.form.CustomerEditForm;
import com.example.springtest.mapper.CustomerMapper;

/**
 * 顧客検索・詳細取得・更新・停止に関する業務ロジックを扱うサービスクラス。
 */

@Service
public class SearchService {

	private final CustomerMapper customerMapper;

	/**
	 * 顧客用Mapperを受け取ってサービスを初期化する。
	 *
	 * @param customerMapper 顧客情報のDBアクセスを担当するMapper
	 */

	public SearchService(CustomerMapper customerMapper) {
	    this.customerMapper = customerMapper;
	}

	/**
	 * 条件をもとに顧客検索を行う。
	 *
	 * @param lastName 姓
	 * @param firstName 名
	 * @param email メールアドレス
	 * @param active 登録状態
	 * @param country 国名
	 * @return 顧客一覧
	 * @throws SQLException 検索処理中にエラーが発生した場合
	 */

	public List<CustomerSummary> searchCustomers(String lastName, String firstName, String email, Integer active,
			String country){
		return customerMapper.searchCustomers(lastName, firstName, email, active, country);
	}

	/**
	 * 顧客IDをもとに顧客詳細を取得する。
	 *
	 * @param id 顧客ID
	 * @return 顧客詳細
	 */

	public CustomerDetail findCustomerDetailById(int id) {
		return customerMapper.findCustomerDetailById(id);
	}

	/**
	 * 顧客情報を更新する。
	 *
	 * @param form 顧客編集フォーム
	 */

	public void updateCustomer(CustomerEditForm form) {
	    customerMapper.updateCustomer(form);
	}

	/**
	 * 顧客を停止する。
	 *
	 * @param id 顧客ID
	 */

	public void deactivateCustomer(int id) {
	    customerMapper.deactivateCustomer(id);
	}
}
