$.getJSON('http://localhost:8080/S0228/S-JOS', function (data) {
    var html = '';
    for (var Ticket in data) {
        html += '<tr>';
        for (var key in data[Ticket]) {
            html += '<td>' + data[Ticket][key] + '</td>';
        }
        html +='<td>'+'<form action="ms" method="post">' +'<input type="submit" name="update" value="修改">'+'</form>'+'</td>';
        html += '</tr>';
    }
    $('#dataTable').append(html);
});
