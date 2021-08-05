import React, { useState, useEffect } from 'react';
//import {Search, Person, Chat} from "@material-ui/icons";
import './Feed.css';
import axios from 'axios';
import {UserPost} from './PostUser/PostUser'
import PostFeed  from './PostFeed/PostFeed'
import { IPost } from '../redux/state-logic/StateStructure';


interface IProps {
  postList: any
}
const Feed = () => {
  const [posts, setPosts]: any = useState([]);
  


  const getPosts = async () => {
      const axiosPayLoad: any = await axios.get('http://localhost:8080/project2hibernate/api/postcontrol/getallposts');
      const axiosData: any = axiosPayLoad.data;
      setPosts(axiosPayLoad.data);
  }

  useEffect(() => {
    getPosts();
  });
  return (
    <div className = "feed">
        <div className = "feedWrapper">
            <UserPost/>
            {posts.map(function(val:any){
              // eslint-disable-next-line react/jsx-key
              return <PostFeed key = {val} Post = {val}/>
            })
            }
        </div>
    </div>
  );
};

export default Feed;