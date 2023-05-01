import React, {Component} from 'react';

class UseNumbers extends Component{
    constructor(props){
        super(props);

        this.state = {
            min: this.props.min,
            max: this.props.max
        };

        this.onSelectMinValue = this.onSelectMinValue.bind(this);
        this.onSelectMaxValue = this.onSelectMaxValue.bind(this);
        this.applyOnChange = this.applyOnChange.bind(this);
    }

    render(){
        let arrayMin = [];
        for(let i = 0; i <=100; i+= 10){
            let option = <option key={'_' + Math.random().toString(36).substr(2, 9)}>{i}</option>;
            arrayMin.push(option);
        }

        let arrayMax = [];
        for(let i = 10; i <=100; i+= 10){
            let option = <option key={'_' + Math.random().toString(36).substr(2, 9)}>{i}</option>;

            arrayMax.push(option);
        }


        return (
            <div className="container">
                <div className="panel-block">
                    <div className="columns is-mobile">
                        <div className="column  is-5">
                            <p className="subtitle">
                                <strong>ОТ</strong>
                            </p>
                        </div>
                        <div className="column">
                            <div className="select">
                                <select value={this.state.min} onChange={this.onSelectMinValue}>
                                    {arrayMin}
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="panel-block">
                    <div className="columns is-mobile">
                        <div className="column is-5">
                            <p className="subtitle">
                                    <strong>ДО</strong>
                            </p>
                        </div>
                        <div className="column">
                            <div className="select">
                                <select value={this.state.max}  onChange={this.onSelectMaxValue} className="select">
                                    {arrayMax}
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

    onSelectMinValue(e){
        let min = e.target.value;
        let max = this.state.max;

        this.applyOnChange(min, max);

        this.setState({
            min: min
        });

    }

    onSelectMaxValue(e){
        let min = this.state.min;
        let max = e.target.value;

        this.applyOnChange(min, max);

        this.setState({
            max: e.target.value
        });
    }

    applyOnChange(min, max){
        this.props.applyOnChange(min, max);
    }

}

export default UseNumbers;