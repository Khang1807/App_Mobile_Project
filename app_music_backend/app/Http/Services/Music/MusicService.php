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
			$newMusic->artistId = $request->input('artistId');
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

	public function getTop10Music(){
        $musicTop10List = DB::table('music')->where('rating','>','90')->take(5)->get();
        $respondedResult = [
			"musicTop10List" => $musicTop10List
		];
        return responseUtil::respondedSuccess("common.success-message.get-data-success", $respondedResult);

    }

	public function findMusic(Request $request){
		// $request->input($name);
		$result = DB::table('music')->join('artist','music.artistId','=','artist.artistId')
					->where('musicName','LIKE','%'.$request->input('keyword').'%')->get();
		$respondedResult = ["resultFind"=>$result];
        if(count($result)){
         return responseUtil::respondedSuccess("pages.get.getAllAccount-success", $respondedResult);
        }
        else
        {
        return response()->json(['Result' => 'No Data not found'], 404);
      }
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

	public function UpdateLikedSong(Request $request){

	}


}
