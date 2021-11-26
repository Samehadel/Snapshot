import React, { Component } from "react";
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import AuthenticatedRoute from './AuthenticatedRoute';
import SignupPage from './Signup';
import Login from './Login';
import PageNotFound from './PageNotFound';
import HomePage from './Home';
import ImageDisplayPage from './ImageDisplayPage';
import UploadImagePage from './UploadImagePage';

const WebsiteRouter = (props) => {

    return (
        <Router>
            <Routes>
                <Route path='/home/image/:id' element={<ImageDisplayPage />} />
                <Route path='/home' element={<HomePage />} />
                <Route path='/home/upload'
                    element={
                        <AuthenticatedRoute>
                            <UploadImagePage />
                        </AuthenticatedRoute>
                    } />
                <Route path='/login' element={<Login />} />
                <Route path='/signup' element={<SignupPage />} />
                <Route path='*' element={<PageNotFound />} />
            </Routes>
        </Router>
    );
}
export default WebsiteRouter;