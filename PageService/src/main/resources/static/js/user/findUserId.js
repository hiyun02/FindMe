
var emailCheck = false; // 인증 여부



/**
 *  이메일 랜덤코드 생성
 */
let randomCode ='';

function emailSend(){

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
    console.log("eamil : " + email);
    console.log("randomCode : " + randomCode);

    const url = protocol + apiServer + "/reg/findCode"

    console.log("url : " + url);

    //ajax 통신
    $.ajax({
        type: "POST",
        url: protocol + apiServer + "/reg/findCode",
        data: {"email": email, "mailCode":randomCode}, // email 키값으로 보낸다.
        success: function (data) {

            console.log("가져오는 값 : " + data);


            if (data === 0){
                swal(title = "인증 코드 전송 실패" , msg ="인증코드 전송을 실패했습니다. 다시 한번 시도해주세요.", state ="error");

            }else if (data === 1){
                swal(title = "인증코드가 전송" , msg ="인증코드가 전송되었습니다. 전송된 코드번호를 확인해주세요.", state ="success");

            }

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) { // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
            alert("통신 실패.")
        }
    });

}


/**
 * 이메일 인증 코드
 */
function updatePw() {


    let email = $("#email").val(); // 값 가져오기
    let emailAuth =$("#emailAuth").val()


    if ( emailAuth === randomCode) {
        emailCheck = true;


            //ajax 통신
            $.ajax({
                type: "POST",
                url: protocol + apiServer + "/reg/idFind",
                data: {"email": email } ,      // email 키값으로 보낸다.
                success: function (data) {

                    console.log("가져오는 값 : " + data);

                   if (data == ""){
                        swal(title = "등록한 이메일인지 확인해주세요", msg = "등록한 이메일인지 확인해주세요.", state = "error");
                    } else {
                        swal(title = "아이디 찾기 성공", msg = "사용자님의 "+data +"입니다", state = "success");
                    }

                },
                error: function (XMLHttpRequest, textStatus, errorThrown) { // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
                    alert("통신 실패.")
                }
            });



    }else if (emailAuth == "") {
        swal(title = "인증 실패", msg = "인증 번호를 입력해주세요", state = "error");

    } else {
        swal(title = "인증 실패", msg = "인증번호를 다시 한번 확인해주세요.", state = "error");
    }


}