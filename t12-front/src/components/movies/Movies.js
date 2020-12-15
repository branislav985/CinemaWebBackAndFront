import React from 'react';
import CinemaAxios from './../../apis/CinemaAxios';

// import './../../index.css';


class Movies extends React.Component {

    constructor(props) {
        super(props);

        this.state = { movies: []}
    }

    componentDidMount() {
        this.getMovies();
    }

    getMovies() {
        CinemaAxios.get('/filmovi')
            .then(res => {
                 // handle success
                 console.log(res);
                 this.setState({movies: res.data});
            })
            .catch(error => {
                // handle error
                console.log(error);
                alert('Error occured please try again!');
            });
    }

    getGenresStringFromList(list) {
        return list.map(element => element.naziv).join(',');
    }

    renderMovies() {
        return this.state.movies.map((movie, index) => {
            return (
               <tr key={movie.id}>
                  <td>{movie.naziv}</td>
                  <td>{movie.trajanje}</td>
                  <td>{this.getGenresStringFromList(movie.zanrovi)}</td>
                  <td><button className="button button-navy" onClick={() => this.goToEdit(movie.id)}>Edit</button></td>
                  <td><button className="button button-navy" onClick={() => this.delete(movie.id)}>Delete</button></td>
               </tr>
            )
         })
    }

    goToEdit(movieId) {
        this.props.history.push('/movies/edit/'+ movieId); 
    }

    deleteFromState(movieId) {
        var movies = this.state.movies;
        movies.forEach((element, index) => {
            if (element.id === movieId) {
                movies.splice(index, 1);
                this.setState({movies: movies});
            }
        });
    }

    delete(movieId) {
        CinemaAxios.delete('/filmovi/' + movieId)
        .then(res => {
            // handle success
            console.log(res);
            alert('Movie was deleted successfully!');
            this.deleteFromState(movieId); // ili refresh page-a window.location.reload();
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
         });
    }

    goToAdd() {
        this.props.history.push('/movies/add');  
    }

    render() {
        return (
            <div>
                <h1>Movies</h1>
                
                <div>
                    <button className="button button-navy" onClick={() => this.goToAdd() }>Add</button>
                    <br/>
                    
                    <table id="movies-table">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Duration (min)</th>
                                <th>Genres</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.renderMovies()}
                        </tbody>                  
                    </table>
                </div>
            </div>
        );
    }
}

export default Movies;