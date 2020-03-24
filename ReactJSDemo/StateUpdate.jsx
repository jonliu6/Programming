class DetailsBox extends React.Component {
    state = {
        name: 'Jon',
        position: 'Consultant'
    }

    // using arrow notation for function will auto-binding function to the component
    pickRandomName = () => {
        var names = ['Alpha','Bravo','Charie','Delta'];
        var nameIndex = Math.floor(Math.random() * names.length);
        this.setState({
            name: names[nameIndex]
        });
    }

    pickRandomPosition = () => {
        var positions = ['Consultant','Developer','Manager','QA'];
        var posIdx = Math.floor(Math.random() * positions.length);
        this.setState({
            position: positions[posIdx]
        });
    }

    render() {
        const txtStyle = {
            color: '#FF0000'
        }

        return(
            <div>
                <div style={txtStyle}>
                    Name: {this.state.name}<br/>
                    Position: {this.state.position}
                </div>
                <button onClick={this.pickRandomName}>Pick a Random Name</button>&#160;
                <button onClick={this.pickRandomPosition}>Pick a Position</button>
            </div>
        );
    }
}

ReactDOM.render(<DetailsBox />, document.getElementById("outer"));
