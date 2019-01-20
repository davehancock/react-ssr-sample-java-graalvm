import React from 'react';

import ssrImg from "../../public/images/java-server-side-render.jpg"
import serverImg from "../../public/images/server-side-render.jpg"
import javaImg from "../../public/images/ssr-static-image-server-side-rendering.jpg"

class Static extends React.Component {

    render() {
        return (
            <div>
                <section className="jumbotron home text-center">
                    <div className="container brand-text">
                        <h1 className="jumbotron-heading">Some Static Content</h1>
                    </div>
                </section>

                <div className="container">
                    <div className="row">
                        <figure className="col-lg-4">
                            <img src={ssrImg} alt="ssr server"/>
                        </figure>
                        <figure className="col-lg-4">
                            <img src={serverImg} alt="server side"/>
                        </figure>
                        <figure className="col-lg-4">
                            <img src={javaImg} alt="java server side rendering"/>
                        </figure>
                    </div>
                </div>
            </div>
        )
    }
}

export default Static
