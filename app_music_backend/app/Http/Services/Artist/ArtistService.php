<?php


namespace App\Http\Services\Artist;

use GuzzleHttp\Client;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;
use Illuminate\Support\Facades\Mail;
use Illuminate\Support\Facades\Storage;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Str;


use App\Http\Utilities\ResponseUtil as responseUtil;

use App\Models\Artist;




class ArtistService 
{

    public function doAddArtist(Request $request)
	{

		DB::beginTransaction();

		$newArtist = new Artist;
		try {
			$newArtist->artistName = $request->input('artistName');
			$newArtist->save();
		} catch (Exception $e) {
			DB::rollback();
			return responseUtil::respondedError("common.error-messages.common-server-error");
		}

		DB::commit();

		return responseUtil::respondedSuccess("pages.add.artist.success", $newArtist);
	}

	public function doUpdateArtist(Request $request)
	{

		DB::beginTransaction();
		;
		// Log::info('data'.$request->input('ArtistId'));
		try {
			$conditions = array(
				['artistId', '=', $request->input('artistId')],
			);
			$existsArtist = DB::table('artist')->where($conditions)->first();
			if(!$existsArtist){
				return responseUtil::respondedBadRequest("artist does not have");


			}else{
				
				$conditions = array(
					['artistId', '=', $existsArtist->artistId],
				);
				Artist::where($conditions)
						->update([
							'artistName' => $request->input('artistName')		
						]);
			}

		} catch (Exception $e) {
			DB::rollback();
			return responseUtil::respondedError("common.error-messages.common-server-error");
		}

		DB::commit();

		return responseUtil::respondedSuccess("pages.update.Artist.success");
	}

	public function doDeleteArtist(Request $request){
		$conditions = array(
			['artistId', '=', $request->input('artistId')]
		);
		$itemDelete = Artist::where($conditions)->first();

		if ($itemDelete == null) {
			return responseUtil::respondedBadRequest("Artist does not have");
		}
		DB::beginTransaction();

		try {

			$deletedRows = Artist::where($conditions)->delete();

			if ($deletedRows > 0) {
				DB::commit();
				return responseUtil::respondedSuccess("delete.Artist.success");
			} else {
				DB::rollback();
				return responseUtil::respondedBadRequest("cannot.delete.Artist.success");
			}
		} catch (Exception $e) {
			DB::rollback();

			$currentDate = date('Y/m/d H:i:s');
			Log::error(env("APP_NAME") . " | " . $currentDate . " - Error: " . $e);

			return responseUtil::respondedError(Lang::get('messages.common_error_exception'), $e);
		}
	}

	public function getArtistList(){
        $ArtistList = DB::table('artist')->get();
        $respondedResult = [
			"artistList" => $ArtistList
		];
        return responseUtil::respondedSuccess("common.success-message.get-data-success",$respondedResult);

    }



}
