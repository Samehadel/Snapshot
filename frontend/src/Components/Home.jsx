import React, { Component } from "react";
import { useNavigate } from "react-router-dom";
import UserService from "../services/UserService";
import ImageService from "../services/ImageService";
import Header from "./HeaderComponent";
import '../styles/HomeStyle.css';

function HomePage(props) {
    const navigate = useNavigate();

    return (
        <Home navigate={navigate} />
    )
}

class Home extends Component {


    constructor(props) {
        super(props);

        this.state = {
            images: []
        }
    }


    render() {
        const isLoggedIn = UserService.isUserLoggedIn();
        const isAdmin = UserService.isAdmin();
        const { images } = this.state;

        return (
            <>
                <Header showLogout={isLoggedIn} showSignup={false} showLogin={!isLoggedIn} showPost={!isAdmin && isLoggedIn} />
                <div className='images-grid' >
                    {images.map(image =>
                        <div className='image' key={image.id}>
                            <img src={'data:' + image.metadata.imageFormat + ';base64,' + image.data} onClick={() => this.handleImageClick(image)} />
                            <div>
                                <p>{image.description}</p>
                                <p>{image.category}</p>
                            </div>
                            {isAdmin &&
                                <div>
                                    <button className='accept' onClick={() => this.handleAcceptImage(image.id)} >Accept</button>
                                    <button className='reject' onClick={() => this.handleRejectImage(image.id)}>Reject</button>
                                </div>}
                        </div>
                    )}
                </div>
            </>
        );
    }

    componentDidMount() {
        const isAdmin = UserService.isAdmin();
        console.log('isAdmin', isAdmin);
        if (isAdmin) {
            ImageService.getAllAdminImages()
                .then(res => {
                    this.setState({
                        images: res.data
                    })
                })
                .catch(err => console.log(err));

        } else {
            ImageService.getAllUsersImages()
                .then(res => {
                    this.setState({
                        images: res.data
                    })
                })
                .catch(err => console.log(err));
        }


    }

    handleImageClick = (img) => {
        this.props.navigate(`/home/image/${img.id}`)
    }

    handleAcceptImage = (id) => {
        //console.log('Accept', id);
        const filterImages = this.state.images.filter(img => img.id !== id);

        //Use API 
        ImageService.acceptImage(id)

        //Set State
        this.setState({
            images: filterImages
        })
    }

    handleRejectImage = (id) => {
        //console.log('Reject', id);
        const filterImages = this.state.images.filter(img => img.id !== id);

        //Use API 
        ImageService.rejectImage(id)

        //Set State
        this.setState({
            images: filterImages
        })

    }
}
export default HomePage;