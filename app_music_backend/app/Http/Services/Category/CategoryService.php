<?php


namespace App\Http\Services\Category;

use GuzzleHttp\Client;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;
use Illuminate\Support\Facades\Mail;
use Illuminate\Support\Facades\Storage;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Str;


use App\Http\Utilities\ResponseUtil as responseUtil;

use App\Models\Category;
use App\Models\Music;




class CategoryService 
{

    public function doAddCategory(Request $request)
	{

		DB::beginTransaction();

		$newCategory = new Category;
		try {
			$newCategory->categoryName = $request->input('categoryName');
			$newCategory->save();
		} catch (Exception $e) {
			DB::rollback();
			return responseUtil::respondedError("common.error-messages.common-server-error");
		}

		DB::commit();

		return responseUtil::respondedSuccess("pages.add.category.success", $newCategory);
	}

	public function doUpdateCategory(Request $request)
	{

		DB::beginTransaction();
		;
		// Log::info('data'.$request->input('categoryId'));
		try {
			$conditions = array(
				['categoryId', '=', $request->input('categoryId')],
			);
			$existsCategory = DB::table('category')->where($conditions)->first();
			if(!$existsCategory){
				return responseUtil::respondedBadRequest("category does not have");


			}else{
				
				$conditions = array(
					['categoryId', '=', $existsCategory->categoryId],
				);
				Category::where($conditions)
						->update([
							'categoryName' => $request->input('categoryName')		
						]);
			}

		} catch (Exception $e) {
			DB::rollback();
			return responseUtil::respondedError("common.error-messages.common-server-error");
		}

		DB::commit();

		return responseUtil::respondedSuccess("pages.update.category.success");
	}

	public function doDeleteCategory(Request $request){
		$conditions = array(
			['categoryId', '=', $request->input('categoryId')]
		);
		$itemDelete = Category::where($conditions)->first();

		if ($itemDelete == null) {
			return responseUtil::respondedBadRequest("category does not have");
		}
		DB::beginTransaction();

		try {

			$deletedRows = Category::where($conditions)->delete();

			if ($deletedRows > 0) {
				DB::commit();
				return responseUtil::respondedSuccess("delete.category.success");
			} else {
				DB::rollback();
				return responseUtil::respondedBadRequest("cannot.delete.category.success");
			}
		} catch (Exception $e) {
			DB::rollback();

			$currentDate = date('Y/m/d H:i:s');
			Log::error(env("APP_NAME") . " | " . $currentDate . " - Error: " . $e);

			return responseUtil::respondedError(Lang::get('messages.common_error_exception'), $e);
		}
	}

	public function getCategoryList(){
        $categoryList = DB::table('category')->get();
        $respondedResult = [
			"categoryList" => $categoryList
		];
        return responseUtil::respondedSuccess("common.success-message.get-data-success",$respondedResult);

    }
	public function getCategoryInfo(Request $request){
		$categoryId = $request->input('categoryId');		
        $categoryInfo = DB::table('music')
						->select('music.*')
						->where('music.categoryId','=',$categoryId)						
						->get();
        $respondedResult = [
			"categoryInfo" => $categoryInfo
		];
        return responseUtil::respondedSuccess("common.success-message.get-data-success", $respondedResult);
    }


}
