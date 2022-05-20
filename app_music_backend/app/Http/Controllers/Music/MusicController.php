<?php

namespace App\Http\Controllers\Music;

// use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

use Illuminate\Support\Facades\Auth;
use Illuminate\Foundation\Bus\DispatchesJobs;
use App\Http\Utilities\ResponseUtil as responseUtil;
use Illuminate\Routing\Controller as BaseController;

use Illuminate\Foundation\Validation\ValidatesRequests;
use App\Http\Utilities\ValidationUtil as validationUtil;

use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use App\Http\Services\Music\MusicService as MusicService;

class MusicController extends BaseController
{
    private $musicService;

	public function __construct(
		MusicService $musicService
	) {
		$this->musicService = $musicService;
	}
	protected function doAddMusic(Request $request){
        return $this->musicService->doAddMusic($request);
	}
	protected function doUpdateMusic(Request $request){
        return $this->musicService->doUpdateMusic($request);
	}
	protected function doDeleteMusic(Request $request){
        return $this->musicService->doDeleteMusic($request);
	}
    protected function getMusicList(){
        return $this->musicService->getMusicList();
	}
}
