<?php

namespace App\Http\Controllers\Category;

// use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

use Illuminate\Support\Facades\Auth;
use Illuminate\Foundation\Bus\DispatchesJobs;
use App\Http\Utilities\ResponseUtil as responseUtil;
use Illuminate\Routing\Controller as BaseController;

use Illuminate\Foundation\Validation\ValidatesRequests;
use App\Http\Utilities\ValidationUtil as validationUtil;

use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use App\Http\Services\Category\CategoryService as CategoryService;

class CategoryController extends BaseController
{
    private $categoryService;

	public function __construct(
		CategoryService $categoryService
	) {
		$this->categoryService = $categoryService;
	}
	protected function doAddCategory(Request $request){
        return $this->categoryService->doAddCategory($request);
	}
	protected function doUpdateCategory(Request $request){
        return $this->categoryService->doUpdateCategory($request);
	}
	protected function doDeleteCategory(Request $request){
        return $this->categoryService->doDeleteCategory($request);
	}
    protected function getCategoryList(){
        return $this->categoryService->getCategoryList();
	}
}
