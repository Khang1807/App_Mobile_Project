<?php
/**
 *  @author   Vinh Nguyen <vinh.nguyen2303@outlook.com>
 *  @mobile: 0975578276
 *  @skype: enjoyvinh
 *
 *  @ Copyright Skyfire Solution 2021. All Rights Reserved.
 **/

namespace App\Http\Utilities;

use Illuminate\Http\Request;

use Illuminate\Support\Facades\Validator;

class ValidationUtil
{

	//Check token session
	public static function checkTokenSession($request)
	{
		//Tạo biến lưu kết quả kiểm tra
		$result["auth"] = false;
		$result["message"] = "";

		if (strcmp($request->session()->token(), $request->header('X-CSRF-TOKEN')) !== 0) {  //Trường hợp không có _token
			//Không có quyền truy xuất API
			$result["auth"] = true;
			$result["message"] = Lang::get('messages.common_error_permission_api');
		}

		//Có quyền truy xuất API
		return $result;
	}

	public static function checkValidRequest(Request $request, $customRules, $rulesMess)
	{
		$rule = $customRules;

		if (empty($customRules)) {
			return null;
		}

		return Validator::make($request->all(), $rule, $rulesMess);
	}
}
