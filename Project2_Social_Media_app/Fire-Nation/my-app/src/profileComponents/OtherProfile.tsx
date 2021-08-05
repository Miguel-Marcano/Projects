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
import './editProfile';
import { ThreeSixty } from "@material-ui/icons";
import { Topbar } from "../MainPageComponents/TopBar";
import PostFeed  from '../FeedComponents/PostFeed/PostFeed';
import "../MainPageComponents/MainMenu.css";
import sample from '../MainPageComponents/fire.mp4';


function OtherProfileComponent() {

  
  const [image, setImage] = React.useState();
  const [images, setImages] = useState([]);
  const [images1, setImages1] = useState([]);

  const [imgName, setImgName] = useState();
  const [imageCover, setImageCover] = React.useState();
  const [imagesCover, setImagesCover] = useState([]);
  const [imgNameCover, setImgNameCover] = useState();
  const state = useSelector((state: State) => state.myStateReducer);
  
  const history = useHistory();
  const [firstname, setFirstname] = useState("");
  const [lastname, setLastname] = useState("");
  const [email, setEmail] = useState("");
  const [picture, setPicture] = useState("");
  const [background, setbackground] = useState("");
  const [username, setUser] = useState("");
  const [posts, setPosts]: any = useState([]);

  
  useEffect(() => {
    getUsers()
    getImages(picture)
    getCoverImages(background);
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
  
  


  const getPosts = async (id:number) => {
      const axiosPayLoad: any = await axios.post('http://localhost:8080/project2hibernate/api/usercontrol/getposts', {
          userId: id,
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



//display user info
const getUsers = async () => {
        
    console.log("state" + state.otherUser?.value)
  const axiosPayLoad: any = await axios.post('http://localhost:8080/project2hibernate/api/usercontrol/getuserbyusername', {
      "userName": state.otherUser?.value,
      "picture": "",
    } );

  const axiosData: IUser = axiosPayLoad.data;
  console.log(axiosData);
  getPosts(axiosData.userId);
  setUser(axiosData.userName);
  setEmail(axiosData.email);
  setPicture(axiosData.picture);
  setbackground(axiosData.background);
  setFirstname(axiosData.firstName);
  setLastname(axiosData.lastName);
  getImages(axiosData.picture);
  getCoverImages(axiosData.background);

  
}

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
        {
          images.map(image =>(
          <img  className="profileUserImg"  src={image} key={image} alt="" />   ))
        }       
      </div>  

      <span style={{color:'white'}}>Welcome ,  {username}</span>

      <div className="former-inner">
        <form className="row" style={{margin: "25px 85px 75px 100px"}}>
          <label style={{color:'white'}} >First Name</label>
          <input
            className="form-control"
            type='text'
            name='firstName'
            value={firstname}
          />
          <br/>
          <label style={{color:'white'}}>Last Name</label>
          <input
            className="form-control"
            type='text'
            name='lastName'
            value={lastname}
          />
          <br/>
          <label style={{color:'white'}}>User Email</label>
          <input
            className="form-control"
            type='text'
            name='email'
            value={email}
          />
          
          <br/>
          
          
         
        </form>
       </div>

      <br/><br/><br/><br/>
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
export default OtherProfileComponent;