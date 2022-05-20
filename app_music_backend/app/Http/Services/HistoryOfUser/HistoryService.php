<?php


namespace App\Http\Services\HistoryOfUser;

use GuzzleHttp\Client;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;
use Illuminate\Support\Facades\Mail;
use Illuminate\Support\Facades\Storage;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Str;


use App\Http\Utilities\ResponseUtil as responseUtil;

use App\Models\HistoryOfUser;




class HistoryService 
{

    public function doAddHistory(Request $request)
	{

		DB::beginTransaction();

		$newHistory = new HistoryOfUser;
		try {
			$newHistory->userId = $request->input('userId');
            $newHistory->musicId = $request->input('musicId');
			$newHistory->save();
		} catch (Exception $e) {
			DB::rollback();
			return responseUtil::respondedError("common.error-messages.common-server-error");
		}

		DB::commit();

		return responseUtil::respondedSuccess("pages.add.category.success", $newHistory);
	}

	public function doDeleteHistoryUser(Request $request){
		$conditions = array(
			['userId', '=', $request->input('userId')],
			['musicId', '=', $request->input('musicId')]
		);
		$itemDelete = HistoryOfUser::where($conditions)->first();

		if ($itemDelete == null) {
			return responseUtil::respondedBadRequest("history of user does not have");
		}
		DB::beginTransaction();

		try {

			$deletedRows = HistoryOfUser::where($conditions)->delete();

			if ($deletedRows > 0) {
				DB::commit();
				return responseUtil::respondedSuccess("delete.success");
			} else {
				DB::rollback();
				return responseUtil::respondedBadRequest("cannot.delete");
			}
		} catch (Exception $e) {
			DB::rollback();

			$currentDate = date('Y/m/d H:i:s');
			Log::error(env("APP_NAME") . " | " . $currentDate . " - Error: " . $e);

			return responseUtil::respondedError(Lang::get('messages.common_error_exception'), $e);
		}
	}


	public function getHistoryUserList(Request $request){
        
        $historyUserList = DB::table('history_of_user')->get();
        $respondedResult = [
			"historyUserList" => $historyUserList
		];
        return responseUtil::respondedSuccess("common.success-message.get-data-success", $respondedResult);
    }



}
