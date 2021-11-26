import React, { Component } from "react";
import { useNavigate, Navigate } from "react-router-dom";
import UserService from '../services/UserService.js';
import Header from './HeaderComponent';
import '../styles/SignupStyle.css';

function LoginPage() {
    const navigate = useNavigate();
    const isLoggedIn = UserService.isUserLoggedIn();

    console.log(isLoggedIn);
    return (
        <>
            {!isLoggedIn && <Login navigate={navigate} />}
            {isLoggedIn && <Navigate to='/' />}
        </>
    )
}
class Login extends Component {

    constructor(props) {
        super(props)

        this.state = {
            username: '',
            password: ''
        }
    }

    render() {

        const { username, password } = this.state;

        return (
            <div>
                <Header showSignup={true} showLogin={false} />
                <div className="container">
                    <h1>Login</h1>
                    <div>
                        <form className='form' onSubmit={this.handleSubmit}>
                            <input className='form-field' name='username' type='text' placeholder='Username' value={username} onChange={(event) => this.handleChange(event)} />

                            <input className='form-field' name='password' type='password' placeholder='Password' value={password} onChange={(event) => this.handleChange(event)} />
                            <button className='submit' type='submit'>Login</button>
                        </form>
                    </div>
                </div>
            </div>
        )
    }

    handleSubmit = (event) => {
        event.preventDefault();
        const { navigate } = this.props;
        const { username, password } = this.state;
        const loginModel = {
            username: username,
            password: password
        }

        UserService.login(loginModel)
            .then(res => {
                sessionStorage.setItem('authorization', res.headers.authorization)
                sessionStorage.setItem('role', res.headers.role)

                navigate('/home');
            })
            .catch(err => console.log(err))
    }

    handleChange = (event) => {
        this.setState(
            {
                [event.target.name]: event.target.value
            }
        )
    }
}
export default LoginPage;
