import { State } from '../redux/state-logic/Store';
import React, { useState, useEffect, SyntheticEvent } from 'react';
import {Link, useHistory} from 'react-router-dom';
import {useSelector} from 'react-redux';
import axios from 'axios';

 function EditUserProfile() {
     //update user states
  const history = useHistory();
  const [firstname, setFirstname] = useState("");
  const [lastname, setLastname] = useState("");
  const [email, setEmail] = useState("");
  const state = useSelector((state: State) => state.myStateReducer);

  const updateUserInfo = async (e: SyntheticEvent) => {
    e.preventDefault();
   
    const axiosPayLoad: any = await axios.post('http://localhost:8080/project2hibernate/api/usercontrol/updateuser', {
     "userId": state.currentUser?.userId,
      "picture": state.currentUser?.picture,
      "background": state.currentUser?.background,
      "userName": state.currentUser?.userName,
     // "firstName": firstName,
     // "lastName": lastName,
      "email": email,
      "passWord": state.currentUser?.passWord
    });
    const axiosData: any = axiosPayLoad.data;
          
    console.log("axiosdata: ", axiosData);
    console.log("Update Done")
  }

    return(
        <div className='classes.input'>
            <input value="" onChange={e => setFirstname(e.target.value)} />
            <input value="" onChange= {e => setLastname(e.target.value)}/>
            <input value="" onChange= {e => setEmail(e.target.value)}/>
            <button >Update</button>
      </div>   
    );
}
export default  EditUserProfile  ;
