import React, { useState, SyntheticEvent } from 'react';
import axios from 'axios';
import sample from './fire.mp4';
import './recoverPass.css';
import {Link, useHistory} from 'react-router-dom';

const RecoverPass = () => {
    const history = useHistory();
    const [password, setPassword] = useState("");
    const [username, setUsername] = useState("");
    const [token, setToken] = useState("");

    const recover = async (e: SyntheticEvent) => {
        e.preventDefault();
        console.log(token);
        const axiosPayLoad: any = await axios.post('http://localhost:8080/project2hibernate/api/usercontrol/updatePassword', {
            "userName": username,    
            "passWord": password,
            "resetToken": token
        });
        console.log(axiosPayLoad);
        console.log("Help")
        const axiosData: any = axiosPayLoad.data;
        
        console.log("axiosdata: ", axiosData);
        if (axiosData){
            history.push('/login');
        }
        
        
    }

    return (
        <div>
             <video className='background-video' autoPlay loop muted>
                <source src={sample} type='video/mp4' />
          </video>
        <div 
            className="container border"
            style={{marginTop: "50px",
                width: '50%',
                backgroundImage: `url('https://wallpapercave.com/wp/OhiaJoY.jpg')`,
                backgroundPosition: 'center',
                backgroundSize: 'cover',
                
            }}
        >
            <h1 style={{marginTop:'25px', textAlign:'center', color:'white'}}>Enter your new Password</h1>
            <form className="row" style={{margin: "25px 85px 75px 100px"}}
            >
                <label htmlFor="username" style={{color:'white'}}>username</label>
                <input type="text" className="form-control" name="username" id="username" onChange={e => setUsername(e.target.value)} value={username}/>

                

                <label style={{color:'white'}}>New Password</label>
                <input type="email" name="user_email" className="form-control"
                onChange={e => setPassword(e.target.value)} value={password}/>

                <label style={{color:'white'}}>Reset Token</label>
                <input type="text" className="form-control" name="token" id="token" onChange={e => setToken(e.target.value)} value={token}/>

                <input type="submit" value="Update" className="form-control btn btn-primary"
                style={{marginTop: '30px'}}
                onClick={recover}
                />
            </form>
        </div>
        </div>
    );
};

export default RecoverPass;