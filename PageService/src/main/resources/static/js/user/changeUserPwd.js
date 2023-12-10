var emailCheck = false; // 인증 여부



function pwdCheck() {


    let beforePwd = $("#beforePwd").val(); // 값 가져오기


    $.ajax({ // Ajax 호출
        url: protocol + apiServer + "/user/passWordCheck",
        type: "post",
        data: {"beforePwd": beforePwd},
        xhrFields: {
            withCredentials: true
        },
        success: function (res) { // Ajax 요청이 성공했다면...

            if (res === 0){
                swal(title = "비밀번호 인증 실패" , msg ="다시 한번 시도 해주세요!", state ="error");

            }else if (res === 1){

                emailCheck = true;
                swal(title = "비밀번호 인증 성공" , msg ="기존 비밀번호가 인증되었습니다. 비밀번호 변경을 진행해주세요!", state ="success");


            }
        }
    });



}


/**
 * 이메일 확인
 */

// $("#codeCheckBtn").click(function (){
//     // console.log("입력한 이메일 코드" + $("#emailCode").val());
//     // console.log("wzip에서 발송한 이메일 코드 " + randomCode);
//
//     if ($("#emailAuth").val() === randomCode){
//         emailCheck = true;
//         swal(title = "인증 완료" , msg ="인증되었습니다. 계속 진행해주세요.", state ="success");
//
//     }else {
//         swal(title = "인증 실패" , msg ="인증번호를 다시한번 확인해주세요.", state ="error");
//
//     };
// });