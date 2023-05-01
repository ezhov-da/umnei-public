import React, {Component} from 'react';

class TasksResultStars extends Component {
    constructor(props){
        super(props);

        this.calculatePercent = this.calculatePercent.bind(this);
        this.createArrayStars = this.createArrayStars.bind(this);
    }

    render(){
        let arrayStars = this.createArrayStars(this.calculatePercent());
        return (
            <nav className="level animated tada delay-6s">
              <div className="level-item has-text-centered">
                    {arrayStars}
              </div>
            </nav>
        );
    }

    calculatePercent(){
        let percent = this.props.correctAnswerNumber * 100 / this.props.allCountTasks;
        return percent;
    }

    createArrayStars(percent){
        let countGoldStar;

        if(percent === 0){
            countGoldStar = 0;
        } else if(percent >= 1 && percent <= 20){
            countGoldStar = 1;
        } else if(percent >= 21 && percent <= 40){
            countGoldStar = 2;
        } else if(percent >= 41 && percent <= 60){
            countGoldStar = 3;
        } else if(percent >= 61 && percent <= 80){
            countGoldStar = 4;
        } else {
            countGoldStar = 5;
        }

        let delay = 1;

        let array = [];
        for(let i = 0; i < countGoldStar; i++){
            let classFigure = "image is-32x32 animated fadeIn delay-" + delay + "s";

            array.push(
                <figure key={'_' + Math.random().toString(36).substr(2, 9)} className={classFigure}>
                    <img src="/image/star-gold-32.png"/>
                </figure>
            );

            delay++;
        }

        if(array.length < 5){
            let diff = 5 - array.length;
            for(let i = 0; i < diff; i++){
                let classFigure = "image is-32x32 animated fadeIn delay-" + delay + "s";

                array.push(
                    <figure key={Date.now() + '' + i} className={classFigure}>
                        <img src="/image/star-gray-32.png"/>
                    </figure>
                );

                delay++;
            }
        }

        return array;
    }

    shouldComponentUpdate(newProps, newState){
        return false;
    }
}


export default TasksResultStars;