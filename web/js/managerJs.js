// 表格获取
//班次获取

//订票获取
$.ajax({
    url: 'http://localhost:8080/S0228/S-JOS',
    type: 'GET',
    dataType: 'json',
    success: function (data) {
        console.log(data);
        var table = document.getElementById("station_table");
        for (var station in data) {
            var row = table.insertRow();
            var cell = row.insertCell();
            cell.innerHTML = data[station].id
            cell = row.insertCell();
            cell.innerHTML = data[station].startTime;
            cell = row.insertCell();
            cell.innerHTML = data[station].startStation
            cell = row.insertCell();
            cell.innerHTML = data[station].endStation;
            cell = row.insertCell();
            cell.innerHTML = data[station].capacity;
            cell = row.insertCell();
            cell.innerHTML = data[station].member;
            cell = row.insertCell();
            cell.innerHTML = "<button class='row_edit'>Edit</button><button class='row_delete'>Delete</button>";
            console.log(data[station].startStation);
            console.log(data[station].startStation);
        }

    },
    error: function (xhr, status, error) {
        console.log('Error:', error);
    }
});
//订票获取
$.ajax({
    url: 'http://localhost:8080/S0228/T-JOS',
    type: 'GET',
    dataType: 'json',
    success: function (data) {
        console.log(data);
        var table = document.getElementById("ticket_table");
        for (var station in data) {
            var row = table.insertRow();
            var cell = row.insertCell();
            cell.innerHTML = data[station].name
            cell = row.insertCell();
            cell.innerHTML = data[station].time;
            cell = row.insertCell();
            cell.innerHTML = data[station].startStation
            cell = row.insertCell();
            cell.innerHTML = data[station].endStation;
            cell = row.insertCell();
            cell.innerHTML = data[station].id;
            cell = row.insertCell();
            cell.innerHTML = "<button class='row_edit'>Edit</button><button class='row_delete'>Delete</button>";
            console.log(data[station].startStation);
            console.log(data[station].startStation);
        }

    },
    error: function (xhr, status, error) {
        console.log('Error:', error);
    }
});
$.ajax({
    url: 'http://localhost:8080/S0228/U-JOS',
    type: 'GET',
    dataType: 'json',
    success: function (data) {
        console.log(data);
        var table = document.getElementById("user_table");
        for (var user in data) {
            var row = table.insertRow();
            var cell = row.insertCell();
            cell.innerHTML = data[user].name
            cell = row.insertCell();
            cell.innerHTML = data[user].password;
            cell = row.insertCell();
            cell.innerHTML = "<button class='row_edit'>Edit</button><button class='row_delete'>Delete</button>";
        }

    },
    error: function (xhr, status, error) {
        console.log('Error:', error);
    }
});

function showContent(contentId) {
    var contents = document.getElementsByClassName("content");

    // 隐藏所有内容区域
    for (var i = 0; i < contents.length; i++) {
        contents[i].classList.add("hidden");
    }

    // 显示选中的内容区域
    document.getElementById(contentId).classList.remove("hidden");
}

// 班次管理
function station_addRow() {
    var sTable = document.getElementById("station_table");
    var tBodies = sTable.tBodies;
    var tbody = tBodies[0];
    var tr = tbody.insertRow(tbody.rows.length);
    var td_1 = tr.insertCell(0);
    td_1.setAttribute("contenteditable", "true")
    var td_2 = tr.insertCell(1);
    td_2.setAttribute("contenteditable", "true")
    var td_3 = tr.insertCell(2);
    td_3.setAttribute("contenteditable", "true")
    var td_4 = tr.insertCell(3);
    td_4.setAttribute("contenteditable", "true")
    var td_5 = tr.insertCell(4);
    td_5.setAttribute("contenteditable", "true")
    var td_6 = tr.insertCell(5);
    td_6.setAttribute("contenteditable", "true")
    var td_7 = tr.insertCell(6);
    td_7.innerHTML = "<button  class='row_finish'>完成</button><button class='row_cancel'>取消</button>";
}

//订票管理
function ticket_addRow() {
    var sTable = document.getElementById("ticket_table");
    var tBodies = sTable.tBodies;
    var tbody = tBodies[0];
    var tr = tbody.insertRow(tbody.rows.length);
    var td_1 = tr.insertCell(0);
    td_1.setAttribute("contenteditable", "true")
    var td_2 = tr.insertCell(1);
    td_2.setAttribute("contenteditable", "true")
    var td_3 = tr.insertCell(2);
    td_3.setAttribute("contenteditable", "true")
    var td_4 = tr.insertCell(3);
    td_4.setAttribute("contenteditable", "true")
    var td_5 = tr.insertCell(4);
    td_5.setAttribute("contenteditable", "true")
    var td_6 = tr.insertCell(5);
    td_6.innerHTML = "<button  class='row_finish'>完成</button><button class='row_cancel'>取消</button>";
}

