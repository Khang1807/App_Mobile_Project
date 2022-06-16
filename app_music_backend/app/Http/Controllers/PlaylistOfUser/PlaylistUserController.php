<?php

namespace App\Http\Controllers\PlaylistOfUser;

// use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

use Illuminate\Support\Facades\Auth;
use Illuminate\Foundation\Bus\DispatchesJobs;
use App\Http\Utilities\ResponseUtil as responseUtil;
use Illuminate\Routing\Controller as BaseController;

use Illuminate\Foundation\Validation\ValidatesRequests;
use App\Http\Utilities\ValidationUtil as validationUtil;

use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use App\Http\Services\PlaylistOfUser\PlaylistUserService as PlaylistUserService;

class PlaylistUserController extends BaseController
{
    private $playlistUserService;

	public function __construct(
		PlaylistUserService $playlistUserService
	) {
		$this->playlistUserService = $playlistUserService;
	}
	protected function doAddPlaylistUser(Request $request){
        return $this->playlistUserService->doAddPlaylistUser($request);
	}
	protected function doDeletePlaylistUser(Request $request){
        return $this->playlistUserService->doDeletePlaylistUser($request);
	}
    protected function getPlaylistOfUser(){
        return $this->playlistUserService->getPlaylistOfUser();
	}
	protected function getPlaylistUser(Request $request){
        return $this->playlistUserService->getPlaylistOfUser($request);
	}
}
