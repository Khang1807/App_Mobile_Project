<?php

namespace App\Http\Controllers\HistoryOfUser;

// use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

use Illuminate\Support\Facades\Auth;
use Illuminate\Foundation\Bus\DispatchesJobs;
use App\Http\Utilities\ResponseUtil as responseUtil;
use Illuminate\Routing\Controller as BaseController;

use Illuminate\Foundation\Validation\ValidatesRequests;
use App\Http\Utilities\ValidationUtil as validationUtil;

use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use App\Http\Services\HistoryOfUser\HistoryService as HistoryService;

class HistoryOfUserController extends BaseController
{
    private $HistoryService;

	public function __construct(
		HistoryService $HistoryService
	) {
		$this->HistoryService = $HistoryService;
	}
	protected function doAddHistory(Request $request){
        return $this->HistoryService->doAddHistory($request);
	}
	protected function doDeleteHistoryUser(Request $request){
        return $this->HistoryService->doDeleteHistoryUser($request);
	}
    protected function getHistoryUserList(Request $request){
        return $this->HistoryService->getHistoryUserList($request);
	}
}
