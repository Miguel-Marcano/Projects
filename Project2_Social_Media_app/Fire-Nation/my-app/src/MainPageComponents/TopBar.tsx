import React, {useState, useEffect, SyntheticEvent} from 'react';
import {Search} from "@material-ui/icons";
import './TopBar.css';
import {useSelector} from 'react-redux';
import { State } from '../redux/state-logic/Store';
import {SearchUsers} from './SearchPerson';
import axios from 'axios';
import { IUser } from '../redux/state-logic/StateStructure';
import logo from './FireNationSymbol.png';
import {Link, useHistory} from 'react-router-dom';
import * as ReactRedux from 'react-redux';
import { Dispatch } from "react";
import { ActionType, IAction } from "../redux/reducer-logic/ActionStructure";
import { Storage } from "aws-amplify";

/* Need to install React-select
    npm install --save react-select
*/

const mapDispatchToProps = (dispatch: Dispatch<IAction>) => {
    //let's extract only the functionality we need to use
    return {
        loggerOut: (incomingPayload: any) => { dispatch({ type: ActionType.LOGOUT, payload: incomingPayload }) },
    }
}

const mapStateToProps = (state: any) => {
//logic goes here
//said logic will extract the NECESSARY state you need, to simplify the component
    return {
        seekOtherUser: state.myStateReducer.otherUser
    }
}

interface IProps {
    loggerOut: any,
}

const TopbarCore: React.FunctionComponent<IProps> = (props: IProps) => {
    
    const history = useHistory();
    const state = useSelector((state: State) => state.myStateReducer);
    const [images, setImages] = useState([]);
    const [users, setUsers] = useState<IUser[]>([{userId: 0, email: "", firstName: "", lastName: "", passWord: "", picture: "", background: "", userName: ""}]);

    const getUsers = async () => {
        
        const axiosPayLoad: any = await axios.get('http://localhost:8080/project2hibernate/api/usercontrol/getusers');
        const axiosData: any = axiosPayLoad.data;
        setUsers(axiosData);
        
    }

    useEffect(() => {
        getUsers();
        getImages(state.currentUser?.picture);
      }, []);

      async function getImages(imgToSendd:any) {
          console.log(state.currentUser?.picture);
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
    

      const logout = async () => {
        
        const axiosPayLoad: any = await axios.delete('http://localhost:8080/project2hibernate/api/logincontrol/logout');
        const axiosData: any = axiosPayLoad.data;
        props.loggerOut(null);  
        history.push('/login');

    }

  return (
    <>
        <div className = "topNavBarContainer">
            <div className = "topNavBarLeft">
                <Link to='/main' style={{color:'white'}}>
                    <img className='topLogoImg' src= {logo} alt='Symbol' />
                    <span className = "navBarLogo">FIRE</span>
                </Link>
            </div>
            <div className = "topNavBarCenter">
                <div className = "searchBar">
                    {/* <Search className = "searchIcon"/>
                    {/* <input type = "text" placeholder = "This is a search bar" className = "searchInput" ></input> */}
                    <SearchUsers listUsers = {users}/>
                </div>
            </div>
            <div className = "topNavBarRight">
                <div className = "topNavBarLinks">
                    <Link to='/main' style={{color:'white'}}><span className = "toplink">HomePage</span></Link>
                    <Link to='/Profile' style={{color:'white'}}><span className = "toplink">Profile</span></Link>
                    <Link to='/login' style={{color:'white'}}><span className = "toplink" onClick = {logout}>LogOut</span></Link>
                </div>
                {/* <div className = "topNavBarIcons">
                    <div className = "topNavBarIcon" >
                        <Person/>
                        <span className = "topBarIconNumber">1</span>
                    </div>
                    <div className = "topNavBarIcon" >
                        <Chat/>
                        <span className = "topBarIconNumber">1</span>
                    </div>
                </div> */}
                <div>
                    <span className = "topNavBarName">{state.currentUser?.lastName}, {state.currentUser?.firstName} </span>
                    {
                        images.map(image =>(
                        <img  className="topNavBarPic"  src={image} key={image} alt="" /> ))
                    }  
                </div>
            </div>


        </div>
    </>
  );
};


export const Topbar = ReactRedux.connect(mapStateToProps, mapDispatchToProps)(TopbarCore);
