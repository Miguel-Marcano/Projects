import React, { useState, useEffect } from 'react';
import axios from 'axios';
import "./postFeed.css";
import Dislike from './icons/Dislike.png'
import Like from './icons/Like.png'
import { IPost } from '../../redux/state-logic/StateStructure';
import { Storage } from "aws-amplify";


interface IProps {
  Post: any
}



const PostFeed: React.FunctionComponent<IProps> = (props: IProps) => {
  console.log(props.Post.postUser);
  const [likes, setLikes] = useState(props.Post.postLikes);
  const [image, setImage] = React.useState();
  const [images, setImages] = useState([]);
  const [images1, setImages1] = useState([]);

  const [imgName, setImgName] = useState();
  const [imageCover, setImageCover] = React.useState();
  const [imagesCover, setImagesCover] = useState([]);
  const [imgNameCover, setImgNameCover] = useState();

  useEffect(() => {
    getImages(props.Post.postUser.picture);
    getCoverImages(props.Post.postPicture);
  },[]);

  async function getImages(imgToSendd:any) {
    console.log("inside getImages function ");

      const imageKeyss:any = await Storage.list(imgToSendd);
      const imageKeyss2:any = await Promise.all(
        imageKeyss.map(async (k:any) => {
          const signedUrl = await Storage.get(k.key);
          return signedUrl;
        })
      );
      console.log("profile  ",imageKeyss2);
      setImages(imageKeyss2); 
     return(imageKeyss2);
  }  
  async function getCoverImages(imgToSendCover:any) {
    console.log("inside getCoverImages function ");
    // Storage.put: uploads selected image  to s3 bucket
  const imageKeyssc:any = await Storage.list(imgToSendCover);
  const imageKeyss2c:any = await Promise.all(
    imageKeyssc.map(async (k:any) => {
      const signedUrl = await Storage.get(k.key);
      return signedUrl;
    })
  );
  console.log("cover  ",imageKeyss2c);

  setImagesCover(imageKeyss2c); // save file(image name + key) to state
 return(imageKeyss2c);

  }    





    

  const likeClick = async () => {
    if(likes === props.Post.postLikes){

      setLikes(1 + likes);
      const axiosPayLoad: any = await axios.post('http://localhost:8080/project2hibernate/api/postcontrol/updatepost', {
        "postId": props.Post.postId,
        "timePosted": null,
        "postContent": null,
        "postLikes": likes + 1,
      });
      const axiosData: any = axiosPayLoad.data;
    }
    getCoverImages(props.Post.postPicture);

  }
    return (
        <div className="post">
          <div className="postWrap">
            <div className="postTOP">
              <div className="postTopLeft">
                {/**Friend's profile image */}
                {/* <img className="postProfileImg" src={props.Post.postUser.picture} alt="" /> */}
                {
                images.map(image =>(
                  <img  className="postProfileImg"  src={image} key={image} alt="" />
                    ))
                }  

                {/**Friend's username */}
                <span className="postUsername">{props.Post.postUser.userName}</span>
                
                {/**When the post was made */}
                <span className="postDate">{props.Post.timePosted}</span>
              </div>
              <div className="postTopRight"></div>
            </div>
            <div className="postCenter">
              <span className="postText">{props.Post.postContent}</span>
    
              {/**image in your friend's post */}

              {/* <img className="postImg" src="" alt="" /> */}
              {
                imagesCover.map(image =>(
                <img  className="postImg"  src={image} key={image} alt="" /> ))
              }   

            </div>
            <div className="postBottom">
              <div className="postBottomLeft">
                {/**like button */}
                <img className="likeYou" src={Like} alt="" onClick= {() => likeClick()}/>
    
                {/**like counter */}
                <span className="postLikeCounter">{likes}</span>
              </div>
              <div className="postBottomRight"></div>
    
            </div>
          </div>
        </div>
      );
}

export default PostFeed;

/*class DisplayPost extends React.Component{
    state = {
      title: '',
      body: '',
      posts: []
    }

    componentDidMount = () =>{
      this.getBlogPost();
    }
    getBlogPost = () =>{
      axios.get('/api')
      .then((response) => {
        const data = response.data;
        this.setState({posts: data});
        console.log('Data has been recieved');
      })
      .catch(() => {
        alert('Error');
      });
    }

    displayBlogPost = (posts) =>{
      if (!posts.length) return null;

      return posts.map((post, index) => (
        <div key = {index}>
          <h3>{post.title}</h3>
          <p>{post.body}</p>
        </div>
      ));
    };
}*/