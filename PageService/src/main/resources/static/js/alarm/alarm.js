// HTML로딩이 완료되고, 실행됨
$(document).ready(function () {

    $.ajax({ // Ajax 호출
        url: protocol + apiServer + "/alarm/getAlarmList",
        type: "post", // 전송방식은 Post
        xhrFields: {
            withCredentials: true
        },
        dataType: "JSON", // 전송 결과는 JSON으로 받기
        success: function (json) {


            console.log("json :" + json)


            // for (let i = 0; i < json.length; i++) {
            //     $("#noticeList").append("<div class=\"divTableRow\">");
            //
            //     if (json[i].noticeYn === "Y") { // 글번호 출력
            //         $("#noticeList").append("<div class=\"divTableCell\">공지사항</div>");
            //
            //     } else {
            //         $("#noticeList").append("<div class=\"divTableCell\">" + json[i].noticeSeq + "</div>");
            //
            //     }
            //
            //     $("#noticeList").append(
            //         "<div class=\"divTableCell\" onclick='doDetail(" + json[i].noticeSeq + ")'>"
            //         + json[i].title + "</div>");
            //     $("#noticeList").append("<div class=\"divTableCell\">" + json[i].readCnt + "</div>");
            //     $("#noticeList").append("<div class=\"divTableCell\">" + json[i].userId + "</div>");
            //     $("#noticeList").append("<div class=\"divTableCell\">" + json[i].regDt + "</div>");
            // }

        },
        // error: function (request, status, error) {
        error: function () {
            alert("접근 권한이 없습니다.");
            location.href = loginPage;

        }

    })
})