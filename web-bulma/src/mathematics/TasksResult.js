import React, {Component} from 'react';
import TasksResultStars from './TasksResultStars';

class TasksResult extends Component {
    constructor(props){
        super(props);

        this.state = {
            hiddenErrors: true
        }

        this.showErrors = this.showErrors.bind(this);
    }

    render(){
        let errorsComponent = null;

        if(this.props.tasksWithErrors.length > 0){
            let errors = this.props.tasksWithErrors;

            let errorsComponentArray = [];

            for(let i = 0; i < errors.length; i++ ){
                let error = errors[i];
                let answer = error.task.answer;
                let userAnswer = error.userAnswer;
                let textTask = <span className="title">
                    <span className={error.task.first === '?' ? 'has-text-danger' : 'has-text-dark'}>{error.task.first === '?' ? userAnswer : error.task.first}</span>
                    <span className="has-text-dark"> </span>
                    <span className="has-text-dark">{error.task.operator}</span>
                    <span className="has-text-dark"> </span>
                    <span className={error.task.second === '?' ? 'has-text-danger' : 'has-text-dark'}>{error.task.second === '?' ? userAnswer : error.task.second}</span>
                    <span className="has-text-dark"> = </span>
                    <span className={error.task.third === '?' ? 'has-text-danger' : 'has-text-dark'}>{error.task.third === '?' ? userAnswer : error.task.third}</span>
                    </span>;


                errorsComponentArray.push(
                    <nav key={'_' + Math.random().toString(36).substr(2, 9)} className="level">
                      <div className="level-item has-text-centered">
                        <div>
                          <p className="heading">Пример</p>
                          <p className="title">{textTask}</p>
                        </div>
                      </div>
                      <div className="level-item has-text-centered">
                        <div>
                          <p className="heading">Правильный ответ</p>
                          <p className="title has-text-success">{answer}</p>
                        </div>
                      </div>
                    </nav>
                );
            }

            errorsComponent = <div className="container">
                <nav className="level">
                    <div className="level-item has-text-centered">
                        <div hidden={!this.state.hiddenErrors}>
                            <a className="button" onClick={this.showErrors} >Посмотреть ошибки</a>
                        </div>
                    </div>
                </nav>
                <div className="container animated flipInX" hidden={this.state.hiddenErrors}>
                    {errorsComponentArray}
                </div>
             </div>
        }

        return (
            <section className="section">
                 <div className="container">
                     <h1 className="title">Результат</h1>
                     <div className="box">
                        <nav className="level">
                          <div className="level-item has-text-centered">
                            <div>
                              <p className="title">{this.props.correctAnswerNumber} из {this.props.allCountTasks}</p>
                            </div>
                          </div>
                        </nav>

                        <TasksResultStars {...this.props}/>

                        <nav className="level">
                          <div className="level-item">
                            <div className="buttons">
                                <button className="button is-success" onClick={this.props.onClickComplete}>Создать новые примеры</button>
                            </div>
                          </div>
                        </nav>

                        {errorsComponent != null ? errorsComponent : ''}

                     </div>
                 </div>
            </section>
        );
    }

    showErrors(){
        this.setState({
            hiddenErrors: false
        });
    }
}


export default TasksResult;