package com.example.springtest.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springtest.dto.CityOption;
import com.example.springtest.form.CustomerCreateForm;
import com.example.springtest.mapper.CustomerMapper;
import com.example.springtest.param.AddressInsertParam;
import com.example.springtest.param.CustomerInsertParam;

/**
 * 顧客登録に関する業務ロジックを扱うサービスクラス。
 *
 * 顧客登録画面で使用する都市一覧の取得や、
 * 住所登録・顧客登録をまとめた新規登録処理を担当する。
 */

@Service
public class CustomerService {

	private final CustomerMapper customerMapper;

	/**
	 * 顧客用Mapperを受け取ってサービスを初期化する。
	 *
	 * @param customerMapper 顧客情報のDBアクセスを担当するMapper
	 */

	public CustomerService(CustomerMapper customerMapper) {
		this.customerMapper = customerMapper;
	}

	/**
	 * 顧客登録画面で使用する都市一覧を取得する。
	 *
	 * @return 都市一覧
	 */

	public List<CityOption> findAllCities() {
		return customerMapper.findAllCities();
	}

	/**
	 * 顧客を新規登録する。
	 *
	 * まず住所情報を登録し、その後に取得した住所IDを利用して
	 * 顧客情報を登録する。
	 *
	 * @param form 顧客登録フォーム
	 * @return 登録された顧客ID
	 */

	@Transactional
	public int createCustomer(CustomerCreateForm form) {

		AddressInsertParam address = new AddressInsertParam();
		address.setAddress(form.getAddress());
		address.setDistrict(form.getDistrict());
		address.setCityId(form.getCityId());
		address.setPostalCode(form.getPostalCode());
		address.setPhone(form.getPhone());

		customerMapper.createAddress(address);

		CustomerInsertParam customer = new CustomerInsertParam();
		customer.setStoreId(form.getStoreId());
		customer.setFirstName(form.getFirstName());
		customer.setLastName(form.getLastName());
		customer.setEmail(form.getEmail());
		customer.setAddressId(address.getAddressId());
		customer.setActive(form.getActive());

		customerMapper.createCustomer(customer);

		return customer.getCustomerId();
	}
}
