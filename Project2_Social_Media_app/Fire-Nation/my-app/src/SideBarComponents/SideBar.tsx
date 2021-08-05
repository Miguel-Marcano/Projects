import React, { useState } from 'react';
import {Search, Person, Chat} from "@material-ui/icons";
import './SideBar.css';



const Sidebar = () => {


  return (
    <div className = "sideBar">
        <div className = "sideBarWrapper">
            <ul className = "sideBarList">
                <li className = "sideBarListItem">
                    <Chat className = "sideBarListIcon"/>
                    <span className = "sideBarListItemText">Post Feed</span>
                </li>
                <li className = "sideBarListItem">
                    <Chat className = "sideBarListIcon"/>
                    <span className = "sideBarListItemText">Post Feed</span>
                </li>
                <li className = "sideBarListItem">
                    <Chat className = "sideBarListIcon"/>
                    <span className = "sideBarListItemText">Post Feed</span>
                </li>
                <li className = "sideBarListItem">
                    <Chat className = "sideBarListIcon"/>
                    <span className = "sideBarListItemText">Post Feed</span>
                </li>
                <li className = "sideBarListItem">
                    <Chat className = "sideBarListIcon"/>
                    <span className = "sideBarListItemText">Post Feed</span>
                </li>
               
            </ul>

        </div>
    </div>
  );
};

export default Sidebar;