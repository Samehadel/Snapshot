import axios from 'axios';

class UserService {
    
    /* State Of The User Section */ 
    isUserLoggedIn(){
        const authenticationToken = sessionStorage.getItem("authorization");
    
        return authenticationToken == null ? false : true; 
    }
    
    isAdmin(){
        const role = sessionStorage.getItem("role");
        
        return role == 'ROLE_ADMIN' ? true : false; 
    }

    getUserToken(){
        return sessionStorage.getItem("authorization");
    }

    logout(){
        sessionStorage.removeItem('authorization');
        sessionStorage.removeItem('role');
    }

    /* User APIs Section */ 
    login(loginModel){
        axios.defaults.headers.common = {}
        return axios.post('http://localhost:8082/login', loginModel);
    }

    
    signup(signupModel){
        axios.defaults.headers.common = {}
        return axios.post('http://localhost:8082/users/signup', signupModel);
    }

    
}

export default new UserService();