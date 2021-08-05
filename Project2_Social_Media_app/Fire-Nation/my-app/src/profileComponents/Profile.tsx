//import React from 'react';
import "./Profile.css";
//import { useState } from 'react';
import { Storage } from "aws-amplify";
import { ActionType, IAction } from "../redux/reducer-logic/ActionStructure";

import axios from 'axios';
import * as userInfo from '../redux/state-logic/StateStructure';

import React, { useState, useEffect, SyntheticEvent } from 'react';
import { Dispatch } from "react";
// import * as Redux from 'redux';
import * as ReactRedux from 'react-redux';
//import './Login.css';
import {Link, useHistory} from 'react-router-dom';
//import sample from './fire.mp4';
import { IUser } from '../redux/state-logic/StateStructure';
import {useSelector} from 'react-redux';
import { State } from '../redux/state-logic/Store';
import CssBaseline from '@material-ui/core/CssBaseline';
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import EditUserProfile from './editProfile';
import './editProfile';
import { ThreeSixty } from "@material-ui/icons";
import { Topbar } from "../MainPageComponents/TopBar";
import PostFeed  from '../FeedComponents/PostFeed/PostFeed';
import "../MainPageComponents/MainMenu.css";
import sample from '../MainPageComponents/fire.mp4';

const mapDispatchToProps1 = (dispatch: Dispatch<IAction>) => {
  //let's extract only the functionality we need to use
  return {
      onLogin: (incomingPayload: any) => { dispatch({ type: ActionType.LOGIN, payload: incomingPayload }) },
  }
}

const mapStateToProps1 = (state: any) => {
//logic goes here
//said logic will extract the NECESSARY state you need, to simplify the component
  return {
      seekImage: state.myStateReducer.image
  }
}
interface IProps {
  image: any,
  images: any,
  imgName: any,
  imageCover: any,
  imagesCover: any,
  imgNameCover: any
}

