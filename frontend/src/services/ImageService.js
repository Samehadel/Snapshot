import UserService from "./UserService";
import axios from 'axios';

class ImageService {
    baseUrl = 'http://localhost:8082/image';

    
    /* User APIs Section */ 

    postImage(file, description, category){
        axios.defaults.headers.common = {'Authorization': UserService.getUserToken()}
        const url = this.baseUrl + '/users/upload';
        const formData = new FormData();
        const config = {
            headers: {
                'content-type': 'multipart/form-data'
            }
        }

        formData.append('file', file);
        formData.append('description', description);
        formData.append('category', category);

        return axios.post(url, formData, config)
    }

    getImage(id){
        axios.defaults.headers.common = {}

        return axios.get(this.baseUrl + `/users/image/${id}`)
    }
    
    getAllUsersImages(){
        axios.defaults.headers.common = {}

        return axios.get(this.baseUrl + '/users')
    }
    
    /* Admin APIs Section*/

    getAllAdminImages(){
        axios.defaults.headers.common = {'Authorization': UserService.getUserToken()}

        return axios.get(this.baseUrl + '/admin')
    }

    acceptImage(id){
        axios.defaults.headers.common = {'Authorization': UserService.getUserToken()}

        return axios.put(this.baseUrl + `/admin/accept/${id}`)
    }

    rejectImage(id){
        axios.defaults.headers.common = {'Authorization': UserService.getUserToken()}

        return axios.delete(this.baseUrl + `/admin/reject/${id}`)
    }
}
export default new ImageService();
