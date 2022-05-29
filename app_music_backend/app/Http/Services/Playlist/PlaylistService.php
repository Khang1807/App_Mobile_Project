<?php


namespace App\Http\Services\Playlist;

use GuzzleHttp\Client;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;
use Illuminate\Support\Facades\Mail;
use Illuminate\Support\Facades\Storage;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Str;


use App\Http\Utilities\ResponseUtil as responseUtil;

use App\Models\Playlist;




class PlaylistService 
{

    public function doAddPlaylist(Request $request)
	{

		DB::beginTransaction();

		$newPlaylist = new Playlist;
		try {
			$newPlaylist->score = $request->input('score');
            $newPlaylist->nameOfPlaylist = $request->input('nameOfPlaylist');
			$newPlaylist->save();
		} catch (Exception $e) {
			DB::rollback();
			return responseUtil::respondedError("common.error-messages.common-server-error");
		}

		DB::commit();

		return responseUtil::respondedSuccess("pages.add.category.success", $newPlaylist);
	}

	public function doUpdatePlaylist(Request $request)
	{

		DB::beginTransaction();
		
		// Log::info('data'.$request->input('categoryId'));
		try {
			$conditions = array(
				['playlistId', '=', $request->input('playlistId')],
			);
			$existsPlaylist = DB::table('playlist')->where($conditions)->first();
			if(!$existsPlaylist){
				return responseUtil::respondedBadRequest("playlist does not have");


			}else{
				
				$conditions = array(
					['playlistId', '=', $existsPlaylist->playlistId],
				);
				Playlist::where($conditions)
						->update([
							'nameOfPlaylist' => $request->input('nameOfPlaylist'),
							'score' => $request->input('score')		
						]);
			}

		} catch (Exception $e) {
			DB::rollback();
			return responseUtil::respondedError("common.error-messages.common-server-error");
		}

		DB::commit();

		return responseUtil::respondedSuccess("pages.update.palylist.success");
	}

	public function doDeletePlaylist(Request $request){
		$conditions = array(
			['playlistId', '=', $request->input('playlistId')],
		);
		$itemDelete = Playlist::where($conditions)->first();

		if ($itemDelete == null) {
			return responseUtil::respondedBadRequest("playlist does not have");
		}
		DB::beginTransaction();

		try {

			$deletedRows = Playlist::where($conditions)->delete();

			if ($deletedRows > 0) {
				DB::commit();
				return responseUtil::respondedSuccess("delete.playlist.success");
			} else {
				DB::rollback();
				return responseUtil::respondedBadRequest("cannot.delete.playlist.success");
			}
		} catch (Exception $e) {
			DB::rollback();

			$currentDate = date('Y/m/d H:i:s');
			Log::error(env("APP_NAME") . " | " . $currentDate . " - Error: " . $e);

			return responseUtil::respondedError(Lang::get('messages.common_error_exception'), $e);
		}
	}

	public function getPlaylist(){
        $playlist = DB::table('playlist')->get();
        $respondedResult = [
			"playlist" => $playlist
		];
        return responseUtil::respondedSuccess("common.success-message.get-data-success", $respondedResult);
    }



}
