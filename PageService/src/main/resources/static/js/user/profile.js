/*카카오 지도 api */
function kakaoPost(f) {
    new daum.Postcode({
        oncomplete: function (data) {
            //카카오 api

            let address = data.address; //주소
            let zonecode = data.zonecode; //우편주소
            f.addr1.value = "(" + zonecode + ")" + address
        }
    }).open();
}

$(document).ready(
    proFile()
);


function proFile() {
    $.ajax({ // Ajax 호출
        url: protocol + apiServer + "/user/userInfo",
        type: "post", // 전송방식은 Post
        xhrFields: {
            withCredentials: true
        },
        dataType: "JSON", // 전송 결과는 JSON으로 받기
        success: function (json) {

            // console.log("json :" + json.userName)
            // console.log("json :" + json.addr1)
            // console.log("json :" + json.addr2)
            // console.log("json :" + json.email)

            $("#userName").val(json.userName);
            $("#email").val(json.email);
            $("#addr1").val(json.addr1);
            $("#addr2").val(json.addr2);

        },
        error: function () {
            alert("접근 권한이 없습니다.");

            // swal(title = "접근 권한 " , msg ="접근 권한이 없습니다.", state ="error");

            location.href = loginPage;

        }
    })

}


function userUpdate() {



    if (f.userName.value === "") {
        swal(title = "필수 항목 누락", msg = "이름을 입력하세요.",state="warning");
        f.userName.focus();
        return;
    }

    if (f.addr1.value === "") {
        swal(title = "필수 항목 누락", msg = "주소를 입력하세요.",state="warning");
        f.addr1.focus();
        return;
    }

    if (f.addr2.value === "") {
        swal(title = "필수 항목 누락", msg = "상세주소를 입력하세요.",state="warning");
        f.addr2.focus();
        return;
    }

    if (f.email.value === "") {
        swal(title = "필수 항목 누락", msg = "이메일을 입력하세요.",state="warning");
        f.email.focus();
        return;
    }


    // console.log("보내는 데이터 : " + $("#f").serialize())

    $.ajax({
            url: protocol + apiServer + "/user/userUpdate",
            type: "post", // 전송방식은 Post
            dataType: "JSON", // 전송 결과는 JSON으로 받기
            data: $("#f").serialize(), // form 태그 내 input 등 객체를 자동으로 전송할 형태로 변경하기
            xhrFields: {
                withCredentials: true
            },
            success: function (json) { // /notice/noticeUpdate 호출이 성공했다면..

                if (json === 1) { // 회원가입 성공
                    // console.log("회원정보 수정 성공")
                    swal({
                        title: "회원정보 수정 ",
                        text: "회원정보 수정 성공하였습니다!",
                        icon: "success",
                        buttons: {
                            confirm: {
                                text: "확인",
                                value: true,
                            },
                        },
                    }).then((value) => {
                        if (value) {
                            location.href = "/exception/main";
                        }
                    });
                } else { // 회원가입 실패
                    // console.log("회원정보 수정 실패")
                    swal(title = "회원가입 실패", msg = json.msg, state = "error");

                }

            }
        }
    )

}