function Profile(props: IProps) {

  
  const [image, setImage] = React.useState();
  const [images, setImages] = useState([]);
  const [images1, setImages1] = useState([]);

  const [imgName, setImgName] = useState();
  const [imageCover, setImageCover] = React.useState();
  const [imagesCover, setImagesCover] = useState([]);
  const [imgNameCover, setImgNameCover] = useState();
  const [posts, setPosts]: any = useState([]);

  const history = useHistory();
  const [firstname, setFirstname] = useState("");
  const [lastname, setLastname] = useState("");
  const [email, setEmail] = useState("");
  const state = useSelector((state: State) => state.myStateReducer);
  const [users, setUsers] = useState<IUser[]>([{userId: 0, email: "", firstName: "", lastName: "", passWord: "", picture: "", background: "", userName: ""}]);

  useEffect(() => {
    getPosts();
    getImages(state.currentUser?.picture);
    getCoverImages(state.currentUser?.background);
  }, []);

  // function take profile image name as a parameter and return image from storge (search by image name), ten save image on state 
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

  const getPosts = async () => {
    const axiosPayLoad: any = await axios.post('http://localhost:8080/project2hibernate/api/usercontrol/getposts', {
        userId: state.currentUser?.userId,
        content: "",
        picture: "",
    });
    const axiosData: any = axiosPayLoad.data;
    setPosts(axiosPayLoad.data);
}

  // function take cover image name as a parameter and return image from storge (search by image name), ten save image on state 
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
 
    
 
 // read profile image from window when user click button
  const onSelectFile = (event:any) => {
    console.log("in select file");
    const reader:any = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    console.log("in event.target .....",event.target.files[0]);
    reader.addEventListener("load", () => {
        setImage(event.target.files[0]);
        const imgToSend = event.target.files[0];
        console.log("you selected: ", imgToSend.name);
        fetchImages(imgToSend);
      });
    };

    // read cover image from window when user click button
    const onSelectFileCover = (event:any) => {
      console.log("in select file");
      const reader:any = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      console.log("in event.target .....",event.target.files[0]);
      reader.addEventListener("load", () => {
          setImageCover(event.target.files[0]);
          const imgToSend = event.target.files[0];
          console.log("you selected: ", imgToSend.name);
          fetchImagesCover(imgToSend);
        });
      };

      
  // fetch profile image from storage and put image in state
  async function fetchImages(imgToSend1:any) {
    console.log("image ready to fetch is : ", imgToSend1);
    if (imgToSend1) {
      // Storage.put: uploads selected image  to s3 bucket
     Storage.put(imgToSend1.name, imgToSend1, {
      contentType: "image/png",
      });
      console.log("result from fetch:", imgToSend1);
      console.log("result from fetch name:", imgToSend1.name);
      const imageKeys:any = await Storage.list(imgToSend1.name);
      console.log("imageKeys 1:", imageKeys);
      const imageKeys2:any = await Promise.all(
        imageKeys.map(async (k:any) => {
          const signedUrl = await Storage.get(k.key);
          return signedUrl;
        })
      );
      console.log("imageKeys 2:", imageKeys2);
      console.log("imageKeys uploading:",imgToSend1 );
      console.log("imageKeys uploading key-profileImageName :",imgToSend1.name);
      console.log("image kes is   ",imageKeys2);
      setImages(imageKeys2); // save file(image name + key) to state
      setImgName(imgToSend1.name); 
    }
  }
  // fetch cover image from storage and put image in state
  async function fetchImagesCover(imgToSend1:any) {
    console.log("image ready to fetch is : ", imgToSend1);
    if (imgToSend1) {
      // Storage.put: uploads selected image  to s3 bucket
     Storage.put(imgToSend1.name, imgToSend1, {
      contentType: "image/png",
      });
      console.log("result from fetch:", imgToSend1);
      console.log("result from fetch name:", imgToSend1.name);
      const imageKeys:any = await Storage.list(imgToSend1.name);
      console.log("imageKeys 1:", imageKeys);
      const imageKeys2:any = await Promise.all(
        imageKeys.map(async (k:any) => {
          const signedUrl = await Storage.get(k.key);
          return signedUrl;
        })
      );
      console.log("imageKeys 2:", imageKeys2);
      console.log("imageKeys uploading:",imgToSend1 );
      console.log("imageKeys uploading key -coverImageName:",imgToSend1.name);
      console.log("image kes is   ",imageKeys2);
      setImagesCover(imageKeys2); // save file(image name + key) to state
      setImgNameCover(imgToSend1.name); 
    }
  }

// update profile and cover images
const updateUserInfo = async (e: SyntheticEvent) => {
  e.preventDefault();
  console.log(" Zach profile  image is   :",imgName);
 console.log(" Zach cover image is   :",imgNameCover);
  const axiosPayLoad: any = await axios.post('http://localhost:8080/project2hibernate/api/usercontrol/updateuser', {
   "userId": state.currentUser?.userId,
    "picture": imgName,
    "background": imgNameCover,
    "userName": state.currentUser?.userName,
    "firstName": state.currentUser?.firstName,
    "lastName": state.currentUser?.lastName,
    "email": state.currentUser?.email,
    "passWord": state.currentUser?.passWord
  });
  const axiosData: any = axiosPayLoad.data;
        
  console.log("axiosdata: ", axiosData);
  console.log("Update Done")
}
// update user info
const updateUserInfo1 = async (e: SyntheticEvent) => {
  e.preventDefault();
  console.log(" New user name is   :",firstname);
 console.log(" New last name is   :",lastname);
 console.log(" New last name is   :",email);

  const axiosPayLoad: any = await axios.post('http://localhost:8080/project2hibernate/api/usercontrol/updateuser', {
   "userId": state.currentUser?.userId,
   "picture": state.currentUser?.picture,
    "background": state.currentUser?.background,
   "userName": state.currentUser?.userName,
    "firstName": firstname,
    "lastName":lastname,
    "email": email,
    "passWord": state.currentUser?.passWord
  });
  const axiosData: any = axiosPayLoad.data;
        
  console.log("axiosdata: ", axiosData);
  getUsers();
  console.log("new user ", state.currentUser?.firstName);
  console.log("Update Done")
}
//display user info
const getUsers = async () => {
        
  const axiosPayLoad: any = await axios.get('http://localhost:8080/project2hibernate/api/usercontrol/getusers');
  const axiosData: any = axiosPayLoad.data;
  setUsers(axiosData);
  
}

useEffect(() => {
  getUsers();
});
  return (
    <>
    <div>
          <video className='background-video' autoPlay loop muted>
                <source src={sample} type='video/mp4' />
          </video>
    </div>
    <Topbar/>
    <div className="container border"
      style={{
      width: '150%',
      backgroundImage: `url('https://wallpapercave.com/wp/OhiaJoY.jpg')`,
      backgroundPosition: 'center',
      backgroundSize: 'cover',
      }}>
      <div>
        <div className="profileCover">
        {
          imagesCover.map(image =>(
              <img  className="profileCoverImg"  src={image} key={image} alt="" /> ))
        }   
        <br/>
        <input  //className="custom-file-input" 
          type="file"
          onChange={onSelectFileCover}/>
        <br/>
        <input //className="custom-file2-input" 
          type="file"
          onChange={onSelectFile}/>
        {
          images.map(image =>(
          <img  className="profileUserImg"  src={image} key={image} alt="" />   ))
        }       
      </div>  
      <button className="button" onClick={updateUserInfo}>Save Images</button>

      <span style={{color:'white'}}>Welcome ,  {state.currentUser?.userName}</span>

      <div className="former-inner">
        <form className="row" style={{margin: "25px 85px 75px 100px"}}>
          <label style={{color:'white'}} >First Name</label>
          <input
            className="form-control"
            type='text'
            name='firstName'
            value={state.currentUser?.firstName}
          />
          <br/>
          <label style={{color:'white'}}>Last Name</label>
          <input
            className="form-control"
            type='text'
            name='lastName'
            value={state.currentUser?.lastName}
          />
          <br/>
          <label style={{color:'white'}}>User Email</label>
          <input
            className="form-control"
            type='text'
            name='email'
            value={state.currentUser?.email}
          />
          
          <br/>
          
          
         
        </form>
       </div>

      <br/><br/><br/><br/>
    
      <form className="row" style={{margin: "25px 85px 75px 100px"}}>
        <div className='summary'>
        <span style={{color:'white'}}>Update User Info</span><br/><br/>

          <label >First Name</label>
            <input  onChange={e => setFirstname(e.target.value)} /><br/>
          <label >Last Name</label>
            <input onChange= {e => setLastname(e.target.value)}/><br/>
          <label >User Email</label>
            <input  onChange= {e => setEmail(e.target.value)}/><br/>
            <button className="form-control btn btn-primary" style={{marginTop: '30px'}} onClick={updateUserInfo1}>Update</button>
        </div>   
      </form>
    </div> 
  </div>   
  <div className = "mainBody">
  <div className = "feedSpacer">
      
    </div>
      <div>
            {posts.map(function(val:any){
              // eslint-disable-next-line react/jsx-key
              return <PostFeed key = {val} Post = {val}/>
            })
            }
  </div>
  <div className = "feedSpacer">
      
  </div>

  </div>  
    </>
  );
}
export const profileComponent = ReactRedux.connect(mapStateToProps1, mapDispatchToProps1)(Profile);