import React, { Component } from 'react'
import UserService from '../services/UserService.js';
import { Navigate } from 'react-router-dom';

function AuthenticatedRoute({children}) {
    const isLoggedIn = UserService.isUserLoggedIn();
    
    return isLoggedIn ? children : <Navigate to='/login'/>
}
export default AuthenticatedRoute;