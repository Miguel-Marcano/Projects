//this enum defines what types of actions can be taken b the reducer
export enum ActionType{
    LOGIN = "loginAction",
    SIGNUP = "signupAction",
    SIGN_IN = "loggedReducer",
    VIEWUSER = "viewOtherUser",
    LOGOUT = "logOutAction"
}

//this interface defines the structure of the actions given to the reducer
export interface IAction{
    type: ActionType,
    payload: any
  }
  