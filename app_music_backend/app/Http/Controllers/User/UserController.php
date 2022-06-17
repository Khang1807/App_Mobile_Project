<?php

namespace App\Http\Controllers\User;

// use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

use Illuminate\Support\Facades\Auth;
use Illuminate\Foundation\Bus\DispatchesJobs;
use App\Http\Utilities\ResponseUtil as responseUtil;
use Illuminate\Routing\Controller as BaseController;

use Illuminate\Foundation\Validation\ValidatesRequests;
use App\Http\Utilities\ValidationUtil as validationUtil;

use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use App\Http\Services\User\UserService as UserService;

class UserController extends BaseController
{
//    private $commonCtl;
	use AuthorizesRequests, DispatchesJobs, ValidatesRequests;

	private $userService;

	// private $rulesMess = [
	// 	'email.required' => 'common.validation.email-required',
	// 	'email.email' => 'common.validation.email-format',
	// 	'email.between' => 'common.validation.email-between',
	// 	'password.required' => 'common.validation.password-required',
	// 	'password.between' => 'common.validation.password-between',

	// 	'pinCode.required' => 'common.validation.pinCode-required',
	// 	'pinCode.between' => 'common.validation.pinCode-between'
	// ];

	public function __construct(
		UserService $userService
	) {
		$this->userService = $userService;
	}

	protected function doRegister(Request $request){
		$rules = [
			'email' => 'required|email|between:3,150',
			'password' => 'required|between:3,100',
			'userName' => 'required|between:3,100'
		];
		return $this->userService->doRegister($request);
	}

	protected function doLogin(Request $request){

		$rules = [
			'email' => 'required|email|between:3,150',
			'password' => 'required|between:3,100'
		];
		// $inValidRequestData = validationUtil::checkValidRequest($request, $rules, $this->rulesMess);
		// if ($inValidRequestData->fails()) {
		// 	return responseUtil::respondedBadRequest($inValidRequestData->errors()->first(), $inValidRequestData->errors());
		// }
		return $this->userService->doLogin($request);
	}
	
	protected function getUserInfo(Request $request){
			return $this->userService->getUserInfo($request);
	}

	protected function doAddImg(Request $request){
		$rules = [
			'email' => 'required'
		];
		$inValidRequestData = validationUtil::checkValidRequest($request, $rules, $this->rulesMess);
		if ($inValidRequestData->fails()) {
			return responseUtil::respondedBadRequest($inValidRequestData->errors()->first(), $inValidRequestData->errors());
		}
		return $this->userService->doAddImg($request);
		
	}
	// protected function doForgotPassword(Request $request){
	// 	$rules = [
	// 		'email' => 'required|email|between:3,150',
	// 	];
	// 	$inValidRequestData = validationUtil::checkValidRequest($request, $rules, $this->rulesMess);
	// 	if ($inValidRequestData->fails()) {
	// 		return responseUtil::respondedBadRequest($inValidRequestData->errors()->first(), $inValidRequestData->errors());
	// 	}

	// 	return $this->userService->doForgotPassword($request);
	// }
	// protected function doResetPassword(Request $request){
	// 	$rules = [
	// 		'email' => 'required|email|between:3,150',
	// 		'newPassword' => 'required|between:3,100',
	// 		'token_resetPassword' => 'required|between:10,10'
	// 	];
	// 	$inValidRequestData = validationUtil::checkValidRequest($request, $rules, $this->rulesMess);
	// 	if ($inValidRequestData->fails()) {
	// 		return responseUtil::respondedBadRequest($inValidRequestData->errors()->first(), $inValidRequestData->errors());
	// 	}

	// 	return $this->userService->doResetPassword($request);
	// }
}
