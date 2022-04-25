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
