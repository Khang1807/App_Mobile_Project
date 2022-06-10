<?php

namespace App\Http\Controllers\Artist;

// use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

use Illuminate\Support\Facades\Auth;
use Illuminate\Foundation\Bus\DispatchesJobs;
use App\Http\Utilities\ResponseUtil as responseUtil;
use Illuminate\Routing\Controller as BaseController;

use Illuminate\Foundation\Validation\ValidatesRequests;
use App\Http\Utilities\ValidationUtil as validationUtil;

use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use App\Http\Services\Artist\ArtistService as ArtistService;

class ArtistController extends BaseController
{
    private $artistService;

	public function __construct(
		ArtistService $artistService
	) {
		$this->artistService = $artistService;
	}
	protected function doAddArtist(Request $request){
        return $this->artistService->doAddArtist($request);
	}
	protected function doUpdateArtist(Request $request){
        return $this->artistService->doUpdateArtist($request);
	}
	protected function doDeleteArtist(Request $request){
        return $this->artistService->doDeleteArtist($request);
	}
    protected function getArtistList(){
        return $this->artistService->getArtistList();
	}
	protected function getArtistInfo(Request $request){
        return $this->artistService->getArtistInfo($request);
	}
}
