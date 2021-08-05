import React, { useState, SyntheticEvent } from 'react';
import { Dispatch } from "react";
import { ActionType, IAction } from "../../redux/reducer-logic/ActionStructure";
// import * as Redux from 'redux';
import * as ReactRedux from 'react-redux';
import axios from 'axios';
//npm install bootstrap before using this
//import 'bootstrap/dist/css/bootstrap.min.css';


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
        onLogin: (incomingPayload: any) => { dispatch({ type: ActionType.LOGIN, payload: incomingPayload }) },
    }
}


interface IProps {
    onLogin: any
}

//THIS is our ACTUAL component
//this is also the callback function that will be given to the "Redux.connect" function below
const CoreLoginComponent: React.FunctionComponent<IProps> = (props: IProps) => {

    const [usernameState, setUsernameState] = useState("");
    const [passwordState, setPasswordState] = useState("");

    const axiosFetcher = async (eve: SyntheticEvent) => {

        eve.preventDefault();

        const axiosPayLoad: any = await axios.post('http://localhost:8080/project2hibernate/api/logincontrol/login', {
            "userName": usernameState,
            "passWord": passwordState
        });
        console.log("Help")
        const axiosData: any = axiosPayLoad.data;

        console.log("axiosdata: ", axiosData);

        // setPokemonData({"name": axiosData.name, "image": axiosData.sprites.front_default});
        if (axiosData.ersUsername != null)
            props.onLogin(axiosData);
    }

    return (
        <>
            <div className="container-fluid bg">

                <div className="row">
                    <div className="col-md-4 col-sm-4 col-xs-12"></div>
                    <div className="col-md-4 col-sm-4 col-xs-12">
                        <form className="form-container backgroundcolor">
                            <div className="form-group">
                                <h2>User Login</h2><br/>
                                <label htmlFor="usernameId">Username</label> <input type="text" id="usernameId" name="username" value={usernameState}
                                    placeholder="username" className="form-control" onChange={(eve) => { setUsernameState(eve.target.value); }} />
                            </div><br/>
                            <div className="form-group">
                                <label htmlFor="passwordId">Password</label>
                                <input type="text" id="passwordId" name="password" value={passwordState}
                                    placeholder="password" className="form-control" onChange={(eve) => { setPasswordState(eve.target.value); }} />
                            </div><br /><br/>
                            <div className="d-grid gap-2">
                                <button onClick={axiosFetcher} className="btn btn-success">Login (using Axios)</button>
                            </div>
                        </form>
                    </div>
                    <div className="col-md-4 col-sm-4 col-xs-12"></div>
                </div>
            </div>
        </>
    );
}

/*
  THIS the redux wrapper for our component
*/
export const LoginComponent = ReactRedux.connect(mapStateToProps, mapDispatchToProps)(CoreLoginComponent);

