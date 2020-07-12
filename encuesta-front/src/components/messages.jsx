import React, { Component } from "react";
import axios from "axios";

class Messages extends Component {
  state = {
    selected: null,
    messageList: [],
  };

  componentDidMount() {
    axios
      .get("http://localhost:8080/messages/diprots")
      .then((response) => {
        console.log("Response: ", response);
        this.setState({ messageList: response.data.messageList });
      })
      .catch((error) => {
        console.log("Error: ", error);
      });
  }

  render() {
    const { messageList, selected } = this.state;
    return (
      <main role="main" className="container">
        <div className="starter-template">
          <h1>See what others feel about you!</h1>

          <ul className="list-group list-group-flush">
            {messageList.map((message) => (
              <li
                key={message.messageId}
                className={
                  message.messageId === selected
                    ? "list-group-item active"
                    : "list-group-item"
                }
                onClick={() => this.handleClickMessage(message)}
              >
                <div className="d-flex w-100 justify-content-between">
                  <small className="text-muted">
                    {this.showTime(message.creationTime)}
                  </small>
                </div>
                {message.userMessage}
              </li>
            ))}
          </ul>
        </div>
      </main>
    );
  }

  handleClickMessage = (message) => {
    const selected = message.id;
    this.setState({ selected });
  };

  showTime = (creationTime) => {
    var interval = Math.ceil((new Date().getTime() - creationTime) / 86400000);
    return interval + (interval === 1 ? " day " : " days ") + "ago";
  };
}

export default Messages;
