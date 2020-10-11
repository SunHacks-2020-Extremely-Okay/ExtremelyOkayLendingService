import React from 'react';

function signIn() {

}

function signUp() {

}

function SignUpForm() {
    return (
        <div>
            <form method="post">
                <label className="loginLabel">
                    Email:
                    <input type="email" id="email" />
                </label>
                <label className="loginLabel">
                    Password:
                    <input type="password" id="password" />
                </label>
                <a className="sign" onClick={signIn}>
                    Sign In
                </a>
                <a className="sign" onClick={signUp}>
                    Sign Up
                </a>
            </form>
        </div>
    )
}

export default SignUpForm;