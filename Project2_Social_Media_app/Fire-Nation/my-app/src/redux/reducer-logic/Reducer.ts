import { IAppState, initialState } from "../state-logic/StateStructure";
import { ActionType, IAction } from "./ActionStructure";


//THIS IS OUR REDUCER FUNCTION
//it is basically a switch case calling different functinalities
export const myReducer = (model: IAppState = initialState, action: IAction): IAppState => {
  
  switch (action.type) {
    case ActionType.LOGIN:
      return loginAction(model, action);
    case ActionType.SIGNUP:
      return SignupAction(model, action);
    case ActionType.VIEWUSER:
      return ViewUserAction(model, action);
    case ActionType.LOGOUT:
        return LogOutAction(model, action);
    default: 
      return model;
  }

  return model;
}

/////////LET'S DEFINE THE LOGIC FOR EACH ACTION; HOW DOES IT MODIFY THE MODEL? 
//remember, you need to return a complete model from these, the state is immutable so you
//need to replace the ENTIRE state model
const loginAction = (model: IAppState, action: IAction) => {
  return Object.assign({}, model, { currentUser: action.payload });
}

const SignupAction = (model: IAppState, action: IAction) => {
  return Object.assign({}, model, { currentUser: action.payload });
}

const ViewUserAction = (model: IAppState, action: IAction) => {
  return Object.assign({}, model, { otherUser: action.payload });
}

const LogOutAction = (model: IAppState, action: IAction) => {
  return Object.assign({}, null, { currentUser: action.payload });
}
