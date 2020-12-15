import React from 'react';
import CinemaAxios from '../../apis/CinemaAxios';

import {Form, Button} from 'react-bootstrap';

class AddProjection extends React.Component {

    constructor(props){
        super(props);

        let projection = {
            time: "",
            type: "",
            hall: 0,
            price: 0.00,
            movie: null
        }

        this.state = {projection: projection, movies: []};
    }

    componentDidMount(){
        this.getMovies();
        console.log("test2");
    }

    // TODO: Dobaviti filmove
    async getMovies(){
        try{
            let result = await CinemaAxios.get("/filmovi");
            let movies = result.data;
            this.setState({movies: movies});
            console.log("test1");
        }catch(error){
            console.log(error);
            alert("Couldn't fetch movies");
        }
    }

    async create(e){
        e.preventDefault();

        try{

            let projection = this.state.projection;
            let projectionDTO = {
                datumIVreme: projection.time,
                film: projection.movie,
                sala: projection.hall,
                tip: projection.type,
                cenaKarte: projection.price
            }

            let response = await CinemaAxios.post("/projekcije", projectionDTO);
            this.props.history.push("/projections");
        }catch(error){
            alert("Couldn't save the movie");
        }
    }

    valueInputChanged(e) {
        let input = e.target;
    
        let name = input.name;
        let value = input.value;
    
        let projection = this.state.projection;
        projection[name] = value;
    
        this.setState({ projection: projection });
      }

    // TODO: Rukovati prihvatom vrednosti na promenu
    movieSelectionChanged(e){
        // console.log(e);

        let movieId = e.target.value;
        let movie = this.state.movies.find((movie) => movie.id == movieId);

        let projection = this.state.projection;
        projection.movie = movie;

        this.setState({projection: projection});
    }

    // TODO: Omogućiti odabir filma za projekciju
    render(){
        return (
            <div>
                <h1>Add Projection</h1>

                <Form>
                    <Form.Group>
                        <Form.Label htmlFor="pTime">Time</Form.Label>
                        <Form.Control id="pTime" name="time" onChange={(e)=>this.valueInputChanged(e)}/>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label htmlFor="pType">Type</Form.Label>
                        <Form.Control id="pType" name="type" onChange={(e)=>this.valueInputChanged(e)}/>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label id="pHall">Hall</Form.Label>
                        <Form.Control type="number" id="pHall" name="hall" onChange={(e)=>this.valueInputChanged(e)}/>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label htmlFor="pPrice">Price</Form.Label>
                        <Form.Control type="number" step=".01" id="pPrice" name="price" onChange={(e)=>this.valueInputChanged(e)}/>
                    </Form.Group>
                    
                    <Form.Group>
                        <Form.Label htmlFor="pMovie">Movies</Form.Label >


                    </Form.Group>
                    
                    <Form.Control as="select" id="pMovie" onChange={event => this.movieSelectionChanged(event)}>
                        <option></option>
                        {
                            this.state.movies.map((movie) => {
                                return (
                                    <option key={movie.id} value={movie.id}>{movie.naziv}</option>
                                )
                            })
                        }
                    </Form.Control><br/>

                    <Button variant="success" onClick={(event)=>{this.create(event);}}>Add</Button>
                </Form>
            </div>
        )
    }
}

export default AddProjection;