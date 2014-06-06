$(document).ready(function(){
    var hosts = load_hosts();
});

function load_hosts() {
    $.get("http://127.0.0.1:9000/hosts", function(data){
        generate_select(data);
        get_results(data);
    });
}

function generate_select(data) {
    var hosts = $("#hosts");
    for(index = 0; index < data.length; index++) {
        hosts.append("<option value='" + data[index].ip + "'>" + data[index].ip + "  |  " + data[index].name + "</option>");
    }
}

function get_results(data) {
    var results = $("#results");

    var data = [];
    var nodes = [];
    var edges = [];

    for(index = 0; index < 0; index++) {
        nodes.push(
            {
                "id": data[index].ip,
                "label": data[index].ip,
                "x": index,
                "y": data[index].ip == "127.0.0.1" ? 0 : 1
            }
        );
        edges.push(
            {
                "id": index,
                "source": "127.0.0.1",
                "target": data[index].ip
            }
        )
    }

    data.push(
        {
            "nodes": nodes,
            "edges": edges
        }
    )

    sigma.parsers.json(data, {
        container: 'results',
        settings: {
            defaultNodeColor: '#ec5148'
        }
    });
}

//{
//    "nodes": [
//    {
//        "id": "n0",
//        "label": "A node",
//        "x": 0,
//        "y": 0,
//        "size": 3
//    },
//    {
//        "id": "n1",
//        "label": "Another node",
//        "x": 3,
//        "y": 1,
//        "size": 2
//    },
//    {
//        "id": "n2",
//        "label": "And a last one",
//        "x": 1,
//        "y": 3,
//        "size": 1
//    }
//],
//    "edges": [
//    {
//        "id": "e0",
//        "source": "n0",
//        "target": "n1"
//    },
//    {
//        "id": "e1",
//        "source": "n1",
//        "target": "n2"
//    },
//    {
//        "id": "e2",
//        "source": "n2",
//        "target": "n0"
//    }
//]
//}
