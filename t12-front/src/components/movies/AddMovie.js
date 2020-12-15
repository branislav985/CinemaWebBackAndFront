import React from 'react';
import CinemaAxios from './../../apis/CinemaAxios';

class AddMovie extends React.Component {

    constructor(props) {
        super(props);

        this.state = { name: '', duration: 0 }
        this.create = this.create.bind(this);
    } 

    create() {
        var params = {
            'naziv': this.state.name,
            'trajanje': this.state.duration
        };

        CinemaAxios.post('/filmovi', params)
        .then(res => {
            // handle success
            console.log(res);
           
            alert('Movie was added successfully!');
            this.props.history.push('/movies');
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
         });
    }

    onNameChange = event => {
        console.log(event.target.value);

        const { name, value } = event.target;
        console.log(name + ", " + value);

        this.setState((state, props) => ({
            name: value
        }));
    }

    onDurationChange = event => {
        console.log(event.target.value);

        const { name, value } = event.target;
        console.log(name + ", " + value);

        this.setState((state, props) => ({
            duration: value
        }));
    }

    render() {
        return (
            <div>
                <h1>Add new movie</h1>
                <form>
                    <label htmlFor="name">Name</label>
                    <input id="name" type="text" onChange={(e) => this.onNameChange(e)}/><br/>
                    <label htmlFor="duration">Duration</label>
                    <input id="duration" type="number" onChange={(e) => this.onDurationChange(e)}/>
                    <button className="button button-navy" onClick={this.create}>Add</button>
                </form>
            </div>
        );
    }
}

export default AddMovie;