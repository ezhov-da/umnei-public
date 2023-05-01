import React from 'react';

import {HashRouter, Route} from "react-router-dom";

import MathematicsContainer from './mathematics/MathematicsContainer';

function App() {
    return (
        <HashRouter>
            <div className="container">
                <nav className="navbar" role="navigation" aria-label="main navigation">
                    <div className="navbar-brand">
                        <a className="navbar-item" href="/">
                            <img src="favicon.ico" width="32" height="32"/>
                            <h6 className="title is-6 logo-title">умней</h6>
                        </a>

                        <a role="button" className="navbar-burger burger" aria-label="menu" aria-expanded="false"
                           data-target="navbarBasicExample">
                            <span aria-hidden="true"></span>
                            <span aria-hidden="true"></span>
                            <span aria-hidden="true"></span>
                        </a>
                    </div>

                    <div id="navbarBasicExample" className="navbar-menu">
                        <div className="navbar-start"></div>
                    </div>
                </nav>

                <Route exact path="/" component={MathematicsContainer}/>
            </div>
        </HashRouter>
    );
}

export default App;
