<?php

namespace App\Http\Controllers\Playlist;

// use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

use Illuminate\Support\Facades\Auth;
use Illuminate\Foundation\Bus\DispatchesJobs;
use App\Http\Utilities\ResponseUtil as responseUtil;
use Illuminate\Routing\Controller as BaseController;

use Illuminate\Foundation\Validation\ValidatesRequests;
use App\Http\Utilities\ValidationUtil as validationUtil;

use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use App\Http\Services\Playlist\PlaylistService as PlaylistService;

class PlaylistController extends BaseController
{
    private $playlistService;

	public function __construct(
		PlaylistService $playlistService
	) {
		$this->playlistService = $playlistService;
	}
	protected function doAddPlaylist(Request $request){
        return $this->playlistService->doAddPlaylist($request);
	}
	protected function doUpdatePlaylist(Request $request){
        return $this->playlistService->doUpdatePlaylist($request);
	}
	protected function doDeletePlaylist(Request $request){
        return $this->playlistService->doDeletePlaylist($request);
	}
    protected function getPlaylist(){
        return $this->playlistService->getPlaylist();
	}
	protected function getPlaylistInfo(Request $request){
        return $this->playlistService->getPlaylistInfo($request);
	}
}
