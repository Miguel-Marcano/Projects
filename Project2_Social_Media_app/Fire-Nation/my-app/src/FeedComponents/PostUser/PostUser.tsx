import React, {useState, SyntheticEvent} from 'react';
import axios from 'axios';
import "./postModular.css";
import upload from './icons/upload.png';
import {IAppState, IUser, IReimbursement} from '../../redux/state-logic/StateStructure'
import { render } from '@testing-library/react';
import { useSelector } from 'react-redux';
import { State } from '../../redux/state-logic/Store';
import { Storage } from "aws-amplify";





export const UserPost= () => {

  const [post, setPost] = useState("");
  const [image, setImage] = React.useState();
  const [images, setImages] = useState([]);
  const [imgName, setImgName] = useState();
  const state = useSelector((state: State) => state.myStateReducer);

  
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
  


  const share = async (e: SyntheticEvent) => {
    // console.log(post);
    const axiosPayLoad: any = await axios.put('http://localhost:8080/project2hibernate/api/postcontrol/addpost', {
        //"postUser": state.currentUser,

        "content": post,
        "userId": state.currentUser?.userId,
        "picture": imgName
    }
    
    );
    // console.log("2");
    // console.log(post);
  }

    return (
        <div className="postMod">
          <div className="postWrapMod">
            {/**Your profile image */}
    
            {/**Your username */}
            <span className="postUser">
            {state.currentUser?.firstName}
            </span>

            {/**<FetchR/>*/}
            <div>
            
            {/**Text post */}
            <input placeholder="What's up chap?" className="postMates" onChange={e => setPost(e.target.value)} value={post}/>
    
            </div>
            
          </div>
          <hr className="elementCenter" />
    
          {/**Lower section of post box area where you can insert a extra item */}
          <div className="shareBottom">
            <div className="shareOptions">
              {/**Photo*/}
              <div className="shareOption">
                  <input className="custom-file2-input2" 
                  type="file"
                  onChange={onSelectFile}/>

                {/* <span className="shareOptionText">Photo or Video</span> */}
              </div>
            </div>
    
            {/**Post to DB button */}
            <button onClick = {share} className="shareButton">Share</button>
          </div>
        </div>
      );     
}



/*export default class FetchR extends React.Component {
  state = {
    loading: true,
    person: null
  };
  
  async componentDidMount() {
    const url = "https://api.randomuser.me/"
    const response = await fetch(url);
    const data = await response.json();
    this.setState({ person: data.results[0], loading: false});
  }
  /**render(){
    return ( 
      <div>
        {this.state.loading || !this.state.person ? (
          <div>loading...</div>
          ) : ( 
            <div>
              <div>{this.state.person.name.first}</div>
            </div>
          )
        }
      </div>
    )
  }
} */
  

