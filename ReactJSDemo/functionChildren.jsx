var names = ["Alpha", "Bravo", "Charlie", "Delta"];

function Names(props) {
    var namelist = [];
    for (var i = 0; i < props.upto; i++) {
        namelist.push(props.children(i, names));
    }

    return <div>{namelist}</div>
}

function NameList(props) {
    return (
        <Names upto={props.upto}>
            {(index, names) => <div key={index}>{names[index]}</div>}
        </Names>
    );
}

ReactDOM.render(<NameList upto={names.length}></NameList>, document.getElementById("outer"));