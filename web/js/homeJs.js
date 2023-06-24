function searchEs() {

    var url = "http://localhost:8080/S0228/ESS";
    var data = document.getElementById('search_endStation').value;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function (response) {
            // 请求成功
            console.log(response);
            var jsonArray = JSON.parse(response);
            console.log(jsonArray);
            var table = document.getElementById("station_table");
            for (var station in jsonArray) {
                var row = table.insertRow();
                var cell = row.insertCell();
                cell.innerHTML = jsonArray[station].id
                cell = row.insertCell();
                cell.innerHTML = jsonArray[station].startTime;
                cell = row.insertCell();
                cell.innerHTML = jsonArray[station].startStation
                cell = row.insertCell();
                cell.innerHTML = jsonArray[station].endStation;
                cell = row.insertCell();
                cell.innerHTML = jsonArray[station].capacity;
                cell = row.insertCell();
                cell.innerHTML = jsonArray[station].member;
                cell = row.insertCell();
                cell.innerHTML = "<button class='row_buy' onclick=openModal()>购买</button>";
                console.log(jsonArray[station].startStation);
            }
            showContent('content2');
        },
        error: function (xhr, status, error) {
            // 请求失败
            console.error(error);
        }
    });
}

function searchId() {

    var url = "http://localhost:8080/S0228/ISS";
    var data = document.getElementById('search_id').value;
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        success: function (response) {
            // 请求成功
            console.log(response);
            var jsonArray = JSON.parse(response);
            console.log(jsonArray);
            var table = document.getElementById("station_table");
            for (var station in jsonArray) {
                var row = table.insertRow();
                var cell = row.insertCell();
                cell.innerHTML = jsonArray[station].id
                cell = row.insertCell();
                cell.innerHTML = jsonArray[station].startTime;
                cell = row.insertCell();
                cell.innerHTML = jsonArray[station].startStation
                cell = row.insertCell();
                cell.innerHTML = jsonArray[station].endStation;
                cell = row.insertCell();
                cell.innerHTML = jsonArray[station].capacity;
                cell = row.insertCell();
                cell.innerHTML = jsonArray[station].member;
                cell = row.insertCell();
                cell.innerHTML = "<button class='row_buy' onclick=openModal()>购买</button>";
                console.log(jsonArray[station].startStation);
            }
            showContent('content2');
        },
        error: function (xhr, status, error) {
            // 请求失败
            console.error(error);
        }
    });
}

function showContent(contentId) {
    var contents = document.getElementsByClassName("content");

    // 隐藏所有内容区域
    for (var i = 0; i < contents.length; i++) {
        contents[i].classList.add("hidden");
    }

    // 显示选中的内容区域
    document.getElementById(contentId).classList.remove("hidden");
}


function add_ticket(jsonString) {
    console.log(jsonString);
    var jsonData = JSON.parse(jsonString);
    var table = document.getElementById("ticket_table");
    var row = table.insertRow();

    for (var key in jsonData) {
        var cell = row.insertCell();
        cell.innerHTML = jsonData[key];
    }
    var lastCell = row.insertCell();
    lastCell.innerHTML = "<button class='row_download'>下载</button>" + " <button class='row_delete'>取消订单</button>";
}


function openModal() {
    document.getElementById("myModal").style.display = "block";
}

function closeModal() {
    document.getElementById("myModal").style.display = "none";
}


//购买按钮
window.addEventListener('DOMContentLoaded', function () {
    var buyTable = document.getElementById('buy_form');
    buyTable.addEventListener('click', function (event) {
        if (event.target.classList.contains('row_buy_yes')) {
            var judge = confirm("确认购买吗?")
            if (judge === true) {
                var name = document.getElementById("name").value;
                var startTime = document.getElementById("startTime").value;
                var startStation = document.getElementById("startStation").value;
                var endStation = document.getElementById("endStation").value;
                var id = document.getElementById("id").value;
                console.log(id);
                if (name && name !== " ") {
                    var rowData = {
                        name: name,
                        startTime: startTime,
                        startStation: startStation,
                        endStation: endStation,
                        id: id
                    };
                    var jsonData = JSON.stringify(rowData);
                    console.log(jsonData);
                    var url = "http://localhost:8080/S0228/BT";
                    //     Ajax
                    $.ajax({
                        url: url,
                        type: "POST",
                        data: rowData,
                        success: function (response) {
                            // 请求成功
                            console.log("success");
                            alert("购票成功，请到订购页面查看！");
                            add_ticket(jsonData);
                        },
                        error: function (xhr, status, error) {
                            // 请求失败
                            console.error(error);
                        }
                    });
                } else {
                    alert("没有输入乘客姓名");
                }
            }
        }
    });
    var table = document.getElementById("station_table");
    var startTime = document.getElementById("startTime");
    var startStation = document.getElementById("startStation");
    var endStation = document.getElementById("endStation");
    var id = document.getElementById("id");
    // 监听表格行的点击事件
    table.addEventListener("click", function (event) {
        if (event.target.classList.contains('row_buy')) {
            var row = event.target.parentNode.parentNode;
            var cells = row.getElementsByTagName('td');
            startTime.value = cells[1].innerText;
            startStation.value = cells[2].innerText;
            endStation.value = cells[3].innerText;
            id.value = cells[0].innerText;
        }
    });
    var ticketTable = document.getElementById("ticket_table");
    ticketTable.addEventListener('click', function (event) {
        if (event.target.classList.contains('row_delete')) {
            var judge = confirm("确认取消订单吗吗?")
            if (judge === true) {
                var row = event.target.parentNode.parentNode;
                var cell = row.firstElementChild;
                var name = cell.innerHTML;
                //     Ajax
                $.ajax({
                    url: 'http://localhost:8080/S0228/TDS',
                    type: 'POST',
                    data: {value: name},
                    success: function (data) {
                        row.parentNode.removeChild(row);
                        console.log("完成");
                    },
                    error: function (xhr, status, error) {
                        console.log('Error:', error);
                    }
                });
            }
        }
    });

});

