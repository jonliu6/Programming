class KnowledgeBaseForm extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            titleTxt: '',
            categoryTxt: '',
            contentTxt: '',
            attachFileName: ''
        };
    }

    handleInputChnage = (e) => {
        const name = e.target.name;
        var value = e.target.value;
        console.log(name + ":" + value);
        this.setState({
            [name]: value
        });
    }

    render() {
        return(
            <form>
                <TitleInput label={"Title: "} name="titleTxt" value={this.state.titleTxt} handleTitleChange={this.handleInputChnage}></TitleInput>
            </form>
        );
    }
}

class TitleInput extends React.Component {
    handleTitleChange = (e) => {
        this.props.handleTitleChange(e);
    }

    render() {
        return(
            <div>
                <div>{this.props.label}</div>
                <input type="text" name={this.props.name} value={this.props.value} onChange={this.handleTitleChange}></input>
            </div>
        );
    }
}

ReactDOM.render(
    <div>
        <KnowledgeBaseForm />
    </div>, 
    document.querySelector("#main"));