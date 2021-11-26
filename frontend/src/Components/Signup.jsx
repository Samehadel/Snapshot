import React, { Component } from "react";
import { useNavigate } from "react-router-dom";
import UserService from '../services/UserService.js';
import Header from './HeaderComponent';
import '../styles/SignupStyle.css';

function SignupPage(props) {
    const navigate = useNavigate();

    return (
        <Signup navigate={navigate} />
    );
}


class Signup extends Component {

    constructor(props) {
        super(props)

        this.state = {
            username: '',
            password: '',
            confirmPassword: '',
            showMessage: false,
        }
    }

    render() {

        const { username, password, confirmPassword, showMessage } = this.state;

        return (
            <div>
                <Header showSignup={false} showLogin={true} />
                <div className="container">
                    <h1>Sign Up</h1>
                    {showMessage && <p className='error-message'>Password and confirm password don't match</p>}
                    <div>
                        <form className='form' onSubmit={this.handleSubmit}>
                            <input className='form-field' name='username' type='text' placeholder='Username' value={username} onChange={(event) => this.handleChange(event)} />
                            <input className='form-field' name='password' type='password' placeholder='Password' value={password} onChange={(event) => this.handleChange(event)} />
                            <input className='form-field' name='confirmPassword' type='password' placeholder='Confirm Password' value={confirmPassword} onChange={(event) => this.handleChange(event)} />
                            <button className='submit' type='submit'>Sign Up</button>
                        </form>
                    </div>

                </div>
            </div>
        )
    }

    handleSubmit = (event) => {
        event.preventDefault();

        const { username, password, confirmPassword } = this.state;
        const signupModel = {
            username: username,
            password: password
        }
        if (this.validate()) {
            UserService.signup(signupModel)
                .then(res => {
                    this.props.navigate('/login')
                })
                .catch(err => console.log(err))
        }

    }

    validate() {
        const { password, confirmPassword } = this.state;

        if (password !== confirmPassword) {
            this.setState({
                showMessage: true
            })
        } else {
            this.setState({
                showMessage: false
            })
        }

        return password === confirmPassword ? true : false
    }

    handleChange = (event) => {
        this.setState(
            {
                [event.target.name]: event.target.value
            }
        )
    }
}


export default SignupPage;