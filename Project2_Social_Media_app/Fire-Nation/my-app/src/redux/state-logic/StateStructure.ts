
//MY APPLICATION'S STATE MODEL
export interface IAppState {
    currentUser: IUser | null;
    reimbList: IReimbursement[] | null;
    otherUser: IOtherUser | null;
}

//SUPPORTING STATE MODELS
export interface IUser{
    userId: number;
    email: string;
    firstName: string;
    lastName: string;
    passWord: string;
    picture: string;
    background: string;
    userName: string;
}


export interface IPost{
    postId: number;
    timePosted: string;
    postContent: string;
    postLikes: number;
    postDislikes: number;
    postUser: string;
}

export interface IOtherUser{
    value: string;
    label: string;
}

export interface IReimbursement{
    reimbId: number;
    reimbAmount: number;
    reimbDescrption: string;
    reimbStatus: string;
    reimAuthor: IUser;
    reimResolver: IUser;
}

/////////INITIAL STATE, this is the beginning state we'll give to the reducer upon startup
export const initialState: IAppState = {
    currentUser: null,
    reimbList: null,
    otherUser: null
}