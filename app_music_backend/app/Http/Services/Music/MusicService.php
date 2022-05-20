<?php


namespace App\Http\Services\Music;

use GuzzleHttp\Client;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;
use Illuminate\Support\Facades\Mail;
use Illuminate\Support\Facades\Storage;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Str;


use App\Http\Utilities\ResponseUtil as responseUtil;

use App\Models\Music;




class MusicService 
{

    public function doAddMusic(Request $request)
	{

		DB::beginTransaction();

		$newMusic = new Music;
		try {
			$newMusic->musicName = $request->input('musicName');
            $newMusic->rating = $request->input('rating');
			$newMusic->playlistId = $request->input('playlistId');
			$newMusic->categoryId = $request->input('categoryId');
			$newMusic->save();
		} catch (Exception $e) {
			DB::rollback();
			return responseUtil::respondedError("common.error-messages.common-server-error");
		}

		DB::commit();

		return responseUtil::respondedSuccess("pages.add.category.success", $newMusic);
	}

	

	public function getMusicList(){
        $musicList = DB::table('music')->get();
        $respondedResult = [
			"musicList" => $musicList
		];
        return responseUtil::respondedSuccess("common.success-message.get-data-success", $respondedResult);

    }

	public function doUpdateMusic(Request $request)
	{

		DB::beginTransaction();
		
		// Log::info('data'.$request->input('categoryId'));
		try {
			$conditions = array(
				['musicId', '=', $request->input('musicId')],
			);
			$existsMusic = DB::table('music')->where($conditions)->first();
			if(!$existsMusic){
				return responseUtil::respondedBadRequest("music does not have");


			}else{
				
				$conditions = array(
					['musicId', '=', $existsMusic->musicId],
				);
				Music::where($conditions)
						->update([
							'musicName' => $request->input('musicName'),
							'playlistId' => $request->input('playlistId'),
							'categoryId' => $request->input('categoryId'),
							'rating' => $request->input('rating')		
						]);
			}

		} catch (Exception $e) {
			DB::rollback();
			return responseUtil::respondedError("common.error-messages.common-server-error");
		}

		DB::commit();

		return responseUtil::respondedSuccess("pages.update.music.success");
	}

	public function doDeleteMusic(Request $request){
		$conditions = array(
			['musicId', '=', $request->input('musicId')],
		);
		$itemDelete = Music::where($conditions)->first();

		if ($itemDelete == null) {
			return responseUtil::respondedBadRequest("music does not have");
		}
		DB::beginTransaction();

		try {

			$deletedRows = Music::where($conditions)->delete();

			if ($deletedRows > 0) {
				DB::commit();
				return responseUtil::respondedSuccess("delete.music.success");
			} else {
				DB::rollback();
				return responseUtil::respondedBadRequest("cannot.delete.music.success");
			}
		} catch (Exception $e) {
			DB::rollback();

			$currentDate = date('Y/m/d H:i:s');
			Log::error(env("APP_NAME") . " | " . $currentDate . " - Error: " . $e);

			return responseUtil::respondedError(Lang::get('messages.common_error_exception'), $e);
		}
	}


}
