import React, {Component} from 'react';

class CountTask extends Component{
    constructor(props){
        super(props);

        this.increment = this.increment.bind(this);
        this.decrement = this.decrement.bind(this);
    }

    render (){
        return (
            <nav className="pagination is-centered" role="navigation" aria-label="pagination">
                <a className="pagination-previous" onClick={this.decrement}>&lt;</a>
                <ul className="pagination-list">
                    <li><a className="title">{this.props.countTasks}</a></li>
                </ul>
                <a className="pagination-next" onClick={this.increment}>&gt;</a>
            </nav>
        );
    }

    increment(){
        let current = this.props.countTasks;
        current++;
        if(current <= 99){
            this.props.applyCount(current);
        }
    }

    decrement(){
        let current = this.props.countTasks;
        current--;
        if(current > 0){
            this.props.applyCount(current);
        }
    }
}

export default CountTask;