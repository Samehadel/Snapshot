class ImageProcessing {
    prepareImage(data, type){
        return 'data:' + type + ';base64,' + data;
    }
}
export default new ImageProcessing();