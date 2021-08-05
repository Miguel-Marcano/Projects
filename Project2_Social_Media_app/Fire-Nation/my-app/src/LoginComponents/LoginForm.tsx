import React, { useState, SyntheticEvent } from 'react';
import { Dispatch } from "react";
import { ActionType, IAction } from "../redux/reducer-logic/ActionStructure";
// import * as Redux from 'redux';
import * as ReactRedux from 'react-redux';
import axios from 'axios';
import './Login.css';
import {Link, useHistory} from 'react-router-dom';
import sample from './fire.mp4';
import { IUser } from '../redux/state-logic/StateStructure';
import { useSelector } from 'react-redux';
import { State } from '../redux/state-logic/Store';
import loggedReducer from '../redux/reducer-logic/isLogged';

const mapDispatchToProps = (dispatch: Dispatch<IAction>) => {
    //let's extract only the functionality we need to use
    return {
        onLogin: (incomingPayload: any) => { dispatch({ type: ActionType.LOGIN, payload: incomingPayload }) },
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


const LoginForm: React.FunctionComponent<IProps> = (props: IProps) => {
    const logged = useSelector((state: State) => state.isLogged);
    const history = useHistory();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    
    const logIn = async (e: SyntheticEvent) => {
        e.preventDefault();
        
        const axiosPayLoad: any = await axios.post('http://localhost:8080/project2hibernate/api/logincontrol/login', {
            "userName": username,
            "passWord": password
        });
        console.log("Help")
        const axiosData: any = axiosPayLoad.data;
        
        console.log("axiosdata: ", axiosData);
        // setPokemonData({"name": axiosData.name, "image": axiosData.sprites.front_default});
        if (axiosData.userName != null)
        {
            props.onLogin(axiosData);
            history.push('/main');
            //axiosData.firstName
        }
    }

    const eml = () => {
        history.push('/recoverPassword');
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
                    <h2 style={{marginTop:'25px', textAlign:'center', color:'white'}}>Login</h2>
                    <form className="row" style={{margin: "25px 85px 75px 100px"}}>
                        <label htmlFor="username" style={{color:'white'}}>username</label>
                        <input type="text" className="form-control" name="username" id="username" onChange={e => setUsername(e.target.value)} value={username}/>
                        
                        <label htmlFor="password" style={{color:'white'}}>password</label>
                        <input type="password" className="form-control" name="password" id="password" onChange={e => setPassword(e.target.value)} value={password}/>
                        
                        <button onClick={logIn} className="form-control btn btn-primary" style={{marginTop: '30px'}}>Login</button>
                        <button onClick={eml} className="form-control btn btn-primary" style={{marginTop: '30px'}}>Forgot Password?</button>
                    </form>
                </div>
            </div>
        </div>
 
    )
}

export const LoginComponent = ReactRedux.connect(mapStateToProps, mapDispatchToProps)(LoginForm);