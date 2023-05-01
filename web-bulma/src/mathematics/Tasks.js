import React, {Component} from 'react';

import Answer from './Answer';
import Task from './Task';
import TasksResult from './TasksResult';

class Tasks extends Component {
    constructor(props){
        super(props);

        this.state = {
            currentTaskNumber: 0,
            complete: false,
            correctAnswerNumber: 0,
            tasksWithErrors: []
        }

        this.applyAnswer = this.applyAnswer.bind(this);
        this.fireComplete = this.fireComplete.bind(this);
    }

    render(){
        let tasksComponent;

        if(this.state.complete){
            tasksComponent = <TasksResult
                                correctAnswerNumber={this.state.correctAnswerNumber}
                                allCountTasks={this.props.tasks.length}
                                onClickComplete={this.fireComplete}
                                tasksWithErrors={this.state.tasksWithErrors}
                                />;
        } else if (this.props.tasks.length > 0){

            let currentTask = this.props.tasks[this.state.currentTaskNumber];

            tasksComponent = <section className="section">
                 <div className="container">
                    <nav className="level">
                        <div className="level-left">
                            <div className="level-item">
                                <h1 className="title">Пример {this.state.currentTaskNumber + 1} / {this.props.tasks.length}</h1>
                            </div>
                        </div>

                        <div className="level">
                            <div className="level-item">
                                <progress className="progress is-success" value={this.state.currentTaskNumber + 1} max={this.props.tasks.length}></progress>
                            </div>
                        </div>

                        <div className="level-right">
                            <p className="level-item"><a className="button" onClick={this.fireComplete}>Завершить досрочно</a></p>
                        </div>
                    </nav>

                     <div className="box">
                         <nav className="pagination is-centered" role="navigation" aria-label="pagination">
                             <ul className="pagination-list">
                                <Task task={currentTask}/>
                             </ul>
                         </nav>
                     </div>

                     <Answer applyAnswer={this.applyAnswer}/>

                 </div>
             </section>;
        } else {
            tasksComponent = <section className="section">
                 <div className="container">
                        <nav className="level">
                            <div className="level-item has-text-centered">
                                <div className="content">
                                    <div className="modal is-active">
                                        <div className="modal-background" onClick={this.fireComplete}></div>
                                            <div className="modal-content">
                                            <article className="message">
                                                <div className="message-body">
                                                    Похоже, мы не подобрали примеров по выбранным параметрам, попробуй изменить их!
                                                </div>
                                            </article>
                                        </div>
                                        <button className="modal-close is-large" aria-label="close" onClick={this.fireComplete}></button>
                                    </div>
                                </div>
                            </div>
                        </nav>
                 </div>
             </section>;
        }

        return (
            tasksComponent
        );
    }

    applyAnswer(answer){
        let currentTask = this.props.tasks[this.state.currentTaskNumber];
        if(currentTask !== undefined){
            let taskAnswer = currentTask.answer;

            let correctAnswerNumber = this.state.correctAnswerNumber;
            if(answer === taskAnswer){
                correctAnswerNumber++;
            } else {
                let tasksWithErrors = this.state.tasksWithErrors;
                tasksWithErrors.push({
                    task: currentTask,
                    userAnswer: answer
                });

                this.setState({
                    tasksWithErrors: tasksWithErrors
                });
            }

            let currentTaskNumber = this.state.currentTaskNumber;
            currentTaskNumber++;

            if(currentTaskNumber !== this.props.tasks.length){
                let newCurrentTask = this.props.tasks[currentTaskNumber];

                this.setState({
                    currentTaskNumber: currentTaskNumber,
                    currentTask: newCurrentTask,
                    correctAnswerNumber: correctAnswerNumber
                });
            } else {
                this.setState({
                    complete: true,
                    correctAnswerNumber: correctAnswerNumber
                });
            }
        }
    }

    fireComplete(){
        this.props.fireComplete();
    }
}

export default Tasks;