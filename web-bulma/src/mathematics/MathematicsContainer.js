import React, {Component} from 'react';

import Generator from './Generator';
import Tasks from './Tasks';
import LinkModal from './LinkModal';

var xhr;

class MathematicsContainer extends Component {
    constructor(props){
        super(props);

        this.state = {
            begin: false,
            tasks: [],
            showLink: false,
            link: ''
        }

        this.generate = this.generate.bind(this);
        this.processRequest = this.processRequest.bind(this);
        this.complete = this.complete.bind(this);
        this.catchLink = this.catchLink.bind(this);
        this.processCatchLinkRequest = this.processCatchLinkRequest.bind(this);
    }
    render (){
        if(this.state.showLink){
            return (<LinkModal link={this.state.link} close={this.complete} linkName={'Скачать "Математические примеры"'}/>);
        } else {
            return (this.state.begin ? <Tasks tasks={this.state.tasks} fireComplete={this.complete}/> : <Generator generate={this.generate} catchLink={this.catchLink}/>);
        }
    }

    generate(generateObject){
        var params = 'type=' + generateObject.type +
                        '&actions=' + generateObject.actions.join(',') +
                        '&countTasks=' + generateObject.countTasks +
                        '&from=' + generateObject.minUseValue +
                        '&to=' + generateObject.maxUseValue;
        xhr = new XMLHttpRequest();
        var url;
        var isLocal = (window.location.hostname === 'localhost' && window.location.port === '3000');
        if(isLocal){
            url = 'example.json' + '?' + params;
        } else {
            url = 'tasks/generate' + '?' + params;
        }

        xhr.open('GET', url, true);
        xhr.send();

        xhr.addEventListener('readystatechange', this.processRequest, false);
    }

   processRequest(){
        if(xhr.readyState === 4 && xhr.status === 200){
            var response = JSON.parse(xhr.responseText);
            this.setState({
                begin: true,
                tasks: response.tasks
            });
        }
    }


    catchLink(generateObject){
        var params = 'type=' + generateObject.type +
                        '&actions=' + generateObject.actions.join(',') +
                        '&from=' + generateObject.minUseValue +
                        '&to=' + generateObject.maxUseValue;
        xhr = new XMLHttpRequest();
        var url;
        var isLocal = (window.location.hostname === 'localhost' && window.location.port === '3000');
        if(isLocal){
            url = 'image-link.json' + '?' + params;
        } else {
            url = '/image-generator/tasks/generate' + '?' + params;
        }

        xhr.open('GET', url, true);
        xhr.send();

        xhr.addEventListener('readystatechange', this.processCatchLinkRequest, false);
    }

   processCatchLinkRequest(){
        if(xhr.readyState === 4 && xhr.status === 200){
            var response = JSON.parse(xhr.responseText);
            this.setState({
                showLink: true,
                link: response.link
            });
        }
    }

    complete(){
        this.setState({
            begin: false,
            showLink: false
        });
    }
}

export default MathematicsContainer;



