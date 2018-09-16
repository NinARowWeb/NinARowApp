var USER = buildUrlWithContextPath("users");
var BOARD_GAME_URL = buildUrlWithContextPath("BoardGame");

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
    //getStatisticsContent();
    //getPlayersDetailsContent();
    //getHistoryContent();
    //getChatContent();
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
                    if(row === 0 || (row === (data.Rows-1) && data.Varient === "POPOUT")){
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