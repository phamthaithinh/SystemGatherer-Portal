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
    for(index = 0; index < data.length; index++) {
        for(check_number = 0; check_number < data[index].checks.length;check_number++) {
            var buffer = data[index];
            $.get("http://" + buffer.ip + "/?name=" + buffer.checks[check_number], function(response){
                if (response.code == 0) {
                    results.append(
                            "<div class='alert alert-success' id="+ buffer.ip +"_" + response.name + ">"
                            + buffer.name + "\t" + response.name + " \t" + JSON.stringify(response.info) + "\tLast check: " + new Date(response.date) + "</div>"
                    );
                } else if (response.code == 1) {
                    results.append(
                            "<div class='alert alert-warning' id="+ buffer.ip +"_" + response.name + ">"
                            + buffer.name + "\t" + response.name + " \t" + JSON.stringify(response.info) + "\tLast check: " + new Date(response.date) + "</div>"
                    );
                }  else if (response.code == 2) {
                    results.append(
                            "<div class='alert alert-danger' id="+ buffer.ip +"_" + response.name + ">"
                            + buffer.name + "\t" + response.name + " \t" + JSON.stringify(response.info) + "\tLast check: " + new Date(response.date) + "</div>"
                    );
                } else if (response.code == 3) {
                    results.append(
                            "<div class='alert alert-info' id="+ buffer.ip +"_" + response.name + ">"
                            + buffer.name + "\t" + response.name + " \t" + JSON.stringify(response.info) + "\tLast check: " + new Date(response.date) + "</div>"
                    );
                }
            });
        }
    }
}