import axios from "axios";

type TaskCard = {
    "description": string,
    "status": string,
    "id": string,
    "getTasks": ()=>void
}

function Card(props:TaskCard) {


    function showDetails() {
    }


    function showEditPage() {
    }

    function deleteTask() {
        axios.delete("api/todo/" + props.id)
            .then(props.getTasks)
    }


    function changeStatus() {
        if (props.status === "OPEN") {
            axios.put("api/todo/" + props.id, {
                id: props.id,
                description: props.description,
                status: "IN_PROGRESS"
            })
                .then(props.getTasks)
        } else {
            axios.put("api/todo/" + props.id, {
                id: props.id,
                description: props.description,
                status: "DONE"
            })
                .then(props.getTasks)
        }
    }
    return (
        <div>
            <h2>
                {props.description}
                <br/>
                {props.status}
            </h2>
            <button onClick={showDetails}>Details</button>
            <button onClick={showEditPage}>Edit</button>
            {props.status === "DONE"?<button onClick={deleteTask}>Delete</button>:<button onClick={changeStatus}>Next Status</button>}
        </div>
    );
}

export default Card;