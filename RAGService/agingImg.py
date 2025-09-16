import replicate
from dotenv import load_dotenv

# 사전에 환경변수 REPLICATE_API_TOKEN을 설정함
def create(imgURL, targetAge):
    output = replicate.run(
        "yuval-alaluf/sam:" + AGING_API_KEY,
        input={
            "image": imgURL,
            "target_age": str(targetAge)  # 나이 데이터 문자열로 변환
        })
    print("에이징 처리 결과 : ", output)
    return output

if __name__ == "__main__" :
    # 모듈 테스트용
    create("https://mintd-bucket.s3.ap-northeast-2.amazonaws.com/1.jpg", 20)