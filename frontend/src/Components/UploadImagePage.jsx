import React, { Component } from 'react';
import ImageService from '../services/ImageService';
import Header from './HeaderComponent';
import '../styles/UploadImageStyle.css';

class UploadImagePage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            file: null,
            description: '',
            category: 'Living Thing'
        }
    }

    render() {
        const { file, description, category } = this.state;

        return (
            <div>
                <Header showLogout={true} showPost={true} />
                <form className='post-form' onSubmit={this.handleSubmit} className="image-upload-form">
                    <input className='post-input' type='file' name='file' onChange={this.handleFileChange} />
                    <input className='post-input' type='text' name='description' value={description} onChange={this.handleChange} placeholder='Image Description' />
                    <select className='post-input' name="category" onChange={this.handleChange}>
                        <option value="Living Thing">Living Thing</option>
                        <option value="Machine">Machine</option>
                        <option value="Nature">Nature</option>
                    </select>
                    <button type='submit'>Post</button>
                </form>
            </div>
        );
    }

    handleSubmit = (event) => {
        event.preventDefault();
        const { file, description, category } = this.state;

        ImageService.postImage(file, description, category)
            .then(
                this.setState({
                    file: null,
                    description: '',
                    category: 'Living Thing'
                })
            )
            .catch(err => console.log(err))
    }

    handleFileChange = (event) => {
        this.setState({
            file: event.target.files[0]
        })
    }
    handleChange = (event) => {
        this.setState(
            {
                [event.target.name]: event.target.value
            }
        )
    }
}
export default UploadImagePage;