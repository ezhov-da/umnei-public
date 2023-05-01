import React,{Component} from 'react';

class LinkModal extends Component{
    constructor(props){
        super(props);

        this.close = this.close.bind(this);
    }

    render(){
        return (
            <section className="section">
                 <div className="container">
                        <nav className="level">
                            <div className="level-item has-text-centered">
                                <div className="content">
                                    <div className="modal is-active">
                                        <div className="modal-background" onClick={this.close}></div>
                                            <div className="modal-content">
                                            <article className="message">
                                                <div className="message-body">
                                                    <a href={this.props.link}>{this.props.linkName}</a>
                                                </div>
                                            </article>
                                        </div>
                                        <button className="modal-close is-large" aria-label="close" onClick={this.close}></button>
                                    </div>
                                </div>
                            </div>
                        </nav>
                 </div>
             </section>
        );
    }

    close(){
        this.props.close();
    }
}

export default LinkModal;