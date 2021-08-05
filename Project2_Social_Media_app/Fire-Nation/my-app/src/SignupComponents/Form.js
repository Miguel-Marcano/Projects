import React, { useState } from 'react';
import './Form.css';
import FormSignup from './FormSignup';
//import FormSuccess from './FormSuccess';
import sample from './fire.mp4';
import { useHistory } from 'react-router-dom'
//import useForm from './useForm';



const Form = () => {
  const [isSubmitted, setIsSubmitted] = useState(false);
  const history = useHistory();

  function submitForm() {
    setIsSubmitted(true);
  }
  return (
    <>
      <div className='back'>
        <h3>Fire Nation App</h3>
        <video className='background-video' autoPlay loop muted>
          <source src={sample} type='video/mp4' />
        </video>
        <div className='form-container'>
          <div className='form-content-left'>
            <img className='form-img' src='https://ih1.redbubble.net/image.866845780.3633/raf,750x1000,075,t,a50120:b8be0d17f7.jpg' alt='firelogo' />
          </div>
          {!isSubmitted ? (
            <FormSignup submitForm={submitForm} />
          ) : (
            history.push('/main')
          )}
        </div>
      </div>
    </>
  );
};

export default Form;