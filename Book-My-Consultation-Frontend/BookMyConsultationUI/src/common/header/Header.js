import React, { useState } from "react";
import './Header.css';
import image from '../../assets/logo.jpeg';
import { Button } from '@material-ui/core';
import LoginModal from "../../screens/login/Login";
import RegisterModal from "../../screens/register/Register";

const Header = () => {
    const [isLoginModalOpen, setIsLoginModalOpen] = useState(false);
    const [isRegisterModalOpen, setIsRegisterModalOpen] = useState(false);

    const handleLoginClick = () => {
        setIsLoginModalOpen(true);
        setIsRegisterModalOpen(false);
    };

    const handleRegisterClick = () => {
        setIsRegisterModalOpen(true);
        setIsLoginModalOpen(false);
    };

    const handleModalClose = () => {
        setIsLoginModalOpen(false);
        setIsRegisterModalOpen(false);
    };
  
    return (
        <div>
            <div className="header">
            <img src={image} className="image"></img>
            <h2 className="heading">Doctor Finder</h2>
            <Button id="login" onClick={handleLoginClick} style={{
                        marginLeft: 1438,
                        marginTop: -95
                    }}  
                    variant="contained" color="primary">
                    Login
            </Button>
        </div>
        {(isLoginModalOpen || isRegisterModalOpen) && (
        <div className="header-modal">
            <div className="header-modal-content">
                <div className="header-auth-container">
                    <div className="header-auth-header">
                        <h5>Authentication</h5>
                    </div>
                    <div className="header-auth-options">
                        <button onClick={handleLoginClick}>Login</button>
                        <button onClick={handleRegisterClick}>Register</button>
                    </div>
                </div>
                {isLoginModalOpen && <LoginModal onClose={handleModalClose} />}
                {isRegisterModalOpen && <RegisterModal onClose={handleModalClose} />}
            </div>
        </div>
        )}
        </div>
    );
}

export default Header;
