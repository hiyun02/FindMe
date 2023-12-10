var emailCheck = false; // 인증 여부

let randomCode ='';


$("#emailCheckBtn").click(function (){

    let email = $("#email").val(); // 값 가져오기
    console.log("가져온 아이디 값 :" + email);


    if (email == "") { //유효성검사
        swal(title = "필수 항목 누락", msg = "이메일을 입력하세요.",state="warning");
        $("#email").focus();
        return;
    }

    //랜덤코드 생성
    function randomString () {
        const chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz'
        const stringLength = 6
        let randomstring = ''
        for (let i = 0; i < stringLength; i++) {
            const rnum = Math.floor(Math.random() * chars.length)
            randomstring += chars.substring(rnum, rnum + 1)
        }
        return randomstring
    }
    randomCode = randomString();

    /**
     * 랜덤코드 이메일 보내주기
     */

    //ajax 통신
    $.ajax({
        type: "POST",
        url: protocol + apiServer + "/reg/sendCode",
        data: {"email": email, "mailCode":randomCode}, // email 키값으로 보낸다.
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {

            console.log("가져오는 값 : " + data);


            if (data === 1){
                swal(title = "인증코드가 전송" , msg ="인증 메일 발송 완료", state ="success");


            }else if (data === 2){
                swal(title = "잘못된 이메일" , msg ="가입한 이메일이랑 다릅니다. 다시 한번 확인해주세요!", state ="warning");


            }else if (data == 3) {
                swal(title = "인증 코드 전송 실패" , msg ="인증코드 전송을 실패했습니다. 다시 한번 시도해주세요.", state ="error");

            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) { // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
            alert("통신 실패.")
        }
    });

});


/**
 * 이메일 확인
 */

$("#codeCheckBtn").click(function (){
    // console.log("입력한 이메일 코드" + $("#emailCode").val());
    // console.log("wzip에서 발송한 이메일 코드 " + randomCode);

    let emailAuth = $("#emailAuth").val(); // 값 가져오기
    if (emailAuth == "") { //유효성검사
        swal(title = "필수 항목 누락", msg = "이메일을 입력하세요.",state="warning");
        $("#email").focus();
        return;
    }


    if ($("#emailAuth").val() === randomCode){
        emailCheck = true;
        swal(title = "인증 완료" , msg ="인증되었습니다. 계속 진행해주세요.", state ="success");

    }else {
        swal(title = "인증 실패" , msg ="인증번호를 다시한번 확인해주세요.", state ="error");

    };
});


/**
 * 비밀번호 최종 변경
 *
 */

$("#changePwd").click(function (){

    if (emailCheck == false) {
        swal(title = "필수 항목 누락", msg = "이메일 인증 절차를 수행해주세요.",state="warning");
        f.emailAuth.focus();
        return false;
    }

    if (f.password.value === "") {
        swal(title = "필수 항목 누락", msg = "비밀번호를 입력하세요.",state="warning");
        f.password.focus();
        return;
    }

    if (f.password2.value === "") {
        swal(title = "필수 항목 누락", msg = "비밀번호 확인을 입력하세요.",state="warning");
        f.password2.focus();
        return;
    }

    if (f.password.value !== f.password2.value) {
        swal(title = "비밀번호 불일치", msg = "비밀번호와 비밀번호 확인이 일치하지 않습니다.",state="warning");

        f.password.focus();
        return;
    }

    let password = $("#password").val(); // 값 가져오기
    console.log("바뀌는 비밀번호 : " + password);


    $.ajax({
        type: "POST",
        url: protocol + apiServer + "/user/changePwd",
        data: {"pwd": password }, // email 키값으로 보낸다.
        xhrFields: {
            withCredentials: true
        },
        success: function (data) {

            console.log("가져오는 값 : " + data);


            if (data === 1){
                swal(title = "인증코드가 전송" , msg ="인증 메일 발송 완료", state ="success");


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



            }else if (data === 0){
                swal(title = "실패" , msg ="실패하였습니다. 다시 시도해주세요!", state ="error");


            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) { // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
            alert("통신 실패.")
        }
    });

});