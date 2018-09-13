var USER = buildUrlWithContextPath("user");
var LOBBY_CONTENT_URL = buildUrlWithContextPath("lobbyArea");

$(function () {
    $.ajax({
        url:USER,
        success:function(data){
            $("#lobby-player-name").append("Hello " + data);
        }
    });
    setInterval(ajaxGetContent,2000);
});

function ajaxGetContent(){
    $.ajax({
        url: LOBBY_CONTENT_URL,
        dataType: 'json',
        success:function(data){
            var boards = $("#lobby-games");
            var title = boards.children[0].cloneNode(true);
            boards.empty();
            $("#lobby-games").appendChild(title);
            $.each(data.boards || [], createBoard);
            $("#lobby-userslist").empty();
            $.each(data.users || [], createUser);
        }
    })
}

function createUser(index,dataJson) {
    $('<li>' + dataJson + '</li>').appendTo($("#lobby-userslist"));
}

function createBoard(index,dataJson){
    if(dataJson !== []){
        var newBoard = $("<tr>");
        newBoard.appendChild($("<td class='lobby-col-title'>").append(dataJson.number));
        newBoard.appendChild($("<td class='lobby-col-title'>").append(dataJson.GameName));
        newBoard.appendChild($("<td class='lobby-col-title'>").append(dataJson.CreatedBy));
        newBoard.appendChild($("<td class='lobby-col-title'>").append(dataJson.RegisteredPlayers));
        newBoard.appendChild($("<td class='lobby-col-title'>").append(dataJson.BoardSize));
        newBoard.appendChild($("<td class='lobby-col-title'>").append(dataJson.Target));
        newBoard.appendChild($("<td class='lobby-col-title'>").append(dataJson.Active));
        $("#lobby-games").appendChild(newBoard);
    }
}

