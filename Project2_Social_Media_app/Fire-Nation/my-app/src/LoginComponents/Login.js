import React, { useState } from 'react';
import LoginForm from './LoginForm';

function Log() {
    const adminUser = {
        username: '',
        password: ''
    }

    const [user, setUser] = useState({username: "", password: ""});
    const [error, setError] = useState("");

    const Login = details => {
        console.log(details);

        if (details.username == adminUser.username && details.password == adminUser.password) {
            console.log("logged in");
        } else {
            console.log("details not match");
        }
    }

    const Logout = () => {
        console.log("Logout")
    }
}

export default Log;