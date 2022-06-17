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
use DateTime;

use Carbon\Carbon;

use App\Http\Utilities\ResponseUtil as responseUtil;

use App\Models\HistoryOfUser;




class HistoryService 
{
    public function doAddHistory(Request $request)
	{

		$conditions1 = array(
			['userId', '=', $request->input('userId')],
		);
		$conditions2 = array(
			['musicId', '=', $request->input('musicId')]
		);
		
		$itemDelete = HistoryOfUser::where($conditions1)->where($conditions2)->first();
		

		if ($itemDelete == null) {
			
			DB::beginTransaction();
			

			$newHistory = new HistoryOfUser;
			try {
				$newHistory->userId = $request->input('userId');
				$newHistory->musicId = $request->input('musicId');
				$date = $request->input('time');
				$newHistory->time=DateTime::createFromFormat('Y-m-d H:i:s', $date)->format('Y-m-d H:i:s'); 

				$newHistory->save();
			} catch (Exception $e) {
				DB::rollback();
				return responseUtil::respondedError("common.error-messages.common-server-error");
			}

			DB::commit();
			return responseUtil::respondedSuccess("pages.add.category.success", $newHistory);
		}else{
			DB::beginTransaction();

			try {
	
				$deletedRows = HistoryOfUser::where($conditions1)->where($conditions2)->delete();
	
				if ($deletedRows > 0) {
					DB::commit();
					
					$newHistory = new HistoryOfUser;
					try {
						$newHistory->userId = $request->input('userId');
						$newHistory->musicId = $request->input('musicId');
						$date = $request->input('time');
						$newHistory->time=DateTime::createFromFormat('Y-m-d H:i:s', $date)->format('Y-m-d H:i:s'); 
		
						$newHistory->save();
					} catch (Exception $e) {
						DB::rollback();
						return responseUtil::respondedError("common.error-messages.common-server-error");
					}
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

		$userId = $request->input('userId');		
        $historyUserList = DB::table('history_of_user')
						->join('music','history_of_user.musicId','=','music.musicId')
						// ->join('artist','music.artistId','=','artist.artistId')
						->where('history_of_user.userId','=',$userId)	
						->orderBy('history_of_user.time', 'DESC')					
						->get();
     
		if (count($historyUserList) <= 0) {
			return responseUtil::respondedBadRequest("history of user does not have");
		}
		
        $respondedResult = [
			"historyUserList" => $historyUserList
		];
        return responseUtil::respondedSuccess("common.success-message.get-data-success", $respondedResult);
    }



}