// 用户管理
function user_addRow() {
    var sTable = document.getElementById("user_table");
    var tBodies = sTable.tBodies;
    var tbody = tBodies[0];
    var tr = tbody.insertRow(tbody.rows.length);
    var td_1 = tr.insertCell(0);
    td_1.setAttribute("contenteditable", "true")
    var td_2 = tr.insertCell(1);
    td_2.setAttribute("contenteditable", "true")
    var td_3 = tr.insertCell(2);
    td_3.innerHTML = "<button  class='row_finish'>完成</button><button class='row_cancel'>取消</button>";
}

// 表格监听器
window.addEventListener('DOMContentLoaded', function () {
    //班次管理
    var stationTable = document.getElementById('station_table');
    stationTable.addEventListener('click', function (event) {
        if (event.target.classList.contains('row_delete')) {
            var judge = confirm("确认删除吗?")
            if (judge === true) {
                var row = event.target.parentElement.parentElement;
                var cell = row.firstElementChild;

                var id = cell.innerHTML;
                console.log(id);
                $.ajax({
                    url: 'http://localhost:8080/S0228/sds',
                    type: 'POST',
                    data: {value: id},
                    success: function (data) {
                        console.log("完成");
                    },
                    error: function (xhr, status, error) {
                        console.log('Error:', error);
                    }
                });
                row.parentNode.removeChild(row);
            }
        }
    });
    stationTable.addEventListener('click', function (event) {
        if (event.target.classList.contains('row_cancel')) {
            var row = event.target.parentElement.parentElement;
            row.parentNode.removeChild(row);
        }
    });
    stationTable.addEventListener('click', function (event) {
        if (event.target.classList.contains("row_finish")) {
            var row = event.target.parentElement.parentElement;
            var cells = row.getElementsByTagName('td');
            var rowData = {
                id: cells[0].innerText,
                startTime: cells[1].innerText,
                startStation: cells[2].innerText,
                endStation: cells[3].innerText,
                capacity: cells[4].innerText,
                member: cells[5].innerText
            };
            var jsonData = JSON.stringify(rowData);
            console.log(jsonData);
            $.ajax({
                url: 'http://localhost:8080/S0228/sis',
                type: 'POST',
                data: rowData,
                success: function (data) {
                    console.log("完成");
                },
                error: function (xhr, status, error) {
                    console.log('Error:', error);
                }
            });
            var lastCell = row.lastElementChild;
            var lastCellIndex = row.cells.length - 1;
            for (var j = 0; j < lastCellIndex; j++) {
                row.cells[j].setAttribute("contenteditable", "false");
            }
            lastCell.innerHTML = "<button class='row_edit'>Edit</button>" + " <button class='row_delete'>Delete</button>";
        }
    });
    stationTable.addEventListener('click', function (event) {
        if (event.target.classList.contains('row_edit')) {
            var row = event.target.parentElement.parentElement;
            var lastCellIndex = row.cells.length - 1;
            var lastCell = row.lastElementChild;
            lastCell.innerHTML = "<button class='row_edit_finish'>完成</button>";
            for (var j = 0; j < lastCellIndex; j++) {
                row.cells[j].setAttribute("contenteditable", "true");
            }
        }
    });
    stationTable.addEventListener('click', function (event) {
        if (event.target.classList.contains("row_edit_finish")) {
            var row = event.target.parentElement.parentElement;
            var lastCellIndex = row.cells.length - 1;
            for (var j = 0; j < lastCellIndex; j++) {
                row.cells[j].setAttribute("contenteditable", "false");
            }
            var lastCell = row.lastElementChild;
            lastCell.innerHTML = "<button class='row_edit'>Edit</button>" + " <button class='row_delete'>Delete</button>";
        }
    });
    //订票管理
    var ticketTable = document.getElementById("ticket_table");
    ticketTable.addEventListener('click', function (event) {
        if (event.target.classList.contains('row_delete')) {
            var judge = confirm("确认删除吗?")
            if (judge === true) {
                var row = event.target.parentElement.parentElement;
                var cell = row.firstElementChild;

                var name = cell.innerHTML;
                console.log(name);
                $.ajax({
                    url: 'http://localhost:8080/S0228/tds',
                    type: 'POST',
                    data: {value: name},
                    success: function (data) {
                        console.log("完成");
                    },
                    error: function (xhr, status, error) {
                        console.log('Error:', error);
                    }
                });
                row.parentNode.removeChild(row);
            }
        }
    });
    ticketTable.addEventListener('click', function (event) {
        if (event.target.classList.contains('row_cancel')) {
            var row = event.target.parentNode.parentNode;
            row.parentNode.removeChild(row);
        }
    });
    ticketTable.addEventListener('click', function (event) {
        if (event.target.classList.contains("row_finish")) {
            var row = event.target.parentElement.parentElement;
            var cells = row.getElementsByTagName('td');
            var rowData = {
                name: cells[0].innerText,
                time: cells[1].innerText,
                startStation: cells[2].innerText,
                endStation: cells[3].innerText,
                id: cells[4].innerText,
            };
            var jsonData = JSON.stringify(rowData);
            console.log(jsonData);
            var lastCell = row.lastElementChild;
            var lastCellIndex = row.cells.length - 1;
            for (var j = 0; j < lastCellIndex; j++) {
                row.cells[j].setAttribute("contenteditable", "false");
            }
            lastCell.innerHTML = "<button class='row_edit'>Edit</button>" + " <button class='row_delete'>Delete</button>";
        }
    })
    ticketTable.addEventListener('click', function (event) {
        if (event.target.classList.contains('row_edit')) {
            var row = event.target.parentElement.parentElement;
            var lastCell = row.lastElementChild;
            lastCell.innerHTML = "<button class='row_edit_finish'>完成</button>";
            var lastCellIndex = row.cells.length - 1;
            for (var j = 0; j < lastCellIndex; j++) {
                row.cells[j].setAttribute("contenteditable", "true");
            }
        }
    });
    ticketTable.addEventListener('click', function (event) {
        if (event.target.classList.contains("row_edit_finish")) {
            var row = event.target.parentElement.parentElement;
            var lastCellIndex = row.cells.length - 1;
            for (var j = 0; j < lastCellIndex; j++) {
                row.cells[j].setAttribute("contenteditable", "false");
            }
            var lastCell = row.lastElementChild;
            lastCell.innerHTML = "<button class='row_edit'>Edit</button>" + " <button class='row_delete'>Delete</button>";
        }
    });
    //用户管理
    var userTable = document.getElementById("user_table");
    userTable.addEventListener('click', function (event) {
        if (event.target.classList.contains('row_delete')) {
            var judge = confirm("确认删除吗?")
            if (judge === true) {
                var row = event.target.parentElement.parentElement;
                var cell = row.firstElementChild;

                var name = cell.innerHTML;
                console.log(name);
                $.ajax({
                    url: 'http://localhost:8080/S0228/uds',
                    type: 'POST',
                    data: {value:name},
                    success: function (data) {
                        console.log("完成");
                    },
                    error: function (xhr, status, error) {
                        console.log('Error:', error);
                    }
                });
                row.parentNode.removeChild(row);
            }
        }
    });
    userTable.addEventListener('click', function (event) {
        if (event.target.classList.contains('row_cancel')) {
            var row = event.target.parentElement.parentElement;
            row.parentNode.removeChild(row);
        }
    });
    userTable.addEventListener('click', function (event) {
        if (event.target.classList.contains("row_finish")) {
            var row = event.target.parentElement.parentElement;
            var cells = row.getElementsByTagName('td');
            var rowData = {
                name: cells[0].innerText,
                password: cells[1].innerText
            }
            var jsonData = JSON.stringify(rowData);
            console.log(jsonData);
            var lastCell = row.lastElementChild;
            var lastCellIndex = row.cells.length - 1;
            for (var j = 0; j < lastCellIndex; j++) {
                row.cells[j].setAttribute("contenteditable", "false");
            }
            lastCell.innerHTML = "<button class='row_edit'>Edit</button>" + " <button class='row_delete'>Delete</button>";
        }
    })
    userTable.addEventListener('click', function (event) {
        if (event.target.classList.contains('row_edit')) {
            var row = event.target.parentElement.parentElement;
            var lastCell = row.lastElementChild;
            lastCell.innerHTML = "<button class='row_edit_finish'>完成</button>";
            var lastCellIndex = row.cells.length - 1;
            for (var j = 0; j < lastCellIndex; j++) {
                row.cells[j].setAttribute("contenteditable", "true");
            }
        }
    });
    userTable.addEventListener('click', function (event) {
        if (event.target.classList.contains("row_edit_finish")) {
            var row = event.target.parentElement.parentElement;
            var lastCellIndex = row.cells.length - 1;
            for (var j = 0; j < lastCellIndex; j++) {
                row.cells[j].setAttribute("contenteditable", "false");
            }
            var lastCell = row.lastElementChild;
            lastCell.innerHTML = "<button class='row_edit'>Edit</button>" + " <button class='row_delete'>Delete</button>";
        }
    });
});

