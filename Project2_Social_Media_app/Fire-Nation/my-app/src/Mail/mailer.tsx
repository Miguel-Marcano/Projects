import sample from './fire.mp4';
import './mail.css'
import axios from 'axios';
import React, { useState, SyntheticEvent } from 'react';

const Mailer = () => {

    const [email, setEmail] = useState("");

    const mail = async (e: SyntheticEvent) => {
        e.preventDefault();

        const axiosPayLoad: any = await axios.post('http://localhost:8080/project2hibernate/api/usercontrol/checkEmail', {
            "email": email,
        });

        console.log("Help")
        const axiosData: any = axiosPayLoad.data;
        
        if (axiosData){
            const axiosPayLoad2: any = await axios.post('http://localhost:8080/project2hibernate/api/logincontrol/recoverPassword', {
                "email": email,
            });
            console.log("Help2 sending the email")
            const axiosData2: any = axiosPayLoad2.data;
        
            console.log("axiosdata: ", axiosData2);
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
            <h1 style={{marginTop:'25px', textAlign:'center', color:'white'}}>Recover your Password</h1>
            <form className="row" style={{margin: "25px 85px 75px 100px"}}
            >
                {/* <label style={{color:'white'}}>Username</label>
                <input type="text" name="name" className="form-control"/> */}

                <label style={{color:'white'}}>Email</label>
                <input type="email" name="user_email" className="form-control"
                onChange={e => setEmail(e.target.value)} value={email}/>

                <input type="submit" value="Send" className="form-control btn btn-primary"
                style={{marginTop: '30px'}}
                onClick={mail}
                />
            </form>
        </div>
        </div>
    );
};

export default Mailer;