<?php


namespace App\Http\Services\User;

use GuzzleHttp\Client;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;
use Illuminate\Support\Facades\Mail;
use Illuminate\Support\Facades\Storage;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Str;

// use App\Http\Services\Common\AppCommonService;

// use App\Http\Utilities\CarbonDateTimeUtil as dateTimeUtil;
use App\Http\Utilities\ResponseUtil as responseUtil;
// use App\Http\Utilities\LogsUtil as logsUtil;
// use App\Http\Utilities\MailUtil as mailUtil;

use App\Models\User;
//  use App\Http\Models\CandidatesProfile;
//  use App\Http\Models\CompaniesProfile;
//  use App\Http\Models\UserToken;

// use App\Http\Models\Dtos\Responses\CandidateLoginDto;
// use App\Http\Models\Dtos\Responses\CompanyLoginDto;



class UserService 
{

	public function doRegister(Request $request)
	{

		$conditions = array(
			['email', '=', $request->input('email')]
		);
		$existsUser = DB::table('Users')->where($conditions)->first();
		if ($existsUser) {
			return responseUtil::respondedBadRequest("pages.register.warning-messages.email-exist");
		}

		DB::beginTransaction();

		$newUser = new User;
		try {
			$newUser->email = $request->input('email');
			$newUser->password = Hash::make($request->input('password'));
			$newUser->name = $request->input('name');
			$newUser->save();
		} catch (Exception $e) {
			DB::rollback();
			return responseUtil::respondedError("common.error-messages.common-server-error");
		}

		DB::commit();

		return responseUtil::respondedSuccess("pages.register.registration-success", $newUser);
	}
	//login User
	public function doLogin(Request $request){
 
		$array = ['email'=>$request->input('email'),
					'password'=>$request->input('password')];
					error_log($request->input('email'));
        if (auth()->attempt($array)) {
            $User=auth()->user();
			
			$respondedResult = [
				"User" => $User,
			];
			return responseUtil::respondedSuccess("pages.login.login-success", $respondedResult);
        }else{
            return responseUtil::respondedNotFound("cannot find this User in database");
        }
     
    }

	// get infor User
	public function getUserInfo(Request $request){
		$email = $request->input('email');		
        $userInfo = DB::table('users')
						->select('users.*')
						->where('users.email','=',$email) 						
						->get();
        $respondedResult = [
			"userInfo" => $userInfo
		];
     
		return responseUtil::respondedSuccess("common.success-message.get-data-success", $respondedResult);
	}
	
	// //click forgot password and will send mail with token to reset password
	// public function doForgotPassword(Request $request)
	// {
	// 	DB::beginTransaction();

	// 	try {
	// 		$currentDate = dateTimeUtil::getNow();


	// 		$conditions = array(
	// 			['email', '=', $request->input('email')],
	// 		);
	// 		$existsUser = DB::table('Users')->where($conditions)->first();
	// 		if (!$existsUser) {
	// 			return responseUtil::respondedBadRequest("User not found");
	// 		}

	// 		$itemNewToken = new UserToken;

	// 		$resetPasswordToken = str::random(10);

	// 		$itemNewToken->User_id = $existsUser->User_id;
	// 		$itemNewToken->token = $resetPasswordToken;
	// 		$itemNewToken->token_expired_date = dateTimeUtil::getNextDateTime($currentDate, 30, "days");
	// 		$itemNewToken->created_date = $currentDate;
	// 		$itemNewToken->created_user = $existsUser->User_id;
	// 		$itemNewToken->save();

	// 		$mailData = [
	// 			"email" => $existsUser->email,
	// 			"candidateFirstName" => "Le",
	// 			"candidateLastName" => "Vinh",
	// 			"verifyToken" => $itemNewToken->token,
	// 		];
			
	// 		mailUtil::doSendMail('doCandidateForgotPassword', $existsUser->email, $mailData, $this->mailOptions);
	// 	} catch (Exception $e) {
	// 		DB::rollback();

	// 		logsUtil::storeLogs('error', $e);

	// 		return responseUtil::respondedError("common.error-messages.common-server-error");
	// 	}

	// 	DB::commit();

	// 	return responseUtil::respondedSuccess("pages.forgotten-password.forgot-password-successfully");
	// }

	// // function handle feature reset password if you have token
	// public function doResetPassword(Request $request)
	// {
	// 	DB::beginTransaction();

	// 	try {
	// 		$currentDate = dateTimeUtil::getNow();

	// 		$conditions = array(
	// 			['email', '=', $request->input('email')],
	// 		);
	// 		$existsUser = DB::table('Users')->where($conditions)->first();
			
	// 		if (!$existsUser) {
	// 			return responseUtil::respondedBadRequest("email does not have");
	// 		}

	// 		$conditions = array(
	// 			['User_id', '=', $existsUser->User_id],
	// 			['token', '=', $request->input('token_resetPassword')]
	// 			);
			
	// 		$existsToken = DB::table('Users_tokens')->where($conditions)->first();
	// 		if (!$existsToken) {
	// 			return responseUtil::respondedBadRequest("token or email does not have");
	// 		}	

	// 		$conditions = array(
	// 			['User_id', '=', $existsUser->User_id],
	// 		);
	// 		$newPasword = Hash::make($request->input('newPassword'));
	// 		User::where($conditions)
	// 				->update([
	// 					'password' => $newPasword,
						
	// 				]);

	// 	} catch (Exception $e) {
	// 		DB::rollback();

	// 		logsUtil::storeLogs('error', $e);

	// 		return responseUtil::respondedError("common.error-messages.common-server-error");
	// 	}

	// 	DB::commit();

	// 	return responseUtil::respondedSuccess("pages.reset-password.reset-password-success1");
	// }



}
