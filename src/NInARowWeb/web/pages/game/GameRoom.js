var USER = buildUrlWithContextPath("users");
var BOARD_GAME_URL = buildUrlWithContextPath("BoardGame");
var STATISTICS_URL = buildUrlWithContextPath("Statistics");
var HISTORY_URL = buildUrlWithContextPath("history");
var PLAYERS_DETAILS_URL = buildUrlWithContextPath("playersDetails");
var historyIndex = -1;

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
            if(data.gameStatus === "Gaming"){
                $("#Current-name-turn").empty();
                $("#Current-name-turn").append(data.PlayerNameTurn);
                $("#Time-elapsed").empty();
                $("#Time-elapsed").append(data.Time);
                $("#Next-Player").empty();
                $("#Next-Player").append(data.NextPlayerName);
            }
        }
    })
}

function getPlayersDetailsContent() {
    $.ajax({
        url: PLAYERS_DETAILS_URL,
        dataType: 'json',
        success:function(data){
                if(data.gameActive) {
                    $("#playersDetails").empty();
                    var currentPlayer = $("<div class = 'playerDetails'>");
                    for (var i = 0; i < data.amountOfPlayers; ++i) {
                        var name = $("<label class = 'playerName'>data.players[i].playerName");
                        var type = $("<label class = 'playerType'>data.players[i].type");
                        var colorOnBoard = $("<label class = 'playerColor'>data.players[i].color");
                        var turnsPlayed = $("<label class = 'playerTurns'>data.players[i].turns");
                        var status = $("<label class = 'playerStatus'>data.players[i].status");
                        currentPlayer.append(name, type, colorOnBoard, turnsPlayed, status);
                    }
                    $("playersDetails").append(currentPlayer);
                }
        }
    })

}

function getHistoryContent(){
    $.ajax({
        url: HISTORY_URL,
        dataType: 'json',
        success:function(data){
            if(data.lastMove && data.index !== historyIndex){
                var newHistoryItem = $("<li class ='History-Item'>")
                newHistoryItem[0].innerHTML = data.lastMove;
                $("#History-List").append(newHistoryItem);
                historyIndex = data.index;
            }
        }
    })
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
                    if(data.gameStatus === "Gaming" &&(row === 0 || (row === (data.Rows-1) && data.Varient === "POPOUT"))){
                        button = $("<input id ='board-cell-button' value='' type='submit' >")
                    }
                    else{
                        button = $("<input id ='board-cell-button' value='' type='submit' disabled>")
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
        }
    })
}