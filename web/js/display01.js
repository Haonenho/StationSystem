$.getJSON('http://localhost:8080/S0228/S-JOS', function (data) {
    var html = '';
    for (var Ticket in data) {
        html += '<tr>';
        for (var key in data[Ticket]) {
            html += '<td>' + data[Ticket][key] + '</td>';
        }
        html += '<td>' + '<button>' + '修改' + '</button>' + '<button>' + '删除' + '</button>' + '</td>';
        html += '</tr>';
    }
    $('#dataTable').append(html);
});

