var USER = buildUrlWithContextPath("users");
var BOARD_GAME_URL = buildUrlWithContextPath("boardGame");

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
    getChatContent();
    $.ajax({
        url: BOARD_GAME_URL,
        success:function(data){

        }
    })
}