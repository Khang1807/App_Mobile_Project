<?php


namespace App\Http\Services\PlaylistOfUser;

use GuzzleHttp\Client;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;
use Illuminate\Support\Facades\Mail;
use Illuminate\Support\Facades\Storage;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Str;


use App\Http\Utilities\ResponseUtil as responseUtil;

use App\Models\PlaylistOfUser;




class PlaylistUserService 
{

	public function doAddPlaylistUser(Request $request)
	{

		DB::beginTransaction();

		$newPlaylistUser = new PlaylistOfUser;
		try {
			$newPlaylistUser->userId = $request->input('userId');
            $newPlaylistUser->musicId = $request->input('musicId');
			$newPlaylistUser->save();
		} catch (Exception $e) {
			DB::rollback();
			return responseUtil::respondedError("common.error-messages.common-server-error");
		}

		DB::commit();

		return responseUtil::respondedSuccess("pages.add.category.success", $newPlaylistUser);
	}

	public function getPlaylistOfUser(Request $request){
        
        $playlistOfUser = DB::table('playlist_of_user')->get();
        $respondedResult = [
			"playlistOfUser" => $playlistOfUser
		];
        return responseUtil::respondedSuccess("common.success-message.get-data-success", $respondedResult);
    }

	public function doDeletePlaylistUser(Request $request){
		$conditions = array(
			['userId', '=', $request->input('userId')],
			['musicId', '=', $request->input('musicId')]
		);
		$itemDelete = PlaylistOfUser::where($conditions)->first();

		if ($itemDelete == null) {
			return responseUtil::respondedBadRequest("playlist of user does not have");
		}
		DB::beginTransaction();

		try {

			$deletedRows = PlaylistOfUser::where($conditions)->delete();

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


}
