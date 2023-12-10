// HTML로딩이 완료되고, 실행됨
$(document).ready(
    alarmList()
);


function alarmList() {

    $.ajax({ // Ajax 호출
        url: protocol + apiServer + "/alarm/getAlarmList",
        type: "post", // 전송방식은 Post
        xhrFields: {
            withCredentials: true
        },
        dataType: "JSON", // 전송 결과는 JSON으로 받기
        success: function (json) {


            for (let i = 0; i < json.length; i++) {
                console.log("json :" + json[i].msgSeq)
                console.log("json :" + json[i].title)
                console.log("json :" + json[i].content)
                console.log("json :" + json[i].msgTime)
                console.log("json :" + json[i].url)
            }

            for (let i = 0; i < json.length; i++) {

                $("#alarmList").append("<li class=\"mt20\" id=\"" + json[i].msgSeq +"\"><p>"
                    +"<div onclick=\"urlLocation('" +json[i].url+"')\">"
                    +"<h4 style=\"display: inline;\">" + json[i].title + "</h4>"
                    +"<h6 style=\"display: inline;float: right;\">알람 시간 :"+ json[i].msgTime + "</h6>"
                    +"</div>"
                    +"<span class=\"float-right\"> <img src=\"/images/icons/x.svg\" onclick='alarmDelete(" +json[i].msgSeq + ")'></span>"
                    +"<div onclick=\"urlLocation('" +json[i].url+"')\">"


                    +json[i].content
                    +"</div>" + "</p></li>"
                );

            }

        },
        // error: function () {
        //     alert("접근 권한이 없습니다.");
        //    // swal(title = "접근 권한 " , msg ="접근 권한이 없습니다.", state ="error");
        //
        //     location.href = loginPage;
        //
        // }

    })
}

function urlLocation(url) {

    // 선택한 URL로 이동
    location.href = url;
}


function alarmDelete(msgSeq) {


    $.ajax({ // Ajax 호출
        url: protocol + apiServer + "/alarm/deleteOneAlarm",
        type: "post",
        data: {"Seq": msgSeq},
        xhrFields: {
            withCredentials: true
        },
        success: function (res) { // Ajax 요청이 성공했다면...

            if (res === 0){
                swal(title = "메세지 실패" , msg ="다시 한번 시도 해주세요!", state ="error");

            }else if (res === 1){
                // swal(title = "메세지 삭제" , msg ="알림 메시지 삭제 완료", state ="success");
                $("#msgSeq").remove();
                location.reload();

            }
        }
    });
    return false;
}



function alarmAllDelete() {


    $.ajax({ // Ajax 호출
        url: protocol + apiServer + "/alarm/deleteAllAlarm",
        type: "post",
        xhrFields: {
            withCredentials: true
        },
        success: function (res) { // Ajax 요청이 성공했다면...

            if (res === 0){
                swal(title = "전체 삭제 실패" , msg ="다시 한번 시도 해주세요!", state ="error");

            }else if (res === 1){
                $("#alarmList").remove();
                location.reload();

            }
        }
    });
    return false;
}