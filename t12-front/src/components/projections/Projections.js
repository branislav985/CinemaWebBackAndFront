import React from "react";
import CinemaAxios from "../../apis/CinemaAxios";

import {Button, Table, Form} from 'react-bootstrap';

class Projections extends React.Component {
  constructor(props) {
    super(props);

    let params = {
      filmId: "",
      dateFrom: "",
    };

    this.state = { projections: [], movies: [], params: params, pageNum: 0, totalPages: 1};
  }

  componentDidMount() {
    this.getProjections();
    this.getMovies();
  }

  async getProjections() {
    try {

      let config = {params:{}};
      if(this.state.params.filmId != ""){
        config.params.filmId = this.state.params.filmId;
      }
      if(this.state.params.dateFrom != ""){
        config.params.datumIVremeOdParametar = this.state.params.dateFrom;
      }

      config.params.pageNum = this.state.pageNum;

      let result = await CinemaAxios.get("/projekcije", config);
      this.setState({ projections: result.data, totalPages: result.headers["total-pages"]});
    } catch (error) {
      console.log(error);
    }
  }

  async getMovies(){
    try{
        let result = await CinemaAxios.get("/filmovi");
        let movies = result.data;
        this.setState({movies: movies});
    }catch(error){
        console.log(error);
        alert("Couldn't fetch movies");
    }
}

  goToAdd() {
    this.props.history.push("/projections/add");
  }

  valueInputChanged(e){
    let control = e.target;

    let name = control.name;
    let value = control.value;

    let params = this.state.params;
    params[name] = value;

    this.setState({params: params});
  }

  doSearch(e){
    e.preventDefault();

    this.setState({pageNum: 0}, ()=>{this.getProjections();});
  }

  changePage(direction){
    let pageNum = this.state.pageNum;
    pageNum = pageNum + direction;

    this.setState({pageNum: pageNum}, ()=>{this.getProjections()});
  }

  render() {
    return (
      <div>
        <h1>Projections</h1>

        <Form>
          <Form.Group>
            <Form.Label>Movies</Form.Label>
            <Form.Control onChange={(event)=>this.valueInputChanged(event)} name="filmId" as="select">
              <option value=""></option>
              {
                this.state.movies.map((movie) => {
                  return <option key={movie.id} value={movie.id}>{movie.naziv}</option>
                })
              }
            </Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Date From</Form.Label>
            <Form.Control onChange={(e)=>{this.valueInputChanged(e);}} name="dateFrom"></Form.Control>
          </Form.Group>
          <Button style={{marginBottom:"10px"}} onClick={(e)=>this.doSearch(e)}>Search</Button>
        </Form>

        <div>
          <Button onClick={() => this.goToAdd()}>
            Add
          </Button>
          <br />

          <br/>
          <br/>

          <Button disabled={this.state.pageNum==0} onClick={()=>this.changePage(-1)}>Previous</Button>
          <Button disabled={this.state.pageNum == this.state.totalPages - 1} onClick={()=>this.changePage(1)}>Next</Button>
          <Table striped id="movies-table">
            <thead className="thead-dark">
              <tr>
                <th>Movie Name</th>
                <th>Time</th>
                <th>Projection Type</th>
                <th>Hall</th>
                <th>Price</th>
              </tr>
            </thead>
            <tbody>
              {this.state.projections.map((projection) => {
                return (
                  <tr key={projection.id}>
                    <td>{projection.film.naziv}</td>
                    <td>{projection.datumIVreme}</td>
                    <td>{projection.tip}</td>
                    <td>{projection.sala}</td>
                    <td>{projection.cenaKarte}</td>
                  </tr>
                );
              })}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

export default Projections;
