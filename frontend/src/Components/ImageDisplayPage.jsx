import React, { Component } from "react";
import { useParams } from 'react-router-dom';
import '../styles/ImageDisplayerStyle.css';
import HeaderComponent from './HeaderComponent';



function ImageDisplayPage(props) {
    const { id } = useParams();

    return (
        <ImageDisplayer id={id} />
    )
}

class ImageDisplayer extends Component {

    constructor(props) {
        super(props);

    }

    render() {
        const { id } = this.props;

        //console.log('Image', image)
        return (
            <>
                <HeaderComponent />
                <div className="image-container">
                    <img className='image-content' src={`http://localhost:8082/image/users/image/${id}`} alt="" />
                </div>
            </ >
        );
    }

}

export default ImageDisplayPage;