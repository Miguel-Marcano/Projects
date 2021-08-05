import React, {useState} from 'react';
import { IUser } from '../redux/state-logic/StateStructure';
import Select from 'react-select';
import "./SearchPerson.css";
import {useSelector} from 'react-redux';
import { State } from '../redux/state-logic/Store';
import * as ReactRedux from 'react-redux';
import { Dispatch } from "react";
import { ActionType, IAction } from "../redux/reducer-logic/ActionStructure";
// import * as Redux from 'redux';
//import './Login.css';
import {Link, useHistory} from 'react-router-dom';
//import sample from './fire.mp4';


    interface IProps {
        findPerson: any,
        listUsers: IUser[]
    }

    interface SearchUser {
        value: string;
        label: string;
    }

    const mapDispatchToProps = (dispatch: Dispatch<IAction>) => {
        //let's extract only the functionality we need to use
        return {
            findPerson: (incomingPayload: any) => { dispatch({ type: ActionType.VIEWUSER, payload: incomingPayload }) },
        }
    }
    
    const mapStateToProps = (state: any) => {
    //logic goes here
    //said logic will extract the NECESSARY state you need, to simplify the component
        return {
            seekOtherUser: state.myStateReducer.otherUser
        }
    }


    const SearchUsersList: React.FunctionComponent<IProps> = (props: IProps) => {
        const history = useHistory();
        const state = useSelector((state: State) => state.myStateReducer);
        const [searchList, setSearchList] = useState()
        const searchArr: SearchUser[] =  props.listUsers.map((user)=>{ return { value: user.userName, label: user.firstName + " " + user.lastName}});
        const [searchingUser, setSearchingUser] = useState<SearchUser | null>({value: "", label: ""})

    const findUserProfile = () => {
        props.findPerson(searchingUser);
        console.log(state.otherUser);
        history.push('/otherprofile');


    }

         return (
            <div className= "searchBarOnTop">
                <button onClick = {findUserProfile}>Find</button>
             <Select
                options = {searchArr} className = "searchInput" onChange = {setSearchingUser} isSearchable
             />
            </div>
           )
    }

export const SearchUsers = ReactRedux.connect(mapStateToProps, mapDispatchToProps)(SearchUsersList);

