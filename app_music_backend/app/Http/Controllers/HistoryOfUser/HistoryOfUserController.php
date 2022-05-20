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
use App\Http\Services\HistoryOfUser\HistoryUserService as HistoryUserService;

class HistoryUserController extends BaseController
{
    private $historyUserService;

	public function __construct(
		HistoryUserService $historyUserService
	) {
		$this->historyUserService = $historyUserService;
	}
	protected function doAddHistory(Request $request){
        return $this->historyUserService->doAddHistory($request);
	}
	protected function doDeleteHistoryUser(Request $request){
        return $this->historyUserService->doDeleteHistoryUser($request);
	}
    protected function getHistoryUserList(){
        return $this->historyUserService->getHistoryUserList();
	}
}
