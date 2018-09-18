var USER = buildUrlWithContextPath("users");
var BOARD_GAME_URL = buildUrlWithContextPath("BoardGame");
var STATISTICS_URL = buildUrlWithContextPath("Statistics");
var HISTORY_URL = buildUrlWithContextPath("History");
var PLAYERS_DETAILS_URL = buildUrlWithContextPath("PlayersDetails");
var START_GAME_URL = buildUrlWithContextPath("StartGame");
var PLAYER_MOVE_URL = buildUrlWithContextPath("PlayerMove");
var historyIndex = 0;

$(function () {
    $.ajax({
        url:USER,
        success:function(data){
            $("#player-session-name").append("Hello " + data);
        }
    });

    setInterval(getContent,2000);

});

function getContent(){
    getBoardContent();
    getStatisticsContent();
    getPlayersDetailsContent();
    getHistoryContent();
    //getChatContent();
}

function getStatisticsContent(){
    $.ajax({
        url: STATISTICS_URL,
        dataType: 'json',
        success:function(data){
            $("#Target").empty();
            $("#Target").append(data.Target);//ToDO: check on children attribute
            $("#Varient").empty();
            $("#Varient").append(data.Varient);
            if(data.GameActive !== "PRE_GAME"){
                $("#Current-name-turn").empty();
                $("#Current-name-turn").append(data.PlayerNameTurn);
                $("#Time-elapsed").empty();
                $("#Time-elapsed").append(data.Time);
                $("#Next-Player").empty();
                $("#Next-Player").append(data.NextPlayerName);
                if($(".game-label")[0].style.visibility !== "visible")
                    $.each($(".game-label") || [], setVisible)
            }
        }
    })
}

function setVisible(index,data){
    data.style.visibility = "visible";
}

function getPlayersDetailsContent() {
    $.ajax({
        url: PLAYERS_DETAILS_URL,
        dataType: 'json',
        success: function (data) {
            if (data.GameActive !== "PRE_GAME") {
                $("#playersDetails").empty();
                for (var i = 0; i < data.Players.length; ++i) {
                    var player = $("<div class = 'playerDetails'>");
                    var name = $("<label class = 'playerName'>").append("Name :" + data.Players[i].PlayerName).append($("<br>"));
                    var type = $("<label class = 'playerType'>").append("Type :" +data.Players[i].Type).append($("<br>"));
                    var colorOnBoard = $("<label class = 'playerColor'>").append("Color :" +data.Players[i].Color).append($("<br>"));
                    var turnsPlayed = $("<label class = 'playerTurns'>").append("Turns Played :" +data.Players[i].Turns).append($("<br>"));
                    var status = $("<label class = 'playerStatus'>").append("Status :" +data.Players[i].Status).append($("<br>"));
                    player.append(name, type, colorOnBoard, turnsPlayed, status);
                    $("#playersDetails").append(player);
                }
            }
        }
    })
}

function getHistoryContent(){
    $.ajax({
        url: HISTORY_URL,
        data: "Index=" + historyIndex,
        dataType: 'json',
        success:function(data){
            if(data.GameActive !== "PRE_GAME" && data.HistoryMoves && data.index !== historyIndex){
                $("#History").css({"visibility":"visible"});
                $.each(data.HistoryMoves || [], appendHistoryContent);
                historyIndex = data.index;
            }
        }
    })
}

function appendHistoryContent(index,data) {
    var newHistoryItem = $("<li class ='History-Item'>")
    newHistoryItem[0].innerHTML = data;
    $("#History-List").append(newHistoryItem);
}

function getBoardContent(){
    $.ajax({
        url: BOARD_GAME_URL,
        dataType: 'json',
        success:function(data){
            var newBoard = $("#board");
            newBoard.empty();
            var row;
            var col;
            for (row = 0; row < data.Rows; row++) {
                for(col = 0; col < data.Cols; col++){
                    var button;
                    if(data.GameStatus === "GAMING") {
                        if (row === 0) {
                            button = $("<button id='board-cell-button' onclick='playerMove(this,false)'>");
                            button[0].setAttribute("Col",col);
                        }
                        else if (row === (data.Rows - 1) && data.Varient === "POPOUT") {
                            button = $("<button id ='board-cell-button' onclick='playerMove(this,true)' >");
                            button[0].setAttribute("Col",col);
                        }
                        else {
                            button = $("<button id ='board-cell-button' type='submit' disabled>")
                        }
                    }
                    else{
                            button = $("<button id ='board-cell-button' type='submit' disabled>")
                    }
                    if(data.Board[row][col]){
                        button[0].style.backgroundImage = 'url(../../resources/' + data.Board[row][col].m_Sign +'.png)';
                    }
                    else
                    {
                        button[0].style.backgroundImage = 'url(' + '../../resources/background.png' + ')';
                    }
                    newBoard.append(button);
                }
                newBoard.append($("<br>"));
            }
            if(data.GameStatus === "END_GAME")
                setWinnersAnimation(data);
            if(data.GameStatus === "PRE_GAME" && data.ActiveGame === "Yes"){
                startGame();
            }
        }
    })
}

function setWinnersAnimation(data){
    var Board = $("#board");
    for(var i = 0; i< data.WinnersPoints.length;++i){
        Board[0].children[(data.Cols + 1)*data.WinnersPoints[i].y + data.WinnersPoints[i].x].setAttribute("id","Winner-Button");
    }
}

function playerMove(button,popout){
    var col = button.getAttribute("Col");
    $.ajax({
        url: PLAYER_MOVE_URL,
        data: {
            "Col" : col,
            "Popout" : popout,
        },
        method: "POST",
        success:function(data){
            $("#error-move-message").empty();
            if(data !== "null"){
                $("#error-move-message").append(data);
                setTimeout(clearErrorMessage(),10000);
            }
        }
    })
}

function clearErrorMessage(){
    $("#error-move-message").empty();
}

function startGame(){
    $.ajax({
        url: START_GAME_URL,
        method: "POST",
        success:function(data){}
    })
}