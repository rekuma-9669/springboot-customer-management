package com.example.springtest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.springtest.dto.CityOption;
import com.example.springtest.dto.CustomerDetail;
import com.example.springtest.dto.CustomerSummary;
import com.example.springtest.form.CustomerEditForm;
import com.example.springtest.param.AddressInsertParam;
import com.example.springtest.param.CustomerInsertParam;

/**
 * 顧客情報に関するデータベースアクセスを行うMyBatis Mapper。
 *
 * 顧客検索、詳細取得、登録、更新、停止に関するSQLを実行する。
 */

@Mapper
public interface CustomerMapper {

	/**
	 * 国一覧を取得する。
	 *
	 * @return 国一覧
	 */

    List<String> findAllCountries();

    /**
     * 都市一覧を取得する。
     *
     * @return 都市一覧
     */

    List<CityOption> findAllCities();

    /**
     * 条件をもとに顧客検索を行う。
     *
     * @param lastName 姓
     * @param firstName 名
     * @param email メールアドレス
     * @param active 登録状態
     * @param country 国名
     * @return 顧客一覧
     */

    List<CustomerSummary> searchCustomers(
    	    String lastName,
    	    String firstName,
    	    String email,
    	    Integer active,
    	    String country
    	);

    /**
     * 顧客IDをもとに顧客詳細を取得する。
     *
     * @param customerId 顧客ID
     * @return 顧客詳細
     */

    CustomerDetail findCustomerDetailById(int customerId);

    /**
     * 顧客情報を更新する。
     *
     * @param form 顧客編集フォーム
     */

    void updateCustomer(CustomerEditForm form);

    /**
     * 顧客を停止する。
     *
     * @param customerId 顧客ID
     */

    void deactivateCustomer(int customerId);

    /**
     * 住所情報を登録する。
     *
     * 登録後、生成された住所IDが引数オブジェクトに設定される。
     *
     * @param address 住所登録用パラメータ
     */

    void createAddress(AddressInsertParam address);

    /**
     * 顧客情報を登録する。
     *
     * 登録後、生成された顧客IDが引数オブジェクトに設定される。
     *
     * @param customer 顧客登録用パラメータ
     */

    void createCustomer(CustomerInsertParam customer);
}
