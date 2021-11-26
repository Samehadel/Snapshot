import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import UserService from '../services/UserService';
import '../styles/HeaderStyle.css';
import image from '../assets/upload.png';

const Header = (props) => {
    const showPost = props.showPost;
    const showSignup = props.showSignup;
    const showLogin = props.showLogin;
    const showLogout = props.showLogout;
    const navigate = useNavigate();

    return (
        <header className="parent">
            <div className='header-container'>
                <Link className='logo' to='/home'>Snapshot</Link>
                <div>
                    {showPost && <Link to='/home/upload' ><img className='post-image' src={image} /></Link>}
                </div>
                {showSignup && <button onClick={() => navigate('/signup')}>Sign Up</button>}
                {showLogin && <button onClick={() => navigate('/login')}>Login</button>}
                {showLogout && <button onClick={() => {
                    UserService.logout();
                    navigate('/login');
                }}>Logout</button>}
            </div>
        </header>
    );
}

export default Header;