class KnowledgeBaseForm extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            titleTxt: '',
            categoryTxt: '',
            contentTxt: ''
        };

        this.fileInputRef = React.createRef(); // File Input is uncontrolled component
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
        e.preventDefault();
        var fileName = this.fileInputRef.current.files[0].name;
        console.log("Submission: \n" + "Title: " + this.state.titleTxt + "\nCategory: " + this.state.categoryTxt + "\nContent: " + this.state.contentTxt + "\nAttachment: " + fileName);

        // this.setState({titleTxt: '', categoryTxt: ''}); // no need to reset to the initial values
        
    }

    render() {
        return(
            <form onSubmit={this.handleSubmit}>
                <TitleInput label={"Title: "} name="titleTxt" value={this.state.titleTxt} handleTitleChange={this.handleInputChange}></TitleInput>
                <CategoryInput label={"Category: "} name="categoryTxt" value={this.state.categoryTxt} options={this.props.categoryItems} handleCategoryChange={this.handleInputChange}></CategoryInput>
                <ContentInput label={"Content: "} name="contentTxt" value={this.state.contentTxt} handleContentChange={this.handleInputChange}></ContentInput>
                <div>Attach a file: </div>
                <input type="file" ref={this.fileInputRef} />
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

class ContentInput extends React.Component {
    handleContentChange = (e) => {
        this.props.handleContentChange(e);
    }

    render() {
        return(
            <div>
                <div>{this.props.label}</div>
                <textarea name={this.props.name} rows="4" cols="50" value={this.props.value} onChange={this.handleContentChange}></textarea>
            </div>
        );
    }
}

ReactDOM.render(
    <div>
        <KnowledgeBaseForm />
    </div>, 
    document.querySelector("#main"));