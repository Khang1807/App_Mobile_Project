<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::group([
    'middleware' => 'api',
    'prefix' => 'auth'

], function ($router) {
    
    Route::post('/login', [App\Http\Controllers\User\UserController::class, 'doLogin']);
    Route::post('/register', [App\Http\Controllers\User\UserController::class, 'doRegister']);

    // Route::post('/register/candidate', [App\Http\Controllers\Account\AccountAuthenticationController::class, 'doRegisterCandidate']);
    // Route::post('/register/company', [App\Http\Controllers\Account\AccountAuthenticationController::class, 'doRegisterCompany']);
    // Route::post('forgot-password',[App\Http\Controllers\Account\AccountAuthenticationController::class, 'doForgotPassword']);
    // Route::post('reset-password',[App\Http\Controllers\Account\AccountAuthenticationController::class, 'doResetPassword']);
       
});
Route::group([
	'middleware' => []
], function () {
	Route::get('music/get-music', [App\Http\Controllers\Music\MusicController::class, 'getMusicList']);
    
    Route::get('music/get-music-top10', [App\Http\Controllers\Music\MusicController::class, 'getTop10Music']);
    Route::get('playlist/get-top10-playlist', [App\Http\Controllers\Playlist\PlaylistController::class, 'getTop10Playlist']);
    Route::get('playlist/get-playlist', [App\Http\Controllers\Playlist\PlaylistController::class, 'getPlaylist']);
    Route::get('playlist/get-playlist-of-user', [App\Http\Controllers\PlaylistOfUser\PlaylistUserController::class, 'getPlaylistOfUser']);
    Route::get('category/get-category', [App\Http\Controllers\Category\CategoryController::class, 'getCategoryList']);
    
    Route::get('history/get-history-of-user', [App\Http\Controllers\HistoryOfUser\HistoryOfUserController::class, 'getHistoryUserList']);
    Route::get('artist/get-artist', [App\Http\Controllers\Artist\ArtistController::class, 'getArtistList']);


    Route::post('music/find-music', [App\Http\Controllers\Music\MusicController::class, 'findMusic']);
    Route::post('user/get-user', [App\Http\Controllers\User\UserController::class, 'getUserInfo']);
    Route::post('user/add-img', [App\Http\Controllers\User\UserController::class, 'doAddImg']);
    Route::post('music/add-music', [App\Http\Controllers\Music\MusicController::class, 'doAddMusic']);
    Route::post('playlist/add-playlist', [App\Http\Controllers\Playlist\PlaylistController::class, 'doAddPlaylist']);
    Route::post('playlistofuser/get-playlist-user', [App\Http\Controllers\PlaylistOfUser\PlaylistUserController::class, 'getPlaylistUser']);
    Route::post('playlistofuser/add-playlist-of-user', [App\Http\Controllers\PlaylistOfUser\PlaylistUserController::class, 'doAddPlaylistUser']);
    Route::post('category/add-category', [App\Http\Controllers\Category\CategoryController::class, 'doAddCategory']);
    Route::post('category/get-category-info', [App\Http\Controllers\Category\CategoryController::class, 'getCategoryInfo']);
    Route::post('artist/get-artist-info', [App\Http\Controllers\Artist\ArtistController::class, 'getArtistInfo']);
    Route::post('playlist/get-playlist-info', [App\Http\Controllers\Playlist\PlaylistController::class, 'getPlaylistInfo']);
    Route::post('history/add-history-of-user', [App\Http\Controllers\HistoryOfUser\HistoryOfUserController::class, 'doAddHistory']);
    
    Route::post('category/update-category', [App\Http\Controllers\Category\CategoryController::class, 'doUpdateCategory']);
    Route::post('playlist/update-playlist', [App\Http\Controllers\Playlist\PlaylistController::class, 'doUpdatePlaylist']);
    Route::post('music/update-music', [App\Http\Controllers\Music\MusicController::class, 'doUpdateMusic']);


    Route::delete('category/delete-category', [App\Http\Controllers\Category\CategoryController::class, 'doDeleteCategory']);
    Route::delete('playlist/delete-playlist', [App\Http\Controllers\Playlist\PlaylistController::class, 'doDeletePlaylist']);
    Route::delete('music/delete-music', [App\Http\Controllers\Music\MusicController::class, 'doDeleteMusic']);
    Route::delete('playlist/delete-playlist-of-user', [App\Http\Controllers\PlaylistOfUser\PlaylistUserController::class, 'doDeletePlaylistUser']);
    Route::delete('history/delete-history-user', [App\Http\Controllers\HistoryOfUser\HistoryOfUserController::class, 'doDeleteHistoryUser']);
    
});
