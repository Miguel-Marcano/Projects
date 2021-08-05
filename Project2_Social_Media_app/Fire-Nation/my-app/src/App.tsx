import React from 'react';
import './App.css';
import { LoginComponent } from './LoginComponents/LoginForm';
import { IAction } from './redux/reducer-logic/ActionStructure';
import Form from './SignupComponents/Form';
import SignupComponents from './SignupComponents/FormSignup';
import Mailer from './Mail/mailer';
import { profileComponent } from './profileComponents/Profile';
import otherProfileComponent from './profileComponents/OtherProfile';
import { Dispatch } from "react";
// import * as Redux from 'redux';
import * as ReactRedux from 'react-redux';
import { IUser } from './redux/state-logic/StateStructure';
//import 'bootstrap/dist/css/bootstrap.min.css';

import MainMenu from "./MainPageComponents/MainMenu";
import { BrowserRouter, Route } from 'react-router-dom';
import RecoverPass from "./RecoverPassword/recoverPass"

const mapStateToProps = (state: any) => {
  //logic goes here
  //said logic will extract the NECESSARY state you need, to simplify the component

  return {
    seekUser: state.myStateReducer.currentUser
  }
}

const mapDispatchToProps = (dispatch: Dispatch<IAction>) => {
  //let's extract only the functionality we need to use
  return {
  }
}


interface IProps {
  seekUser?: IUser|null
}


//THIS is our ACTUAL component
//this is also the callback function that will be given to the "Redux.connect" function below
function CoreApp(props: IProps) {
  return (
    <>
          <BrowserRouter>
            <Route path="/" exact component={SignupComponents} />
            <Route path="/login" component={LoginComponent} /> 
            <Route path="/main" component={(props.seekUser?.userName == null) ? LoginComponent : MainMenu} /> 
            <Route path="/recoverPassword" component={Mailer} /> 
            <Route path="/resetPassword" component={RecoverPass}/>
            <Route path="/profile" component={(props.seekUser?.userName == null) ? LoginComponent : profileComponent} /> 
            <Route path="/otherprofile" component={(props.seekUser?.userName == null) ? LoginComponent : otherProfileComponent} /> 
          </BrowserRouter>
           
    </>
  );
}

/*
  THIS the redux wrapper for our component
*/
export const App = ReactRedux.connect(mapStateToProps, mapDispatchToProps)(CoreApp);


export default App;

