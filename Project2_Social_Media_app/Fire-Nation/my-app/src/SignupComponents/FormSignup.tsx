import React, { useState, SyntheticEvent } from 'react';
import validate from './validateInfo';
import useForm from './useForm';
import './Form.css';
import {Link, useHistory} from 'react-router-dom';
import { Dispatch } from "react";
import { ActionType, IAction } from "../redux/reducer-logic/ActionStructure";
import { IUser } from '../redux/state-logic/StateStructure';
import axios from 'axios';
import * as ReactRedux from 'react-redux';
import sample from './fire.mp4';
import {useSelector} from 'react-redux';
import { State } from '../redux/state-logic/Store';

const mapDispatchToProps = (dispatch: Dispatch<IAction>) => {
  //let's extract only the functionality we need to use
  return {
      onLogin: (incomingPayload: any) => { dispatch({ type: ActionType.SIGNUP, payload: incomingPayload }) },
  }
}

const mapStateToProps = (state: any) => {
//logic goes here
//said logic will extract the NECESSARY state you need, to simplify the component
  return {
      seekUser: state.myStateReducer.currentUser
  }
}

interface IProps {
  onLogin: any,
  stateUser: IUser
}


const SignupForm: React.FunctionComponent<IProps> = (props: IProps) => {
  // const { handleChange, handleSubmit, values, errors } = useForm(
  //   submitForm,
  //   validate
  // );

    //console.log(values);

    //const logged = useSelector((state: State) => state.isLogged);
    const history = useHistory();
    const [username, setUsername] = useState("");
    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    
    const signup = async (e: SyntheticEvent) => {
        e.preventDefault();
        
        const axiosPayLoad: any = await axios.put('http://localhost:8080/project2hibernate/api/usercontrol/newuser', {
            "userName": username,
            "firstName": firstname,
            "lastName": lastname,
            "email": email,
            "passWord": password
        });
        console.log("Help")
        const axiosData: any = axiosPayLoad.data;
        
        console.log("axiosdata: ", axiosData);
        
        if (axiosData.userName != null)
        {
            props.onLogin(axiosData);
            history.push('/main');
            //axiosData.firstName
        }
    }

  return (
    <div>
      <video className='background-video' autoPlay loop muted>
                <source src={sample} type='video/mp4' />
          </video> 
    <div className="container border"
    style={{marginTop: "50px",
        width: '50%',
        backgroundImage: `url('https://wallpapercave.com/wp/OhiaJoY.jpg')`,
        backgroundPosition: 'center',
        backgroundSize: 'cover',
        
    }}>
      <div className="former-inner">
        <h1 style={{color:'white'}}>
          Get started with us today! Create your account by filling out the
          information below.
        </h1>
        <form className="row" style={{margin: "25px 85px 75px 100px"}}>
          <label style={{color:'white'}}>Username</label>
          <input
            className="form-control"
            type='text'
            name='username'
            placeholder='Enter your username'
            onChange={e => setUsername(e.target.value)} value={username}
          />
          <label style={{color:'white'}}>First Name</label>
          <input
            className="form-control"
            type='text'
            name='firstname'
            placeholder='Enter your first name'
            onChange={e => setFirstname(e.target.value)} value={firstname}
          />
          <label style={{color:'white'}}>Last Name</label>
          <input
            className="form-control"
            type='text'
            name='lastname'
            placeholder='Enter your last name'
            onChange={e => setLastname(e.target.value)} value={lastname}
          />
          <label style={{color:'white'}}>Email</label>
          <input
            className="form-control"
            type='email'
            name='email'
            placeholder='Enter your email'
            onChange={e => setEmail(e.target.value)} value={email}
          />
          <label style={{color:'white'}}>Password</label>
          <input
            className="form-control"
            type='password'
            name='password'
            placeholder='Enter your password'
            onChange={e => setPassword(e.target.value)} value={password}
          />
        <button onClick={signup} className="form-control btn btn-primary" style={{marginTop: '30px'}}>
          Sign up
        </button>
        <span style={{marginTop: '30px', color: 'white'}}>
          Already have an account? Login <Link to='/login' >here</Link>
        </span>
        </form>
      </div>
    </div>
    </div>
  );
};

const SignupComponent = ReactRedux.connect(mapStateToProps, mapDispatchToProps)(SignupForm);

export default SignupComponent;