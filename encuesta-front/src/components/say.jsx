import React, { Component } from "react";
import axios from "axios";

class Say extends Component {
  state = {
    message: "",
    userId: "",
  };

  componentDidMount() {
    let userId = this.props.match.params.userId;
    this.setState({ userId: userId });
  }

  render() {
    const { message } = this.state;
    return (
      <div className="jumbotron">
        <h1 className="display-4">Share your thoughts!</h1>
        <p className="lead">
          Remember that whatever you write will be secretly conveyed to the
          person. So choose your words wisely.
        </p>
        <hr className="my-4" />
        <form>
          <div className="form-group">
            <textarea
              className="form-control"
              id="exampleFormControlTextarea1"
              rows="3"
              onChange={this.handleChange}
            ></textarea>
          </div>
        </form>
        <button
          className="btn btn-primary btn-lg"
          onClick={() => this.handleSendMessage(message)}
        >
          Send Message
        </button>
      </div>
    );
  }

  handleChange = (event) => {
    this.setState({ message: event.target.value });
  };

  handleSendMessage = (message) => {
    console.log(message);
    const reqBody = {
      userId: this.state.userId,
      userMessage: this.state.message,
    };
    axios
      .post("http://localhost:8080/messages/", reqBody)
      .then((response) => {
        console.log(response);
        window.alert("Message sent successfully!");
        window.location.reload();
      })
      .catch((error) => console.log(error));
  };
}

export default Say;
