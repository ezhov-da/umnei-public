import React, {Component} from 'react';

class Task extends Component {
    constructor(props){
        super(props);
    }

    render(){
        let task = this.props.task;
        let taskText = task.first + ' ' + task.operator + ' ' + task.second + ' = ' + task.third;
        return (
            <nav className="level">
                <div className="level-item">
                    <div className="content is-large">
                        <h1>{taskText}</h1>
                    </div>
                </div>
            </nav>
        );
    }
}

export default Task;