import React from 'react';
import {Topbar} from "./TopBar";
import Sidebar from "../SideBarComponents/SideBar";
import Feed from "../FeedComponents/Feed";
import sample from './fire.mp4';
import "./MainMenu.css";



const MainMenu = () => {
  return (
    <>
        <div>
          <video className='background-video' autoPlay loop muted>
                <source src={sample} type='video/mp4' />
          </video>
        </div>
        <Topbar/>

        <div className = "mainBody">
          {/* <div className = "mainLeft">
            <Sidebar/>
          </div> */}
          <div className = "mainRight">
            <div className = "feedSpacer"></div>
            <div className = "mainFeed">
              <Feed/>
            </div>
            <div className = "feedSpacer"></div>
          </div>
        </div>
    </>
  );
};

export default MainMenu;