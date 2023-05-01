import React, {Component} from 'react';

import CountTask from './CountTask';
import UseNumbers from './UseNumbers';

const TYPE_NUMBER_ESCAPED = 'numberEscaped';
const TYPE_MATH_EXERCISE = 'mathExercise';

class Generator extends Component {
    constructor(props){
        super(props);

        this.state = {
            whatType: TYPE_NUMBER_ESCAPED,
            addition: false,
            subtraction: false,
            multiplication: false,
            division: false,
            countTasks: 10,

            minUseValue: 10,
            maxUseValue: 20,
        };

        this.generate = this.generate.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleChangeCheckbox = this.handleChangeCheckbox.bind(this);
        this.onChangeCountTasks = this.onChangeCountTasks.bind(this);
        this.applyOnChange = this.applyOnChange.bind(this);
        this.catchLink = this.catchLink.bind(this);
        this.createGenerateObject = this.createGenerateObject.bind(this);
    }

    render(){
        return (
            <section className="section">
                <div className="container">
                    <h1 className="title">Создать примеры</h1>
                    <div className="tile is-ancestor">
                        <div className="tile is-4 is-vertical is-parent">
                            <div className="tile is-child box">
                                <p className="title ">Тип задачи</p>
                                <div className="control">
                                    <label className="panel-block">
                                        <input type="radio" name="foobar" value={TYPE_NUMBER_ESCAPED} checked={this.state.whatType === TYPE_NUMBER_ESCAPED} onChange={this.handleChange}/>
                                        Сбежавшее число
                                    </label>
                                    <label className="panel-block">
                                        <input type="radio" name="foobar" value={TYPE_MATH_EXERCISE} checked={this.state.whatType === TYPE_MATH_EXERCISE} onChange={this.handleChange}/>
                                        Математические примеры
                                    </label>
                                </div>
                            </div>
                            <div className="tile is-child box">
                                <p className="title">Количество примеров</p>
                                <label className="panel-block">
                                    <CountTask countTasks={this.state.countTasks} applyCount={this.onChangeCountTasks}/>
                                </label>
                            </div>
                        </div>
                        <div className="tile is-parent">
                            <div className="tile is-child box">
                                <p className="title">Действие</p>
                                <div className="control">
                                    <label className="panel-block">
                                        <input type="checkbox" name="operator" onChange={this.handleChangeCheckbox} value="addition"/>
                                        Сложение
                                    </label>
                                    <label className="panel-block">
                                        <input type="checkbox" name="operator" onChange={this.handleChangeCheckbox} value="subtraction"/>
                                        Вычитание
                                    </label>
                                    <label className="panel-block">
                                        <input type="checkbox" name="operator" onChange={this.handleChangeCheckbox} value="multiplication"/>
                                        Умножение
                                    </label>
                                    <label className="panel-block">
                                        <input type="checkbox" name="operator" onChange={this.handleChangeCheckbox} value="division"/>
                                        Деление
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div className="tile is-parent">
                            <div className="tile is-child box">
                                <p className="title">Используемые числа</p>
                                <UseNumbers min={this.state.minUseValue} max={this.state.maxUseValue} applyOnChange={this.applyOnChange}/>
                            </div>
                        </div>
                    </div>
                    <div className="columns is-desktop is-one-fifth">
                        <div className="column">
                            <button className="button is-link is-outlined is-fullwidth" onClick={this.generate}>
                                Начать
                            </button>
                        </div>
                        <div className="column"></div>
                        {/*<div className="column">*/}
                        {/*    <button className="button is-fullwidth" onClick={this.catchLink}>*/}
                        {/*        Получить ссылку для печати*/}
                        {/*    </button>*/}
                        {/*</div>*/}
                    </div>

                </div>
            </section>
        );
    }

    handleChangeCheckbox(e){
        var name = e.target.value;
        if("addition" === name ){
                this.setState({
                    addition: e.target.checked
                })
        } else if("subtraction" === name){
                this.setState({
                    subtraction: e.target.checked
                })
        } else if("multiplication" === name){
                this.setState({
                    multiplication: e.target.checked
                })
        } else if("division" === name){
                this.setState({
                    division: e.target.checked
                })
        }
    }

    handleChange(e){
        this.setState({
            whatType: e.target.value
        })
    }

    onChangeCountTasks(value){
        this.setState({
            countTasks: value
        });
    }

    createGenerateObject(){
        var generateObject = {};
        generateObject.type = this.state.whatType ===  TYPE_NUMBER_ESCAPED ? TYPE_NUMBER_ESCAPED : TYPE_MATH_EXERCISE;
        generateObject.actions = [];
        generateObject.countTasks = this.state.countTasks;
        generateObject.minUseValue = this.state.minUseValue;
        generateObject.maxUseValue = this.state.maxUseValue;

        if(this.state.addition){
            generateObject.actions.push('addition');
        }
        if(this.state.subtraction){
            generateObject.actions.push('subtraction');
        }
        if(this.state.multiplication){
            generateObject.actions.push('multiplication');
        }
        if(this.state.division){
            generateObject.actions.push('division');
        }

        return generateObject;
    }

    generate(){
        var generateObject = this.createGenerateObject();
        this.props.generate(generateObject);
    }

    catchLink(){
        var generateObject = this.createGenerateObject();
        this.props.catchLink(generateObject);
    }

    applyOnChange(min, max){
        this.setState({
            minUseValue: min,
            maxUseValue: max,
        });
    }
}

export default Generator;