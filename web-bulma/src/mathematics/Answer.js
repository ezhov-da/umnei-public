import React, {Component} from 'react';

class Answer extends Component {
    constructor(props){
        super(props);

        this.onClickNumber = this.onClickNumber.bind(this);
        this.onClickClear = this.onClickClear.bind(this);
        this.onClickApply = this.onClickApply.bind(this);

        this.state = {
            answer: '-'
        }
    }

    render(){
        const buttonSuccessClassName = "button is-success " + (this.state.answer === '-' ? "is-static" : "");

        return (
             <div className="box">
                <nav className="level">
                    <div className="level-item">
                        <div className="content is-large">
                            <p>Введите ответ</p>
                        </div>
                    </div>
                </nav>
                <nav className="level">
                    <div className="level-item">
                        <div className="content is-large">
                            <h1>{this.state.answer}</h1>
                        </div>
                    </div>
                </nav>
                <nav className="pagination is-centered" role="navigation" aria-label="pagination">
                    <ul className="pagination-list">
                        <li><a className="pagination-link" aria-label="Goto page 1" onClick={this.onClickNumber}>1</a></li>
                        <li><a className="pagination-link" aria-label="Goto page 1" onClick={this.onClickNumber}>2</a></li>
                        <li><a className="pagination-link" aria-label="Goto page 1" onClick={this.onClickNumber}>3</a></li>
                        <li><a className="pagination-link" aria-label="Goto page 1" onClick={this.onClickNumber}>4</a></li>
                        <li><a className="pagination-link" aria-label="Goto page 1" onClick={this.onClickNumber}>5</a></li>
                        <li><a className="pagination-link" aria-label="Goto page 1" onClick={this.onClickNumber}>6</a></li>
                        <li><a className="pagination-link" aria-label="Goto page 1" onClick={this.onClickNumber}>7</a></li>
                        <li><a className="pagination-link" aria-label="Goto page 1" onClick={this.onClickNumber}>8</a></li>
                        <li><a className="pagination-link" aria-label="Goto page 1" onClick={this.onClickNumber}>9</a></li>
                        <li><a className="pagination-link" aria-label="Goto page 1" onClick={this.onClickNumber}>0</a></li>
                    </ul>
                </nav>
                <nav className="level">
                    <div className="level-item">
                        <div className="buttons is-full">
                            <button className="button is-warning" onClick={this.onClickClear}>Очистить</button>
                            <button className={buttonSuccessClassName} onClick={this.onClickApply}>Ответить</button>
                        </div>
                    </div>
                </nav>
            </div>
        );
    }

    onClickClear(e){
        this.setState({
            answer : '-'
        });
    }

    onClickNumber(e){
        let input = e.currentTarget.textContent;
        let already = this.state.answer;
        let apply = '-' === already ? '' : already;
        this.setState({
            answer : apply + input
        });
    }

    onClickApply(e){
        let answer = this.state.answer;
        if(answer !== '-'){
            this.setState({
                answer : '-'
            });
            this.props.applyAnswer(answer);
        }
    }
}

export default Answer;