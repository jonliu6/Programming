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

    handleInputChange = (e) => {
        const name = e.target.name;
        var value = e.target.value;
        // console.log(name + ":" + value);
        this.setState({
            [name]: value
        });
    }

    handleSubmit = (e) => {
        console.log("Submission: \n" + "Title: " + this.state.titleTxt + "\nCategory: " + this.state.categoryTxt);

        // this.setState({titleTxt: '', categoryTxt: ''}); // no need to reset to the initial values
        e.preventDefault();
    }

    render() {
        return(
            <form onSubmit={this.handleSubmit}>
                <TitleInput label={"Title: "} name="titleTxt" value={this.state.titleTxt} handleTitleChange={this.handleInputChange}></TitleInput>
                <CategoryInput label={"Category: "} name="categoryTxt" value={this.state.categoryTxt} options={this.props.categoryItems} handleCategoryChange={this.handleInputChange}></CategoryInput>

                <br/>
                <input type="submit" value="Submit"></input>
            </form>
        );
    }
}

KnowledgeBaseForm.defaultProps = {
    categoryItems: [
        'Programming', 'Database', 'Design', 'Miscellaneous'
    ]
};

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

class CategoryInput extends React.Component {
    handleInputChange = (e) => {
        this.props.handleCategoryChange(e);
    }

    render() {
        return(
            <div>
                <div>{this.props.label}</div>
                <select value={this.props.value} name={this.props.name} onChange={this.handleInputChange}>
                    {
                        this.props.options.map(
                            (option) => <option key={option} value={option}>{option}</option>
                        )
                    }
                </select>
            </div>
        );
    }
}

ReactDOM.render(
    <div>
        <KnowledgeBaseForm />
    </div>, 
    document.querySelector("#main"));